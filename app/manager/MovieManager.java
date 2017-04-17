package manager;

import dao.MovieDAO;
import model.Movie;
import service.FetchMovieService;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class MovieManager {

    private FetchMovieService fetchMovieService;
    private MovieDAO movieDAO;

    @Inject
    public MovieManager(FetchMovieService fetchMovieService, MovieDAO movieDAO) {
        this.fetchMovieService = fetchMovieService;
        this.movieDAO = movieDAO;
    }

    //TODO: get both local and remote movies async then join up
    public List<Movie> getOrderedByBestMovie(List<String> titles) throws SQLException {
        List<Movie> fetchedMovies = new ArrayList<>();

        List<CompletionStage<Movie>> fetchedFutures = new ArrayList<>();

        for(String title : titles) {
            fetchedFutures.add(fetchMovieService.getMovie(title));
        }

        try {
            for (CompletionStage<Movie> movie : fetchedFutures) {
                Movie m = movie.toCompletableFuture().get();
                checkRating(m);
                fetchedMovies.add(m);
            }
        } catch (InterruptedException | ExecutionException e) {
            // all is lost
        }

        fetchedMovies.sort(Comparator.comparing(Movie::getImdbRating).reversed());
        return fetchedMovies;
    }

    private void checkRating(Movie m) throws SQLException {
        String storedRating = movieDAO.getRating(m.title);
        if(!storedRating.isEmpty()) {
            m.setOverallRating(storedRating);
        } else {
            m.setOverallRating(m.getImdbRating());
        }
    }

}