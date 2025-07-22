package resources;

import pojo.AddProduct;

import java.util.Random;

public class TestDataBuild {
    public AddProduct addProductPayLoad(String title, double price, String desc, String category, int stock){
        AddProduct addProduct = new AddProduct();
        addProduct.setId(generateId());
        addProduct.setTitle(title);
        addProduct.setPrice(price);
        addProduct.setDescription(desc);
        addProduct.setCategory(category);
        addProduct.setStock(stock);
        return addProduct;
    }
    private String generateId() {
        Random random = new Random();
        int length = 4;
        int min = (int)Math.pow(10, length - 1);
        int max = (int)Math.pow(10, length) - 1;
        int number = random.nextInt(max - min + 1) + min;
        return "P" + number;
    }
}
