package resources;

public enum APIResources {
    // Board
    CreateBoardAPI("/1/boards/"),
    GetBoardAPI("1/boards/{id}"),
    UpdateBoardAPI("1/boards/{id}"),
    DeleteBoardAPI("1/boards/{id}"),
    // Label
    CreateLabelAPI("/1/labels"),
    GetLabelAPI("/1/labels/{id}"),
    UpdateLabelAPI("/1/labels/{id}"),
    DeleteLabelAPI("/1/labels/{id}");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
