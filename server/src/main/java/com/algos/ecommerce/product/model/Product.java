package com.algos.ecommerce.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Category;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    private String name;
    private String description;
    private Double price;

    @OneToMany(
            mappedBy = "product",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="categoryID")
    @JsonIgnore
    private ProductCategory category;


    private int stock;


    public Product(String name, String description, Double price, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stock = 0;
    }

    public boolean addImage(Image image){
        if(!images.contains(image)){
            images.add(image);
            image.setProduct(this);
            return true;
        }
        return false;
    }

    public ArrayList<String> getImagesURL(){
       ArrayList<String> imageURLS = new ArrayList<>();

        for(int i =0; i < this.images.size(); i++){
            Image image = this.images.get(i);
            String URL = "/api/product/image/"+image.getId();
            imageURLS.add(URL);
        }
        return imageURLS;
    }

    public int addStock(){
        this.stock += 1;
        return stock;
    }

    public int removeStock(){

        if(stock > 0){
            this.stock -= 1;

        }
        return stock;
    }

}
