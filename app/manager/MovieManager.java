package manager;

import dao.MovieDAO;
import model.Movie;
import play.Configuration;
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
    private Configuration configuration;

    @Inject
    public MovieManager(FetchMovieService fetchMovieService, MovieDAO movieDAO, Configuration configuration) {
        this.fetchMovieService = fetchMovieService;
        this.movieDAO = movieDAO;
        this.configuration = configuration;
    }

    //TODO: get both local and remote movies async then join up
    public List<Movie> getOrderedByBestMovie(List<String> titles) throws SQLException {

        System.out.println(configuration.getString("omdbapi.url"));

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

    public void postMovieRating(String title, String rating) {
        movieDAO.postMovieRating(title, rating);
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