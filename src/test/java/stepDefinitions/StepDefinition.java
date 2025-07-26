package stepDefinitions;

import io.cucumber.datatable.DataTable;
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
import java.util.List;
import java.util.Map;

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
    @Given("Update Product Payload with fields and values:")
    public void updateProductPayloadWithFieldsAndValues(DataTable dataTable) throws IOException {
        List<Map<String,String>> data = dataTable.asMaps();
        String title = "";
        String price = "";
        String desc = "";
        String category ="";
        String stock = "";
        for (Map<String, String> row : data) {
            String field = row.get("fields");
            String value = row.get("values");
            if (field != null && !field.isEmpty() && value != null && !value.isEmpty()) {
                switch (field.trim().toLowerCase()) {
                    case "title":
                        title = value;
                        break;
                    case "price":
                        price = value;
                        break;
                    case "desc":
                        desc = value;
                        break;
                    case "category":
                        category = value;
                        break;
                    case "stock":
                        stock = value;
                        break;
                    default:
                        break;
                }
            }
        }

        if (title.equals("")) {
            title = null;
        }
        if (desc.equals("")) {
            desc = null;
        }
        if (category.equals("")) {
            category = null;
        }
        if (stock.equals("")) {
            stock = null;
        }
        if (price.equals("")) {
            price = null;
        }
         Double parsedPrice = null;
        if (price != null){
            parsedPrice = Double.parseDouble(price);
        }
        Integer parsedStock = null;
        if(stock != null){
           parsedStock = Integer.parseInt(stock);
        }
        res = given().spec(requestSpecification()).body(dataBuild.updateProductPayLoad(title,parsedPrice,desc,category,parsedStock));
    }

    @When("users call {string} with {string} http request")
    public void users_call_with_post_http_request(String resource, String method) throws IOException {
        APIResources resourceAPI = APIResources.valueOf(resource);
       resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
       switch (method.toUpperCase()){
           case "POST":
               response = res.when().post(resourceAPI.getResource());
               productId = getJsonPath(response,"id");
               break;
           case "GET":
               response = res.when().get(resourceAPI.getResource());
               break;
           case "PATCH":
               response = res.pathParam("id",productId).when().patch(resourceAPI.getResource());
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

    @And("verify productId created products to {string} with value {string} using {string}")
    public void verifyProductIdCreatedProductsToWithValueUsing(String fieldName, String expectedName, String resource)  throws IOException{
        productId = getJsonPath(response,"id");
        res = given().spec(requestSpecification()).pathParam("id",productId);
        users_call_with_post_http_request(resource,"GET");
        String actualName = getJsonPath(response,fieldName);
        Assert.assertEquals(actualName,expectedName);
    }
}
