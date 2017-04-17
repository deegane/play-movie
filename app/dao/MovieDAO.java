package dao;

import model.LocalMovie;

public interface MovieDAO {

    String getRating(String title);

    LocalMovie getMovie(String title);

    default void postMovieRating(String title, String rating) {
        System.out.println("default implementation");
    }
}
