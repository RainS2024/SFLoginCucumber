package org.GitHub.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.GitHub.RequestPOJO.CreateDataPOJO;
import org.GitHub.RequestPOJO.UpdateDataPOJO;
import org.GitHub.Utils.EnvironmentDetails;
import org.GitHub.Utils.ExtentReportsUtility;

import org.testng.Assert;

import java.util.HashMap;
import java.util.List;

@Slf4j
public class APIHelper {
	ExtentReportsUtility report=ExtentReportsUtility.getInstance(); 
    RequestSpecification reqSpec;
    String token = EnvironmentDetails.getProperty("mytoken");
    String username = EnvironmentDetails.getProperty("owner");
    
    public APIHelper() {
        RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");    
      
    }
    
    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization","Bearer "+ token);
        return headers;
    }
    
    public Response getData(String reponame) {
        reqSpec = RestAssured.given()
        		.header("Authorization","Bearer"+ token)
        		.pathParam("owner",username)
        		.pathParam("repo",reponame);
        		
        Response response = null;
        try {
            response = reqSpec.get("/repos/{owner}/{repo}");
            
           // response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Get data is failing due to :: " + e.getMessage());
            report.logTestFailed("Get data is failing due to :: "+ e.getMessage());
        }
        return response;
    }

    
    public Response negData() {
        reqSpec = RestAssured.given()
        		.header("Authorization","Bearer"+ token)
        		.pathParam("owner",username)
        		.pathParam("repo","");
        		
        Response response = null;
        try {
            response = reqSpec.get("/repos/{owner}/{repo}");
            
           // response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Get data is failing due to :: " + e.getMessage());
            report.logTestFailed("Get data is failing due to :: "+ e.getMessage());
        }
        return response;
    }
    
    public Response getAllData() {
        reqSpec = RestAssured.given().auth().oauth2(token);
        		//.header("Authorization","Bearer"+ token);		
        		
        Response response = null;
        try {
            response = reqSpec.get("/user/repos");
            
           // response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Get data is failing due to :: " + e.getMessage());
            report.logTestFailed("Get data is failing due to :: "+ e.getMessage());
        }
        return response;
    }

    
    public Response CreateData(CreateDataPOJO CreateDataRequest) {
        reqSpec = RestAssured.given();
        		//.auth().oauth2(token);
        //.header("Authorization","Bearer"+ token);
        Response response = null;
        try {
            log.info("Adding below data :: " + new ObjectMapper().writeValueAsString(CreateDataRequest));
            reqSpec.headers(getHeaders());
            reqSpec.body(new ObjectMapper().writeValueAsString(CreateDataRequest)); //Serializing addData Request POJO classes to byte stream
            response = reqSpec.post("/user/repos");
            //response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Add data functionality is failing due to :: " + e.getMessage());
            report.logTestFailed("Add data is failing due to :: "+ e.getMessage());
        }
        
        return response;
    }

    public Response patchData(String RepoName ,UpdateDataPOJO updateDataRequest) {
        reqSpec = RestAssured.given().auth().oauth2(token)
                  .pathParam("owner",username)
		          .pathParam("repo2",RepoName);
        Response response = null;
        try {
            reqSpec.body(new ObjectMapper().writeValueAsString(updateDataRequest)); //Serializing addData Request POJO classes to byte stream
            response = reqSpec.patch("/repos/{owner}/{repo2}");
           // response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Update data functionality is failing due to :: " + e.getMessage());
            report.logTestFailed("Update data is failing due to :: "+ e.getMessage());
        }
        return response;
    }

    public Response deleteData(String name) {
    	reqSpec = RestAssured.given().auth().oauth2(token)
                .pathParam("owner",username)
		          .pathParam("repo3",name);
        Response response = null;
        try {
            response = reqSpec.delete("/repos/{owner}/{repo3}");
            //response.then().log().all();
        } catch (Exception e) {
            Assert.fail("Delete data functionality is failing due to :: " + e.getMessage());
            report.logTestFailed("Delete data is failing due to :: "+ e.getMessage());
        }
        return response;
    }
    
   
   

}
