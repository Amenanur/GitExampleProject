package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class PutSteps {

    String payloadForPutPet;
    String URL;
    Response response;
    @Given("I have valid URL with all valid data")
    public void iHaveValidURLWithAllValidData() {

        URL = "https://petstore.swagger.io/v2/pet";

        payloadForPutPet = "{\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"husky\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"www.google.com/image1.png\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"tag1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

    }

    @When("I send the PUT request with valid header")
    public void iSendThePutRequestWithValidHeader() {
        response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .body(payloadForPutPet).then().log().all().when()
                .put(URL);

    }

    @Then("I validate the successful response")
    public void iValidateTheSuccessfulResponse() {
        response.then().statusCode(200);
        response.then().log().all();
        // Assert.assertEquals((int)response.body().path("id"),1);
        //    response.as(string.class);

        assert response.body().path("id").equals(1);
//  Assert.assertEquals((int)response.body().path("category.id"),1);
        Assert.assertEquals(response.body().path("category.name"),"husky");
        //   Assert.assertEquals(response.body().path("photoUrls[0]"),"www.google.com/image1.png");
        //  Assert.assertEquals((int) response.body().path("tags[0].id"),1);
        //   Assert.assertEquals(response.body().path("tags[0].name"),"tag1");
        //    Assert.assertEquals( response.body().path("name"),"tommy");
        Assert.assertEquals(response.body().path("status"),"available");




    }
    @Given("I have invalid URL with invalid data")
    public void i_have_invalid_url_with_invalid_data() {
        URL = "https://petstore.swagger.io/v2/pet";

        payloadForPutPet = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"husky\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"www.google.com\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"tags1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";



    }

    @When("I send the PUT request with invalid header")
    public void i_send_the_put_request_with_invalid_header() {

        response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .body(payloadForPutPet).then().log().all().when()
                .put(URL);


    }

    @Then("I invalided the successful response")
    public void i_invalided_the_successful_response() {

        response.then().statusCode(200);
        response.then().log().all();
        Assert.assertEquals((int)response.body().path("id"),1);
        //  response.as(string.class);

        assert response.body().path("id").equals(1);
        Assert.assertEquals((int)response.body().path("category.id"),1);
        Assert.assertEquals(response.body().path("category.name"),"husky");
        Assert.assertEquals(response.body().path("photoUrls[0]"),"www.google.com/image1.png");
        Assert.assertEquals((int) response.body().path("tags[0].id"),1);
        Assert.assertEquals(response.body().path("tags[0].name"),"tag1");
        Assert.assertEquals( response.body().path("name"),"tommy");
        Assert.assertEquals(response.body().path("status"),"available");


    }


}
