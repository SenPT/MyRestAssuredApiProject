package resources;

import pojo.AddProduct;

public class TestDataBuild {
    public AddProduct addProductPayLoad(String title, int price, String desc){
        AddProduct addProduct = new AddProduct();
        addProduct.setTitle(title);
        addProduct.setPrice(price);
        addProduct.setDescription(desc);
        addProduct.setCategory("Unknown");
        addProduct.setImage("http://example.com");
        addProduct.setStock(17);
        return addProduct;
    }
}
