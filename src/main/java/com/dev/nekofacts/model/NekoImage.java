package com.dev.nekofacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NekoImage {
    private String id;
    private String url;

    // What is JsonProperty? https://stackoverflow.com/questions/12583638/when-is-the-jsonproperty-property-used-and-what-is-it-used-for
    @JsonProperty
    private List<String> tags;
}
