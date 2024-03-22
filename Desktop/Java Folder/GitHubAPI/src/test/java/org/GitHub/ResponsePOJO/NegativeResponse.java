package org.GitHub.ResponsePOJO;

import com.fasterxml.jackson.annotation.JsonProperty;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class NegativeResponse{
    @JsonProperty("message") 
    public String getMessage() { 
		 return this.message; } 
    public void setMessage(String message) { 
		 this.message = message; } 
    String message;
    @JsonProperty("documentation_url") 
    public String getDocumentation_url() { 
		 return this.documentation_url; } 
    public void setDocumentation_url(String documentation_url) { 
		 this.documentation_url = documentation_url; } 
    String documentation_url;
    
    
    
    
}