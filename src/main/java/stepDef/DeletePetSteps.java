package stepDef;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class DeletePetSteps {

    Response response;

    @Given("I have pet")
    public void iHavePet() {

        String url = "https://petstore.swagger.io/v2/pet/1";

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when().log().all()
                .get(url);

        response.then().log().all();


    }

    @When("I send the delete request")
    public void iSendTheDeleteRequest() {

        response.then().statusCode(200);


    }

    @Then("pet is deleted")
    public void pet_is_deleted() {
        response.then().statusCode(200);

        if (equals("invalid")) {
            Assert.assertTrue(response.body().path(""));
        } else {

            List jsonList = response.body().path("");
            Map json = (Map) jsonList.get(0);

            Assert.assertTrue(jsonList.size() >= 1);
            Assert.assertNotNull(json.get("id"));
            Assert.assertNotNull(json.get("name"));
            Assert.assertNotNull(json.get("photoUrls"));
            Assert.assertNotNull(json.get("tags"));
            Assert.assertNotNull(json.get("category"));
            Assert.assertNotNull(json.get("status"));


        }
    }
}
