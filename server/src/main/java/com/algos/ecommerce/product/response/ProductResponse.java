package com.algos.ecommerce.product.response;

import com.algos.ecommerce.product.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ProductResponse {

    Long id;
    String name;
    String description;
    Double price;
    String category;
    ArrayList<String> images;

    public ProductResponse(Product product) {
        this.id = product.getProductID();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory().getCategoryName();
        this.images = product.getImagesURL();
    }
}
