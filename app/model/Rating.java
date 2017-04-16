package model;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"Source",
		"Value"
})
public class Rating {

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
	public String toString() {
		return "Rating{" +
				"source='" + source + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}