package services;


import model.Movie;

import java.util.concurrent.CompletionStage;

public interface MovieService {
     CompletionStage<Movie> getMovie(String title);
}
