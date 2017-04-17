package model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovieTest {

    Movie movie;

    @Before
    public void setup() {
        movie = new Movie("rambo");
        Rating rating = new Rating("Internet Movie Database","9.1");
        movie.ratings.add(rating);

    }

    @Test
    public void testRatingComparisonSuccess () {
        Assert.assertEquals("9.1",movie.getImdbRating());
    }

    @Test
    public void testRatingComparisonFail () {
        Assert.assertNotEquals("5.0",movie.getImdbRating());
    }
}
