package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
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
    @Given("Add Product Payload")
    public void add_product_payload() throws IOException {
        res = given().spec(requestSpecification()).body(dataBuild.addProductPayLoad());
    }
    @When("users call {string} with Post http request")
    public void users_call_with_post_http_request(String string) {
        response = res.when().post("/products").then().spec(resSpec).extract().response();
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        Assert.assertEquals(js.get(keyValue).toString(),expectedValue);
    }
}
