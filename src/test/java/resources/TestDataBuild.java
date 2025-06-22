package resources;

import pojo.AddProduct;

public class TestDataBuild {
    public AddProduct addProductPayLoad(){
        AddProduct addProduct = new AddProduct();
        addProduct.setTitle("Laptop A");
        addProduct.setPrice(20);
        addProduct.setDescription("Laptop A 2020");
        addProduct.setCategory("Technology");
        addProduct.setImage("http://example.com");
        addProduct.setStock(12);
        return addProduct;
    }
}
