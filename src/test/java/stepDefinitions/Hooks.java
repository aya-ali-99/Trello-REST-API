package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@CreateLabel")
    public static void requiredBoardCreated() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.boardID == null || StepDefinition.boardDeleted) {
            System.out.println("Required Board Created");
            s.create_board_payload_with("Peep_Peep_Test_Board");
            s.user_calls_with_http_request("CreateBoardAPI", "Post");
            s.verify_exists_that_maps_to_using("board","Peep_Peep_Test_Board", "GetBoardAPI");
        }
    }

    @Before("@UpdateLabel or @DeleteLabel")
    public void requiredLabelCreated() throws IOException {

        StepDefinition s = new StepDefinition();

        if(StepDefinition.labelID == null || StepDefinition.labelDeleted) {
            System.out.println("Required Label Created");
            s.create_label_payload_with_name_and_color("Test_Label", "blue");
            s.user_calls_with_http_request("CreateLabelAPI", "Post");
            s.verify_exists_that_maps_to_using("label","Test_Label", "GetLabelAPI");
        }
    }


    @AfterAll
    public static void requiredBoardDeleted() throws IOException {

        StepDefinition s = new StepDefinition();

        if(!StepDefinition.labelDeleted)
        {
            System.out.println("HOOK Required Label Deleted");
            s.a_label_is_created_using_create_label_api();
            s.user_calls_with_http_request("DeleteLabelAPI", "Delete");
            s.the_api_call_is_success_with_status_code(200);
        }
        if(!StepDefinition.boardDeleted) {
            System.out.println("HOOK Required Board Deleted");
            s.a_board_is_created_using_create_board_api();
            s.user_calls_with_http_request("DeleteBoardAPI", "Delete");
            s.the_api_call_is_success_with_status_code(200);
        }
    }


}
