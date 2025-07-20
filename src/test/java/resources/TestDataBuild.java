package resources;

import pojo.AddProduct;

public class TestDataBuild {
    public AddProduct addProductPayLoad(String title, double price, String desc, String category, int stock){
        AddProduct addProduct = new AddProduct();
        addProduct.setTitle(title);
        addProduct.setPrice(price);
        addProduct.setDescription(desc);
        addProduct.setCategory(category);
        addProduct.setStock(stock);
        return addProduct;
    }
}
