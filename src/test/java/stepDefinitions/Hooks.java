package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@UpdateBoard or @DeleteBoard")
    public void requiredBoardCreated() throws IOException {

        StepDefinition s = new StepDefinition();
        if(s.boardID == null) {
            s.create_board_payload_with("RestTestBoard");
            s.user_calls_with_http_request("CreateBoardAPI", "Post");
            s.verify_board_exists_that_maps_to_using("RestTestBoard", "GetBoardAPI");
        }
    }



    @After("@UpdateBoard or @DeleteBoard")
    public void requiredBoardDeleted() throws IOException {
        StepDefinition s = new StepDefinition();
        s.user_calls_with_http_request("DeleteBoardAPI", "Delete");
    }


}
