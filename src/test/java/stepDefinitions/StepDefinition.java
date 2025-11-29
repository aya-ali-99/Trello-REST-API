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
    static String labelID;
    static String listID;
    static String cardID;
    static String checklistID;

    static boolean boardDeleted = false;
    static boolean labelDeleted = false;
    static boolean listArchived = false;
    static boolean cardDeleted = false;
    static boolean checklistDeleted = false;


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
        } else if (httpMethod.equalsIgnoreCase("Get")) {
            response = reqSpec
                    .when()
                    .get(resourceAPI.getResource());
        } else if (httpMethod.equalsIgnoreCase("Put")) {
            response = reqSpec
                    .when()
                    .put(resourceAPI.getResource());
        } else if (httpMethod.equalsIgnoreCase("Delete")) {
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

    @Then("verify {string} exists that maps to {string} using {string}")
    public void verify_exists_that_maps_to_using(String objectName, String expectedName, String resource) throws IOException {
        if (objectName.equalsIgnoreCase("board")) {
            boardDeleted = false;
            boardID = getJsonPath(response, "id");
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", boardID);
        } else if (objectName.equalsIgnoreCase("label")) {
            labelDeleted = false;
            labelID = getJsonPath(response, "id");
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", labelID);
        } else if (objectName.equalsIgnoreCase("list")) {
            listArchived = false;
            listID = getJsonPath(response, "id");
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", listID);
        } else if (objectName.equalsIgnoreCase("card")) {
            cardDeleted = false;
            cardID = getJsonPath(response, "id");
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", cardID);
        } else if (objectName.equalsIgnoreCase("checklist")) {
            checklistDeleted = false;
            checklistID = getJsonPath(response, "id");
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", checklistID);
        }
        user_calls_with_http_request(resource, "Get");
        String actualName = getJsonPath(response, "name");
        assertEquals(expectedName, actualName);
    }

    @Given("a board is created using CreateBoardAPI")
    public void a_board_is_created_using_create_board_api() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", boardID);
    }

    @Then("verify the {string} with ID no longer exists via {string} with expected status {int}")
    public void verify_the_with_id_no_longer_exists_via_with_expected_status(String objectName, String resource, int int1) throws IOException {
        if (objectName.equalsIgnoreCase("board")) {
            boardDeleted = true;
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", boardID);
        } else if (objectName.equalsIgnoreCase("label")) {
            labelDeleted = true;
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", labelID);
        } else if (objectName.equalsIgnoreCase("card")) {
            cardDeleted = true;
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", cardID);
        } else if (objectName.equalsIgnoreCase("checklist")) {
            checklistDeleted = true;
            reqSpec = given()
                    .spec(requestSpecification())
                    .pathParam("id", checklistID);
        }

        user_calls_with_http_request(resource, "Get");

        System.out.println(boardDeleted);
        System.out.println(labelDeleted);
        System.out.println(cardDeleted);
        System.out.println(checklistDeleted);
        assertEquals(int1, response.getStatusCode());
    }

    @Given("a board is created using CreateBoardAPI and new board name {string}")
    public void a_board_is_created_using_create_board_api_and_new_board_name(String newName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", boardID)
                .queryParam("name", newName);
    }

    @Given("create label payload with name {string} and color {string}")
    public void create_label_payload_with_name_and_color(String labelName, String labelColor) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .queryParam("name", labelName)
                .queryParam("color", labelColor)
                .queryParam("idBoard", boardID);
    }

    @Given("a label is created using CreateLabelAPI and new label name {string}")
    public void a_label_is_created_using_create_label_api_and_new_label_name(String newName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", labelID)
                .queryParam("name", newName);
    }

    @Given("a label is created using CreateLabelAPI")
    public void a_label_is_created_using_create_label_api() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", labelID);
    }

    @Given("create list payload with name {string}")
    public void create_list_payload_with_name(String listName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .queryParam("name", listName)
                .queryParam("idBoard", boardID);
    }

    @Given("a list is created using CreateListAPI and new list name {string}")
    public void a_list_is_created_using_create_list_api_and_new_list_name(String newName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", listID)
                .queryParam("name", newName);
    }

    @Given("prepare archive request for list")
    public void prepare_archive_request_for_list() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", listID)
                .queryParam("value", "true");
    }

    @Given("prepare unarchive request for list")
    public void prepare_unarchive_request_for_list() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", listID)
                .queryParam("value", "false");
    }

    @Then("verify the {string} is archived via {string}")
    public void verify_the_is_archived_via(String objectName, String resource) throws IOException {
        listArchived = true;
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", listID);
        user_calls_with_http_request(resource, "Get");
        String closedStatus = getJsonPath(response, "closed");
        assertEquals("true", closedStatus);
    }

    @Then("verify the {string} is unarchived via {string}")
    public void verify_the_is_unarchived_via(String objectName, String resource) throws IOException {
        listArchived = false;
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", listID);
        user_calls_with_http_request(resource, "Get");
        String closedStatus = getJsonPath(response, "closed");
        assertEquals("false", closedStatus);
    }

    @Given("a list is archived using ArchiveListAPI")
    public void a_list_is_archived_using_archive_list_api() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", listID)
                .queryParam("value", "true");
        user_calls_with_http_request("ArchiveListAPI", "Put");
        listArchived = true;
    }

    @Given("create card payload with name {string}")
    public void create_card_payload_with_name(String cardName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .queryParam("name", cardName)
                .queryParam("idList", listID);
    }

    @Given("a card is created using CreateCardAPI and new card name {string}")
    public void a_card_is_created_using_create_card_api_and_new_card_name(String newName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", cardID)
                .queryParam("name", newName);
    }

    @Given("a card is created using CreateCardAPI")
    public void a_card_is_created_using_create_card_api() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", cardID);
    }

    @Given("create checklist payload with name {string}")
    public void create_checklist_payload_with_name(String checklistName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .queryParam("name", checklistName)
                .queryParam("idCard", cardID);
    }

    @Given("a checklist is created using CreateChecklistAPI and new checklist name {string}")
    public void a_checklist_is_created_using_create_checklist_api_and_new_checklist_name(String newName) throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", checklistID)
                .queryParam("name", newName);
    }

    @Given("a checklist is created using CreateChecklistAPI")
    public void a_checklist_is_created_using_create_checklist_api() throws IOException {
        reqSpec = given()
                .spec(requestSpecification())
                .pathParam("id", checklistID);
    }

}