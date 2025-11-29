package resources;

public enum APIResources {
    CreateBoardAPI("/1/boards/"),
    GetBoardAPI("1/boards/{id}"),
    UpdateBoardAPI("1/boards/{id}"),
    DeleteBoardAPI("1/boards/{id}"),

    CreateLabelAPI("/1/labels"),
    GetLabelAPI("/1/labels/{id}"),
    UpdateLabelAPI("/1/labels/{id}"),
    DeleteLabelAPI("/1/labels/{id}"),

    CreateListAPI("/1/lists"),
    GetListAPI("/1/lists/{id}"),
    UpdateListAPI("/1/lists/{id}"),
    ArchiveListAPI("/1/lists/{id}/closed"),
    UnarchiveListAPI("/1/lists/{id}/closed"),

    CreateCardAPI("/1/cards"),
    GetCardAPI("/1/cards/{id}"),
    UpdateCardAPI("/1/cards/{id}"),
    DeleteCardAPI("/1/cards/{id}"),

    CreateChecklistAPI("/1/checklists"),
    GetChecklistAPI("/1/checklists/{id}"),
    UpdateChecklistAPI("/1/checklists/{id}"),
    DeleteChecklistAPI("/1/checklists/{id}");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}