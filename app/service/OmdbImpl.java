package service;

import model.Movie;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;


public class OmdbImpl implements FetchMovieService {

    private final WSClient ws;
    private final static String BASE_URL = "http://www.omdbapi.com/";

    @Inject
    public OmdbImpl(WSClient ws ) {
        this.ws=ws;
    }

    public CompletionStage<Movie> getMovie(String title) {

        WSRequest request = ws.url(BASE_URL).setQueryParameter("t", title);

        CompletionStage<Movie> movie = request.get()
                .thenApply(WSResponse::asJson)
                .thenApply(json -> Json.fromJson(json, Movie.class));

        return movie;
    }


}
