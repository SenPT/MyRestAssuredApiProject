package resources;

import pojo.AddProduct;

public class TestDataBuild {
    public AddProduct addProductPayLoad(){
        AddProduct addProduct = new AddProduct();
        addProduct.setTitle("Laptop C");
        addProduct.setPrice(16);
        addProduct.setDescription("Laptop C 2025");
        addProduct.setCategory("Technology");
        addProduct.setImage("http://example.com");
        addProduct.setStock(17);
        return addProduct;
    }
}
