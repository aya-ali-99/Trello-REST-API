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

    @Before("@CreateList")
    public static void requiredBoardForList() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.boardID == null || StepDefinition.boardDeleted) {
            System.out.println("Required Board Created for List");
            s.create_board_payload_with("List_Test_Board");
            s.user_calls_with_http_request("CreateBoardAPI", "Post");
            s.verify_exists_that_maps_to_using("board","List_Test_Board", "GetBoardAPI");
        }
    }

    @Before("@UpdateList or @ArchiveList")
    public void requiredListCreated() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.listID == null || StepDefinition.listArchived) {
            System.out.println("Required List Created");
            s.create_list_payload_with_name("Test_List");
            s.user_calls_with_http_request("CreateListAPI", "Post");
            s.verify_exists_that_maps_to_using("list","Test_List", "GetListAPI");
        }
    }
    @Before("@UnarchiveList")
    public void requiredListArchived() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.listID == null || !StepDefinition.listArchived) {
            System.out.println("Required List Created and Archived");
            s.create_list_payload_with_name("Archive_Test_List");
            s.user_calls_with_http_request("CreateListAPI", "Post");
            s.verify_exists_that_maps_to_using("list","Archive_Test_List", "GetListAPI");
            s.a_list_is_archived_using_archive_list_api();
        }
    }

    @Before("@CreateCard")
    public static void requiredListForCard() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.boardID == null || StepDefinition.boardDeleted) {
            System.out.println("Required Board Created for Card");
            s.create_board_payload_with("Card_Test_Board");
            s.user_calls_with_http_request("CreateBoardAPI", "Post");
            s.verify_exists_that_maps_to_using("board","Card_Test_Board", "GetBoardAPI");
        }
        if(StepDefinition.listID == null || StepDefinition.listArchived) {
            System.out.println("Required List Created for Card");
            s.create_list_payload_with_name("Card_Test_List");
            s.user_calls_with_http_request("CreateListAPI", "Post");
            s.verify_exists_that_maps_to_using("list","Card_Test_List", "GetListAPI");
        }
    }

    @Before("@UpdateCard or @DeleteCard")
    public void requiredCardCreated() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.cardID == null || StepDefinition.cardDeleted) {
            System.out.println("Required Card Created");
            s.create_card_payload_with_name("Test_Card");
            s.user_calls_with_http_request("CreateCardAPI", "Post");
            s.verify_exists_that_maps_to_using("card","Test_Card", "GetCardAPI");
        }
    }

    @Before("@CreateChecklist")
    public static void requiredCardForChecklist() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.boardID == null || StepDefinition.boardDeleted) {
            System.out.println("Required Board Created for Checklist");
            s.create_board_payload_with("Checklist_Test_Board");
            s.user_calls_with_http_request("CreateBoardAPI", "Post");
            s.verify_exists_that_maps_to_using("board","Checklist_Test_Board", "GetBoardAPI");
        }
        if(StepDefinition.listID == null || StepDefinition.listArchived) {
            System.out.println("Required List Created for Checklist");
            s.create_list_payload_with_name("Checklist_Test_List");
            s.user_calls_with_http_request("CreateListAPI", "Post");
            s.verify_exists_that_maps_to_using("list","Checklist_Test_List", "GetListAPI");
        }
        if(StepDefinition.cardID == null || StepDefinition.cardDeleted) {
            System.out.println("Required Card Created for Checklist");
            s.create_card_payload_with_name("Checklist_Test_Card");
            s.user_calls_with_http_request("CreateCardAPI", "Post");
            s.verify_exists_that_maps_to_using("card","Checklist_Test_Card", "GetCardAPI");
        }
    }

    @Before("@UpdateChecklist or @DeleteChecklist")
    public void requiredChecklistCreated() throws IOException {
        StepDefinition s = new StepDefinition();
        if(StepDefinition.checklistID == null || StepDefinition.checklistDeleted) {
            System.out.println("Required Checklist Created");
            s.create_checklist_payload_with_name("Test_Checklist");
            s.user_calls_with_http_request("CreateChecklistAPI", "Post");
            s.verify_exists_that_maps_to_using("checklist","Test_Checklist", "GetChecklistAPI");
        }
    }

    @AfterAll
    public static void requiredBoardDeleted() throws IOException {
        StepDefinition s = new StepDefinition();
        if(!StepDefinition.boardDeleted) {
            System.out.println("HOOK Required Board Deleted");
            s.a_board_is_created_using_create_board_api();
            s.user_calls_with_http_request("DeleteBoardAPI", "Delete");
            s.the_api_call_is_success_with_status_code(200);
        }
    }
}