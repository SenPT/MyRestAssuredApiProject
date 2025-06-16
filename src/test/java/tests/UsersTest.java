package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsersTest {
    @Test
    public void verifyGetUsersSuccessfully(){
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("http://localhost:3000").build();
        String responseUsers = given().spec(req).when().get("/Users").then().extract().response().asString();
        System.out.println("List of users: "+responseUsers);
    }
}
