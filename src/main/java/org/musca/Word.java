package org.musca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Word {

    private final String value;

    @JsonCreator
    public Word(@JsonProperty("value") String value) {
        this.value = value;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                '}';
    }
}
