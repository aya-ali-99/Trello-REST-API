package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@UpdateBoard or @DeleteBoard or @Label")
    public void requiredBoardCreated() throws IOException {

        StepDefinition s = new StepDefinition();
        if(StepDefinition.boardID == null) {
            s.create_board_payload_with("Test_Board");
            s.user_calls_with_http_request("CreateBoardAPI", "Post");
            s.verify_exists_that_maps_to_using("board","Test_Board", "GetBoardAPI");
        }
    }

    @Before("@UpdateLabel or @DeleteLabel")
    public void requiredLabelCreated() throws IOException {

        StepDefinition s = new StepDefinition();

        if(StepDefinition.labelID == null) {
            s.create_label_payload_with_name_and_color("Test_Label", "blue");
            s.user_calls_with_http_request("CreateLabelAPI", "Post");
            s.verify_exists_that_maps_to_using("label","Test_Label", "GetLabelAPI");
        }
    }


    @AfterAll
    public static void requiredBoardDeleted() throws IOException {
        StepDefinition s = new StepDefinition();

        if(StepDefinition.labelID != "Deleted" || StepDefinition.labelID != null)
        {
            s.a_label_is_created_using_create_label_api();
            s.user_calls_with_http_request("DeleteLabelAPI", "Delete");
        }
        if(StepDefinition.boardID != "Deleted"  || StepDefinition.boardID != null) {
            s.a_board_is_created_using_create_board_api();
            s.user_calls_with_http_request("DeleteBoardAPI", "Delete");
        }
    }


}
