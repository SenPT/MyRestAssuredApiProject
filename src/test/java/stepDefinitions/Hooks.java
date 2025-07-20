package stepDefinitions;

import io.cucumber.java.Before;
import stepDefinitions.StepDefinition;

import java.io.IOException;

public class Hooks {
    @Before("@DeleteProduct")
    public void beforeScenario() throws IOException {
        StepDefinition m = new StepDefinition();
        if(StepDefinition.productId ==null){
            m.add_product_payload_with_and_and_and_and("Organic Cotton T-Shirt",10.9,"Eco-friendly unisex t-shirt made from 100% organic cotton","Clothes",9);
            m.users_call_with_post_http_request("AddProductAPI","POST");
            m.verifyProductIdCreatedProductsToUsing("Organic Cotton T-Shirt","GetProductAPI");
        }
    }
}
