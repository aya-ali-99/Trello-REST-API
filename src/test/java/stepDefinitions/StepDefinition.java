package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefinition extends Utils {

    RequestSpecification reqSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String boardID;


    @Given("Create Board Payload with {string}")
    public void create_board_payload_with(String boardName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .queryParam("name", boardName);

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
        APIResources resourceAPI = APIResources.valueOf(resource);

        if (httpMethod.equalsIgnoreCase("Post")) {
            response = reqSpec
                    .when()
                    .post(resourceAPI.getResource());
        }
        else if (httpMethod.equalsIgnoreCase("Get")) {
            response = reqSpec
                    .when()
                    .get(resourceAPI.getResource());
        }
        else if (httpMethod.equalsIgnoreCase("Put")) {
            response = reqSpec
                    .when()
                    .put(resourceAPI.getResource());
        }
        else if (httpMethod.equalsIgnoreCase("Delete")) {
            response = reqSpec
                    .when()
                    .delete(resourceAPI.getResource());
        }

    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int int1) {
        assertEquals(int1, response.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        assertEquals(expectedValue, getJsonPath(response, key));
    }

    @Then("verify board is created that maps to {string} using {string}")
    public void verify_board_is_created_that_maps_to_using(String expectedName, String resource) throws IOException {
        boardID = getJsonPath(response, "id");
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", boardID);

        user_calls_with_http_request(resource, "Get");

        String actualBoardName = getJsonPath(response, "name");
        assertEquals(expectedName, actualBoardName);
    }

    @Given("a board is created using CreateBoardAPI")
    public void a_board_is_created_using_create_board_api() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", boardID);
    }

    @Then("verify the board with ID no longer exists via {string} with expected status {int}")
    public void verify_the_board_with_id_no_longer_exists_via_with_expected_status(String resource, int int1) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", boardID);

        user_calls_with_http_request(resource, "Get");

        assertEquals(int1, response.getStatusCode());
    }

}
