package stepDef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class GetPetByStatus {

    Response response;

    @When("^I send get request to get all pet which has status (|available|pending|sold|)$")
    public void iSendGetRequestToGetAllPetWhichHasValidStatus(String status) {

        String url = "https://petstore.swagger.io/v2/pet/findByStatus";

        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status",status)
                .when().log().all()
                .get(url);

        response.then().log().all();
    }


    @When("^I send get request to get  pet which has (.*) status$")
    public void iSendGetRequestToGetPetWhichHasInvalidStatus(String status) {
        String url = "https://petstore.swagger.io/v2/pet/findByStatus";

        response= RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("status",status)
                .when().log().all()
                .get(url);

        response.then().log().all();

    }

    @Then("I validate the successful response get by status")
    public void iValidateTheSuccessfulResponseGetByStatus() {
        response.then().statusCode(200);

    }

    @Then("^I validate the successful response for get by status (.*)$")
    public void iValidateTheSuccessfulResponseGetByInvalidStatus(String status) {
        response.then().statusCode(200);

        if(status.equals("invalid")){
            Assert.assertTrue(response.body().path(""));
        }else {

            List jsonList = response.body().path("");
            Map json = (Map) jsonList.get(0);

            Assert.assertTrue(jsonList.size()>=1);
            Assert.assertNotNull(json.get("id"));
            Assert.assertNotNull(json.get("name"));
            Assert.assertNotNull(json.get("photoUrls"));
            Assert.assertNotNull(json.get("tags"));
            Assert.assertNotNull(json.get("category"));
            Assert.assertNotNull(json.get("status"));


        }

    }



}
