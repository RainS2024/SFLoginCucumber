Feature: Login

@Smoke
  Scenario: Login Error to Salesforce
    Given User open Salesforce application
    When User on "LoginPage"
    When User Enters value in username "UserName"
    When User Enters novalue in password
    When User Clicks on Login
    Then Verify we can see Error Message
@Smoke @Regression
 Scenario: Login to Salesforce
    When User open Salesforce application
    When User on "LoginPage"
    When User Enters value in username "UserName"
    When User Enters value in password "Password"
    When User Clicks on Login
    When User on "Homepage"
    Then Verify we can see Home Page
 @Functional @Regression
 Scenario: Check Remember me
    When User open Salesforce application
    When User on "LoginPage"
    When User Enters value in username "UserName"
    When User Enters value in password "Password"
    When User Clicks on CheckBox
    When User Clicks on Login
    When User on "HomePage"
    When User Clicks on UserMenudropdown
    When User Clicks on Log out
    When User on "Login Page"
    Then Verify we can see Login sales force page
 @Functional   
  Scenario: ForgotPassword4A
    When User open Salesforce application
    When User on "LoginPage"
    When User Clicks on Forgot password
    When User on page "ForgotPasswordPage"
    When User Enters value in FPUsername "FPUserName"
    When User Clicks on Contiue button
    When User on page "NewPage"
    Then Verify we can see Password reset message
    @Functional
  Scenario: ForgotPassword4B
    When User open Salesforce application
    When User on "LoginPage"
    When User Enters Wrong UserName
    When User Enters Wrong Password
    When User Clicks on Login
    Then Verify we can see newerror message
    
 