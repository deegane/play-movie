package model;

import com.fasterxml.jackson.annotation.*;
import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"Title",
		"Year",
		"Rated",
		"Released",
		"Runtime",
		"Genre",
		"Director",
		"Writer",
		"Actors",
		"Plot",
		"Language",
		"Country",
		"Awards",
		"Poster",
		"Ratings",
		"Metascore",
		"imdbRating",
		"imdbVotes",
		"imdbID",
		"Type",
		"DVD",
		"BoxOffice",
		"Production",
		"Website",
		"Response"
})
public class Movie {

	private String overallRating = "0.0";

	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}

	@JsonProperty("Title")
	public String title;
	@JsonProperty("Year")
	public String year;
	@JsonProperty("Rated")
	public String rated;
	@JsonProperty("Released")
	public String released;
	@JsonProperty("Runtime")
	public String runtime;
	@JsonProperty("Genre")
	public String genre;
	@JsonProperty("Director")
	public String director;
	@JsonProperty("Writer")
	public String writer;
	@JsonProperty("Actors")
	public String actors;
	@JsonProperty("Plot")
	public String plot;
	@JsonProperty("Language")
	public String language;
	@JsonProperty("Country")
	public String country;
	@JsonProperty("Awards")
	public String awards;
	@JsonProperty("Poster")
	public String poster;
	@JsonProperty("Ratings")
	public List<Rating> ratings = new ArrayList<>();
	@JsonProperty("Metascore")
	public String metascore;
	@JsonProperty("imdbRating")
	public String imdbRating;
	@JsonProperty("imdbVotes")
	public String imdbVotes;
	@JsonProperty("imdbID")
	public String imdbID;
	@JsonProperty("Type")
	public String type;
	@JsonProperty("DVD")
	public String dVD;
	@JsonProperty("BoxOffice")
	public String boxOffice;
	@JsonProperty("Production")
	public String production;
	@JsonProperty("Website")
	public String website;
	@JsonProperty("Response")
	public String response;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Movie() {
	}

	public Movie(String title) {
		this.title=title;
	}

	public String getImdbRating() {
		return getRating("Internet Movie Database");
	}

	private String getRating(String site) {
		return ratings.stream()
				.filter(rating -> site.equals(rating.getSource()))
				.findAny()
				.orElse(new Rating()).getValue();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("overallRating", overallRating)
				.add("title", title)
				.add("ratings", ratings)
				.toString();
	}
}