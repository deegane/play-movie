package dao;

import model.LocalMovie;

public interface MovieDAO {

    String getRating(String title);

    LocalMovie getMovie(String title);
}
