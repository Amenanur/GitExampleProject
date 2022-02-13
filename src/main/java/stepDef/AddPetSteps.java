package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddPetSteps {
    String payloadForAddPet;
    String URL;
    Response response;



    @Given("I have valid URL with all valid data")
    public void iHaveValidURLWithAllValidData() {

         URL = "https://petstore.swagger.io/v2/pet";

        payloadForAddPet = "{\n" +
                "  \"id\": 12,\n" +
                "  \"category\": {\n" +
                "    \"id\": 12,\n" +
                "    \"name\": \"husky\"\n" +
                "  },\n" +
                "  \"name\": \"tommy\",\n" +
                "  \"www.google.com/image1.png\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 12,\n" +
                "      \"name\": \"tag1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

    }

    @When("I send the POST request with valid header")
    public void iSendThePostRequestWithValidHeader() {
      //  RestAssured.given().accept("application/json)
    //    RestAssured.given().accept(ContentType.JSON).contentType("application/json");
  response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
         .when()
         .body(payloadForAddPet).then().log().all().when()
                .post(URL);


    }

    @Then("I validate the successful response")
    public void iValidateTheSuccessfulResponse() {

        response.then().statusCode(200);
        response.then().log().all();
    //    response.as(String.class);
  assert response.body().equals(payloadForAddPet);

    }

}