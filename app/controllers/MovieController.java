package controllers;


import dao.MovieDAO;
import model.Movie;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.MovieService;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class MovieController extends Controller {

    private MovieService movieService;
    private MovieDAO movieDAO;

    @Inject
    public MovieController(MovieService movieService, MovieDAO movieDAO) {
        this.movieService = movieService;
        this.movieDAO = movieDAO;
    }

    public Result movie(String firstMovie, String secondMovie) throws Exception {

        List<Movie> movies = new ArrayList<>();

        List<CompletionStage<Movie>> futures = new ArrayList<>();
        futures.add(movieService.getMovie(firstMovie));
        futures.add(movieService.getMovie(secondMovie));

        try {
            for (CompletionStage<Movie> movie : futures) {
                Movie m = movie.toCompletableFuture().get();
                checkRating(m);
                movies.add(m);
            }
        } catch (InterruptedException | ExecutionException e) {
            // all is lost
        }

        movies.sort(Comparator.comparing(Movie::getImdbRating).reversed());
        return ok(Json.toJson(movies));
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