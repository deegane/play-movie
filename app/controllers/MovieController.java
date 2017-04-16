package controllers;


import com.google.common.collect.Lists;
import model.Movie;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.MovieService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class MovieController extends Controller {

    private MovieService movieService;

    @Inject
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public Result index() {

        List<String> movieTitles = Lists.newArrayList("predator","the terminator");
        List<Movie> movies = new ArrayList<>();

        List<CompletionStage<Movie>> futures = new ArrayList<>();

        for(String title : movieTitles) {
            futures.add(movieService.getMovie(title));
        }

        // Wait for all movies before sorting and returning
        try {

            for (CompletionStage<Movie> movie : futures) {
                movies.add(movie.toCompletableFuture().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            // all is lost
        }

        Collections.sort(movies, Comparator.comparing(Movie::getImdbRating).reversed());

        return ok(Json.toJson(movies.get(0)));
    }
}
