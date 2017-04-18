package model;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"Source",
		"Value"
})
public class Rating {

	public Rating() {
	}

	public Rating(String source, String value) {
		this.source = source;
		this.value = value;
	}

	@JsonProperty("Source")
	public String source;
	@JsonProperty("Value")
	public String value = "0.0";
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

	public String getSource() {
		return source;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rating rating = (Rating) o;
		return Objects.equals(source, rating.source) &&
				Objects.equals(value, rating.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(source, value);
	}

	@Override
	public String toString() {
		return "Rating{" +
				"source='" + source + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}