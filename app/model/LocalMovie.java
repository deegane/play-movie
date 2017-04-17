package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*

{
  "title": "predator",
  "rating": "9.5"
}

create table movies (title varchar(255) NOT NULL UNIQUE, rating varchar(4));
insert into movies values ("the terminator", "9.9");

 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class LocalMovie {
    private String title;
    private String rating;

    public LocalMovie() {
    }

    public LocalMovie(String title, String rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }
}
