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
import org.testng.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    RequestSpecification req;
    ResponseSpecification resSpec;
    RequestSpecification res;
    Response response;
    TestDataBuild dataBuild = new TestDataBuild();
    String productId;
    JsonPath js;
    @Given("Add Product Payload")
    public void add_product_payload() throws IOException {
        res = given().spec(requestSpecification()).body(dataBuild.addProductPayLoad());
    }
    @When("users call {string} with {string} http request")
    public void users_call_with_post_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
       resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
       if(method.equalsIgnoreCase("POST")){
           response = res.when().post(resourceAPI.getResource());
       } else if (method.equalsIgnoreCase("GET"))
           response = res.when().get(resourceAPI.getResource());
       else if (method.equalsIgnoreCase("DELETE"))
           response = res.when().delete(resourceAPI.getResource());
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(),201);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        String resp = response.asString();
        js = new JsonPath(resp);
        Assert.assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @And("verify productId created products to {string} using {string}")
    public void verifyProductIdCreatedProductsToUsing(String expectedName, String resource) throws IOException {
        String productId = getJsonPath(response,"id");
        res = given().spec(requestSpecification()).pathParam("id",productId);
        users_call_with_post_http_request(resource,"GET");
        String actualName = getJsonPath(response,"title");
        Assert.assertEquals(actualName,expectedName);
    }
}
