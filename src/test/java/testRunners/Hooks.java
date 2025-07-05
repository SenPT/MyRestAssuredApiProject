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
            m.add_product_payload();
            m.users_call_with_post_http_request("","");
            m.verifyProductIdCreatedProductsToUsing("","");
        }
    }
}
