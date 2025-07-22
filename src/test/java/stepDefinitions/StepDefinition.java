package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
public class StepDefinition extends Utils {
    ResponseSpecification resSpec;
    RequestSpecification res;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild();
    public static String productId;
    JsonPath js;
    @Given("Add Product Payload with {string} and {double} and {string} and {string} and {int}")
    public void add_product_payload_with_and_and_and_and(String title, Double price, String desc, String category, Integer stock) throws IOException {
        res = given().spec(requestSpecification()).body(dataBuild.addProductPayLoad(title,price,desc,category,stock));
    }
    @When("users call {string} with {string} http request")
    public void users_call_with_post_http_request(String resource, String method) throws IOException {
        APIResources resourceAPI = APIResources.valueOf(resource);
       resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
       switch (method.toUpperCase()){
           case "POST":
               response = res.when().post(resourceAPI.getResource());
               break;
           case "GET":
               response = res.when().get(resourceAPI.getResource());
               break;
           case "DELETE":
               res = given().spec(requestSpecification()).pathParam("id", productId);
               response = res.when().delete(resourceAPI.getResource());
               break;
           default:
               throw new IllegalArgumentException("Unsupported HTTP method: " + method);
       }
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        String resp = response.asString();
        js = new JsonPath(resp);
        Assert.assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @And("verify productId created products to {string} using {string}")
    public void verifyProductIdCreatedProductsToUsing(String expectedName, String resource) throws IOException {
        productId = getJsonPath(response,"id");
        res = given().spec(requestSpecification()).pathParam("id",productId);
        users_call_with_post_http_request(resource,"GET");
        String actualName = getJsonPath(response,"title");
        Assert.assertEquals(actualName,expectedName);
    }
}
