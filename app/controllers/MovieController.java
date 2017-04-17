package controllers;


import com.google.common.collect.Lists;
import manager.MovieManager;
import model.LocalMovie;
import model.Movie;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/*
localhost:9000/movie?m1=predator&m2=the terminator

localhost:9000/movie/update
{
  "title": "predator",
  "rating": "9.5"
}
 */
public class MovieController extends Controller {

    private MovieManager movieManager;

    @Inject
    public MovieController(MovieManager movieManager) {
        this.movieManager = movieManager;
    }

    public Result movie(String firstMovie, String secondMovie) throws Exception {
        List<Movie> movies = movieManager.getOrderedByBestMovie(
                Lists.newArrayList(firstMovie, secondMovie));
        return ok(Json.toJson(movies));
    }

    public Result postMovieRating() {
        LocalMovie localMovie = Json.fromJson(request().body().asJson(), LocalMovie.class);
        movieManager.postMovieRating(localMovie.getTitle(), localMovie.getRating());
        return ok("updated");
    }
}
