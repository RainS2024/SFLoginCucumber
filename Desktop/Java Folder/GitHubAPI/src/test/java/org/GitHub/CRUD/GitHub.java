package org.GitHub.CRUD;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.hamcrest.Matchers.lessThan;

import java.util.List;
import java.util.Map;

import org.GitHub.Utils.EnvironmentDetails;
import org.GitHub.Utils.ExtentReportsUtility;
import org.GitHub.Utils.JsonSchemaValidate;

import org.GitHub.base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import org.GitHub.base.APIHelper;

import org.GitHub.RequestPOJO.CreateDataPOJO;
import org.GitHub.RequestPOJO.UpdateDataPOJO;
import org.GitHub.ResponsePOJO.CreateDataResponse;
import org.GitHub.ResponsePOJO.NegativeCreateMsg;


import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@Listeners(org.GitHub.Utils.TestEventListenersUtility.class)	

public class GitHub extends BaseTest {
ExtentReportsUtility report=ExtentReportsUtility.getInstance(); 
APIHelper apiHelper;
String name,description,homepage;
String NewAccount;
private Faker faker;

@BeforeClass
public void beforeClass() {
    faker = new Faker();
    apiHelper = new APIHelper();
    }


@BeforeTest  
	public void setup()	{
		 RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");  ;
	 
	 }

@Test

public void validateGetSingleRepository() {
	Response data = apiHelper.getData();
	
	data.then().log().all();
	data.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
	Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code" + data.getStatusLine());
    //Assert.assertEquals(data.as(GetOneRepositoryResponse.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    JsonSchemaValidate.validateSchema(data.asPrettyString(), "GetOneRepository.json");	
		
}
@Test
public void negativevalidationGetSingleRepository() {
	Response data = apiHelper.negData();
	data.then().log().all();
	Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code" + data.getStatusLine());
    //Assert.assertEquals(data.as(GetOneRepositoryResponse.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
     JsonSchemaValidate.validateSchema(data.asPrettyString(), "negresponse.json");	
		
}

@Test

public void validateGetAllRepository() {
	Response data = apiHelper.getAllData();
	data.then().log().all();
	data.body().jsonPath().get("size");
	System.out.println("Total No. of repositories is -->" + data.body().jsonPath().getList("name").size());
	System.out.println("Repositories name that are public is -->" + data.body().jsonPath().get("findAll{it.visibility=='public'}.name"));
	data.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
	Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code" + data.getStatusLine());
    //Assert.assertEquals(data.as(GetOneRepositoryResponse.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    //JsonSchemaValidate.validateSchema(data.asPrettyString(), "GetRepository.json");	
		
}
@Test

public void validateAddDataFunctionality() {
	name ="Hello-World" + faker.number().numberBetween(1000, 2000);;
	description="This is your first repo!";
	homepage="https://github.com";
    CreateDataPOJO addDataRequest = CreateDataPOJO.builder().name(name).description(description).homepage(homepage).build();
    Response response = apiHelper.CreateData(addDataRequest);
   // List<CreateDataResponse> getDataResponseList = response.getBody().as(new TypeRef<List<CreateDataResponse>>() {
    //});
    //System.out.println(getDataResponseList.toString());
    System.out.println(response.prettyPrint());
    System.out.println(response.getStatusLine());
    String NewAccount = response.getBody().jsonPath().getString("name");
    System.out.println(response.getBody().jsonPath().getString("name"));
    System.out.println(response.getBody().jsonPath().getString("owner.login"));
    System.out.println(response.getBody().jsonPath().getString("owner.type"));
    
    report.logTestInfo("name created -->" + name);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code" + response.getStatusLine());
   

}

@Test
public void negativeAddDataFunctionality() {
	name ="Hello-World";
	description="This is your first repo!";
	homepage="https://github.com";
    CreateDataPOJO addDataRequest = CreateDataPOJO.builder().name(name).description(description).homepage(homepage).build();
    Response response = apiHelper.CreateData(addDataRequest);
    report.logTestInfo("name created -->" + name);
    response.getBody().jsonPath().get("[0].errors.message");
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY, "Add data functionality is not working as expected.");
   // System.out.println("The messge is " + getDataResponseList.get(0));
    System.out.println(response.getStatusLine());
    System.out.println(response.prettyPrint());
    System.out.println("Message received --->" + response.getBody().jsonPath().getString("errors[0].message"));
    report.logTestInfo("Status Code" + response.getStatusLine());
   // Assert.assertEquals(response.as(EditDataResponsePOJO.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    JsonSchemaValidate.validateSchema(response.asPrettyString(), "negCreate.json");

}
@Test
public void validateUpdateDataFunctionality() {
	name = "Rainbow";
	description= "my repository created using apis after update";
	UpdateDataPOJO updateDataRequest = UpdateDataPOJO.builder().name(name).description(description).build();
    Response response = apiHelper.patchData(updateDataRequest);
    report.logTestInfo("Account got updated with description as -->" + updateDataRequest.getDescription());
    System.out.println("Status Code is --> " + response.getStatusLine());
    System.out.println("Account name updated to --->" + response.getBody().jsonPath().getString("name"));
    Assert.assertEquals(response.getBody().jsonPath().getString("name"),"Rainbow");
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code" + response.getStatusLine());
   // Assert.assertEquals(response.as(EditDataResponsePOJO.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    //JsonSchemaValidate.validateSchema(response.asPrettyString(), "EditData.json");

}
@Test

public void validateDeleteData() {

    Response data = apiHelper.deleteData();
    Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NO_CONTENT, "Delete data functionality is not working as expected.");
    report.logTestInfo("Status Code" + data.getStatusLine());
   // Assert.assertEquals(data.as(EditDataResponsePOJO.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    System.out.println("------------------Account deleted------------------------");
    //String actualResponse = data.jsonPath().prettyPrint();
    System.out.println(data.getBody());
    System.out.println("Status Code is -->" + data.getStatusCode());
  
}
@Test
public void negativeDeleteData() {

    Response data = apiHelper.deleteData();
    Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Delete data functionality is not working as expected.");
    report.logTestInfo("Status Code" + data.getStatusLine());
   // Assert.assertEquals(data.as(EditDataResponsePOJO.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    System.out.println("------------------Account not found------------------------");
    //String actualResponse = data.jsonPath().prettyPrint();
    System.out.println(data.getBody());
    System.out.println("Status Code is -->" + data.getStatusCode());
  
}


}
