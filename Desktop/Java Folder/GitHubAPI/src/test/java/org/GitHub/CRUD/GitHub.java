package org.GitHub.CRUD;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.hamcrest.Matchers.lessThan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.GitHub.Utils.EnvironmentDetails;
import org.GitHub.Utils.ExtentReportsUtility;
import org.GitHub.Utils.JsonSchemaValidate;
import org.GitHub.Utils.TestDataUtils;
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
import org.GitHub.ResponsePOJO.GetSingleRepo;
import org.GitHub.ResponsePOJO.NegativeCreateMsg;
import org.GitHub.ResponsePOJO.NegativeResponse;

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
boolean Private;
String NewAccount = "";
String Reponame = "";
private Faker faker;

@BeforeClass
public void beforeClass() {
    faker = new Faker();
    apiHelper = new APIHelper();
    Reponame ="HelloWorld" ;
    NewAccount = Reponame + "-" + faker.number().numberBetween(1000, 2000);
    }


@BeforeTest  
	public void setup()	{
		 RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");  ;
	 
	 }

@Test (enabled=true)

public void validateGetSingleRepository() {
	Response data = apiHelper.getData(EnvironmentDetails.getProperty("repo"));
	
	data.then().log().all();
	data.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
	Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Add data functionality is not working as expected.");
	String Fullname= data.body().jsonPath().getString("full_name");
	System.out.println("Full_Name is -->" +Fullname);
	System.out.println("Status Code" + data.getStatusCode());
	System.out.println("Response Header is -->" + data.getHeader("Content-Type") );
	System.out.println("Default Branch is -->" + data.body().jsonPath().getString("default_branch") );
	Assert.assertEquals(data.body().jsonPath().getString("default_branch"), "master");
	Assert.assertEquals(data.body().jsonPath().getString("full_name"), Fullname);
    
	report.logTestInfo("Status Code is -->" + data.getStatusLine());
    report.logTestInfo("Full_Name is -->" + data.body().jsonPath().getString("full_name"));
    report.logTestInfo("Response Header is -->" + data.getHeader("Content-Type") );
    report.logTestInfo("Default Branch is -->" + data.body().jsonPath().getString("default_branch"));
    Assert.assertEquals(data.as(GetSingleRepo.class).getFull_name(),Fullname, "The value of Full_name is not as expected in response ");
    Assert.assertEquals(data.as(GetSingleRepo.class).getDefault_branch(),"master","The default branch is not as expected in response ");
    JsonSchemaValidate.validateSchema(data.asPrettyString(), "GetOneRepository.json");	
		
}
@Test(enabled=true)
public void negativevalidationGetSingleRepository() {
	Response data = apiHelper.negData();
	data.then().log().all();
	Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code -->" + data.getStatusLine());
    report.logTestInfo("Message -->" + data.body().jsonPath().getString("message"));
    System.out.println("Message --> " + data.body().jsonPath().getString("message"));
    Assert.assertEquals(data.as(NegativeResponse.class).getMessage(), TestDataUtils.getProperty("message"), "The message is not as expected in response ");
    JsonSchemaValidate.validateSchema(data.asPrettyString(), "negresponse.json");	
		
}

@Test(enabled=true)

public void validateGetAllRepository() {
	Response data = apiHelper.getAllData();
	//data.then().log().all();
	data.body().jsonPath().get("size");
	System.out.println("Total No. of repositories -->" + data.body().jsonPath().getList("name").size());
	System.out.println("Repositories name that are public -->" + data.body().jsonPath().get("findAll{it.visibility=='public'}.name"));
	data.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
	Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_OK, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code" + data.getStatusLine());
    report.logTestInfo("Total No. of repositories -->" + data.body().jsonPath().getList("name").size());
    report.logTestInfo("Total No. of repositories -->" + "Repositories name that are public is -->" + data.body().jsonPath().get("findAll{it.visibility=='public'}.name"));
    //Assert.assertEquals(data.as(GetSingleRepo.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    //JsonSchemaValidate.validateSchema(data.asPrettyString(), "GetRepository.json");	
		
}

@Test(priority = 0, description = "validate added data")

public void validateAddDataFunctionality() {
	
	description="This is your first repo!";
	homepage="https://github.com";
	Private = false;
    CreateDataPOJO addDataRequest = CreateDataPOJO.builder().name(Reponame).description(description).homepage(homepage).Private(Private).build();
    Response response = apiHelper.CreateData(addDataRequest);
    response.then().assertThat().statusCode(201);
    
    CreateDataResponse AcCreated = response.as(CreateDataResponse.class); 
    String AcName = AcCreated.getName();
    System.out.println("The Ac created -->" + AcName);
    report.logTestInfo("The Ac created -->" + AcName);
    Assert.assertEquals(AcCreated.getName(), Reponame, "Account Name is not Matching");
    
    String Login = AcCreated.getOwner().getLogin();
    System.out.println("Login --->" + Login);
    report.logTestInfo("Login --->" + Login);
    Assert.assertEquals(Login, "RainS2024", "Login Name is Matching");
    
    String Type = AcCreated.getOwner().getType();
    System.out.println("TypeCreated-->" + Type);
    report.logTestInfo("TypeCreated-->" + Type);
    Assert.assertEquals(Type, "User", "Type is not Matching");
    
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code" + response.getStatusLine());
    
    //System.out.println(response.prettyPrint());
   /* System.out.println(response.getStatusLine());
    String NewAccount = response.getBody().jsonPath().getString("name");
    System.out.println(response.getBody().jsonPath().getString("name"));
    System.out.println(response.getBody().jsonPath().getString("owner.login"));
    System.out.println(response.getBody().jsonPath().getString("owner.type"));*/    
  
  
}

@Test(priority = 1, description = "validate negative added data ", dependsOnMethods = "validateAddDataFunctionality")
public void negativeAddDataFunctionality() {
	description="This is your first repo!";
	homepage="https://github.com";
    CreateDataPOJO addDataRequest = CreateDataPOJO.builder().name(Reponame).description(description).homepage(homepage).build();
    Response response = apiHelper.CreateData(addDataRequest);
    response.prettyPrint();
    report.logTestInfo("name created -->" + Reponame);
    response.getBody().jsonPath().get("[0].errors.message");
   
    Assert.assertEquals(response.body().jsonPath().getString("errors.message"),"[name already exists on this account]") ;
    report.logTestInfo(response.body().jsonPath().getString("errors.message"));
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code-->" + response.getStatusLine());
    JsonSchemaValidate.validateSchema(response.asPrettyString(), "negCreate.json");
    
   /* NegativeCreateMsg negative = response.as(NegativeCreateMsg.class); 
    ArrayList<String> ErrorMessage = negative.getErrorMessage();
    for(int i=0;i<ErrorMessage.size();i++) {
    System.out.println(ErrorMessage.toString());}
    report.logTestInfo("Error Message" + ErrorMessage);
    Assert.assertEquals(ErrorMessage, "name already exists on this account", "Error Message not displayed");
    System.out.println("The message is " + getDataResponseList.get(0));
    System.out.println(response.getStatusLine());
    System.out.println(response.prettyPrint());
    System.out.println("Message received --->" + response.getBody().jsonPath().getString("errors[0].message"));*/

}
@Test(priority = 2, description = "validate update data ", dependsOnMethods = "validateAddDataFunctionality")
public void validateUpdateDataFunctionality() {
	name = NewAccount;
	description= "my repository created using apis after update";
	UpdateDataPOJO updateDataRequest = UpdateDataPOJO.builder().name(name).description(description).build();
    Response response = apiHelper.patchData(Reponame, updateDataRequest);
    report.logTestInfo("Account Name got uodated to--> " + response.getBody().jsonPath().getString("name"));
    report.logTestInfo("Account got updated with description as -->" + updateDataRequest.getDescription());
    System.out.println("Status Code is --> " + response.getStatusLine());
    System.out.println("Account name updated to --->" + response.getBody().jsonPath().getString("name"));
    Assert.assertEquals(response.getBody().jsonPath().getString("name"),NewAccount);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Add data functionality is not working as expected.");
    report.logTestInfo("Status Code-->" + response.getStatusLine());
   // Assert.assertEquals(response.as(EditDataResponsePOJO.class).getStatus(), TestDataUtils.getProperty("successStatusMessage"), "The value of status key is not as expected in response ");
    //JsonSchemaValidate.validateSchema(response.asPrettyString(), "EditData.json");

}
@Test(priority = 3, description = "validate Delete data ", dependsOnMethods = "validateUpdateDataFunctionality")

public void validateDeleteData() {

    Response data = apiHelper.deleteData(NewAccount);
    Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NO_CONTENT, "Delete data functionality is not working as expected.");
    report.logTestInfo("Status Code" + data.getStatusLine());
    System.out.println("------------------Account deleted------------------------");
    System.out.println(data.getBody().asString());
    System.out.println("Status Code is -->" + data.getStatusCode());
  
}
@Test(priority = 4, description = "negative valaidation of Delete data ", dependsOnMethods = "validateDeleteData")
public void negativeDeleteData() {

    Response data = apiHelper.deleteData(NewAccount);
    Assert.assertEquals(data.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Delete data functionality is not working as expected.");
    report.logTestInfo("Status Code is -->" + data.getStatusLine());
    report.logTestInfo("Message is -->" + data.body().jsonPath().getString("message"));
    System.out.println("------------------Account not found------------------------");
    String Response = data.body().jsonPath().getString("message");
    Assert.assertEquals(Response, "Not Found");
    System.out.println(data.getBody().asString());
    System.out.println("Status Code is -->" + data.getStatusCode());
  
}


}
