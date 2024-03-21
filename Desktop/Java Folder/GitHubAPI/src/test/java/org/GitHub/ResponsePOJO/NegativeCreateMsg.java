package org.GitHub.ResponsePOJO;


import java.util.ArrayList;

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
public class NegativeCreateMsg {
	@JsonProperty("resource") 
    public String getResource() { 
		 return this.resource; } 
    public void setResource(String resource) { 
		 this.resource = resource; } 
    String resource;
    @JsonProperty("code") 
    public String getCode() { 
		 return this.code; } 
    public void setCode(String code) { 
		 this.code = code; } 
    String code;
    @JsonProperty("field") 
    public String getField() { 
		 return this.field; } 
    public void setField(String field) { 
		 this.field = field; } 
    String field;
    @JsonProperty("message") 
    public String getMessage() { 
		 return this.message; } 
    public void setMessage(String message) { 
		 this.message = message; } 
    String message;
}


