package service;


import model.Movie;

import java.util.concurrent.CompletionStage;

public interface FetchMovieService {
     CompletionStage<Movie> getMovie(String title);
}
