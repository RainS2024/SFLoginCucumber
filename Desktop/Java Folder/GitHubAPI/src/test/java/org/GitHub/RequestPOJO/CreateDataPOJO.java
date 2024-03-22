package org.GitHub.RequestPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateDataPOJO {
@JsonProperty(value = "name")
private String name;
@JsonProperty(value = "description")
private String description;
@JsonProperty(value = "homepage")
private String homepage;
@JsonProperty(value = "private")
private boolean Private;


}

