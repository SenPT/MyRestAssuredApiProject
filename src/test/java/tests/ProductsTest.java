package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.AddProduct;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsTest {
    @Test
    public void verifyAddNewProductReturn201AndContentTypeIsJsonFormat(){
        AddProduct addProduct = new AddProduct();
        addProduct.setTitle("Laptop A");
        addProduct.setPrice(20);
        addProduct.setDescription("Laptop A 2020");
        addProduct.setCategory("Technology");
        addProduct.setImage("http://example.com");

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://6841b714d48516d1d35ca00d.mockapi.io")
                .setContentType(ContentType.JSON).build();

        RequestSpecification res = given().spec(req).body(addProduct);
        ResponseSpecification ressponse = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();

        Response response = res.when().post("/products").then().spec(ressponse).extract().response();

        String responseString = response.asString();
        System.out.println("Response: "+responseString);
    }
    @Test
    public void verifyGetProductByIdReturnStatusCode200(){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://6841b714d48516d1d35ca00d.mockapi.io")
                .addPathParam("id","18")
                .setContentType(ContentType.JSON).build();

        RequestSpecification requestSpecification = given().spec(req);
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Response response = requestSpecification.when().get("/products/{id}").then().spec(responseSpecification).extract().response();

        String responseString = response.asString();
        System.out.println("Response get product: "+responseString);
        }
}
