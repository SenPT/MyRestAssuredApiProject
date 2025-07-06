package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AddProduct {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public float price ;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String description;
    public String category;
    public String image;
    public int stock;

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
