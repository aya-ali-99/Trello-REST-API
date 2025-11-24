package resources;

public enum APIResources {
    CreateBoardAPI("/1/boards/"),
    GetBoardAPI("1/boards/{id}"),
    UpdateBoardAPI("1/boards/{id}"),
    DeleteBoardAPI("1/boards/{id}");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
