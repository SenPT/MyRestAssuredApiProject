package resources;

public enum APIResources {
    AddProductAPI("/products"),
    GetProductAPI("/products/{id}"),
    PatchProductAPI("/products/{id}"),
    DeleteProductAPI("/products/{id}");
    private String resource;
    APIResources(String resource){
        this.resource=resource;
    }
    public String getResource(){
        return resource;
    }
}
