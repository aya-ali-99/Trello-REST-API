package resources;

public enum APIResources {
    CreateBoardAPI("/1/boards/"),
    GetBoardAPI("1/boards/"),
    UpdateBoardAPI("1/boards/"),
    DeleteBoardAPI("1/boards/");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
