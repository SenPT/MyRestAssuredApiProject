package testRunners;

import io.cucumber.java.Before;
import stepDefinitions.StepDefinition;

import java.io.IOException;

public class Hooks {
    @Before("@DeleteProduct")
    public void beforeScenario() throws IOException {
        //execute this code only when place id is null
        //write a code that will give you place id
        StepDefinition m = new StepDefinition();
        if(StepDefinition.productId ==null){
            m.add_product_payload_with("Organic Cotton T-Shirt",10,"Eco-friendly unisex t-shirt made from 100% organic cotton");
            m.users_call_with_post_http_request("AddProductAPI","POST");
            m.verifyProductIdCreatedProductsToUsing("Organic Cotton T-Shirt","GetProductAPI");
        }
    }
}
