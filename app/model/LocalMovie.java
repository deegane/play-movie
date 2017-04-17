package model;


public class LocalMovie {
    private String title;
    private String rating;

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
