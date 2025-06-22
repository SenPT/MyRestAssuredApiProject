package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddProduct;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsTest {
    @Test
    public void verifyGetProductByIdReturnStatusCode200(){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("http://localhost:3000")
                .addPathParam("id","18")
                .setContentType(ContentType.JSON).build();

        RequestSpecification requestSpecification = given().spec(req);
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Response response = requestSpecification.when().get("/products/{id}").then().spec(responseSpecification).extract().response();

        String responseString = response.asString();
        System.out.println("Response get product: "+responseString);
        }
        @Test
    public void verifyUpdateAProductSuccessfully(){
        AddProduct product = new AddProduct();
        product.setTitle("Laptop B");

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("http://localhost:3000")
                .addPathParam("id","24")
                .setContentType(ContentType.JSON).build();

        RequestSpecification requestSpecification = given().spec(req).body(product);
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

         AddProduct addProduct= requestSpecification.when().put("/products/{id}").then().spec(responseSpecification).extract().response().as(AddProduct.class);
         String updatedTitle = addProduct.getTitle();
         Assert.assertEquals(updatedTitle,"Laptop B","Title doesn't update as expected");
        }
    @Test
    public void verifyDeleteAProductSuccessfully(){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("http://localhost:3000")
                .addPathParam("id","23").build();

        RequestSpecification requestSpecification = given().spec(req);
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        requestSpecification.when().delete("/products/{id}").then().spec(responseSpecification).extract().response();
    }
}
