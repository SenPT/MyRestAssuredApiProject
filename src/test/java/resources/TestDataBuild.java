package resources;

import pojo.AddProduct;

import java.util.Random;

public class TestDataBuild {
    public AddProduct addProductPayLoad(String title, Double price, String desc, String category, Integer stock) {
        AddProduct addProduct = new AddProduct();
        addProduct.setId(generateId());
        addProduct.setTitle(title);
        addProduct.setPrice(price);
        addProduct.setDescription(desc);
        addProduct.setCategory(category);
        addProduct.setStock(stock);
        return addProduct;
    }
    public AddProduct updateProductPayLoad(String title, Double price, String desc, String category, Integer stock){
        AddProduct addProduct = new AddProduct();
        if (title != null) addProduct.setTitle(title);
        if (price != null) addProduct.setPrice(price);
        if (desc != null) addProduct.setDescription(desc);
        if (category != null) addProduct.setCategory(category);
        if (stock != null) addProduct.setStock(stock);
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
