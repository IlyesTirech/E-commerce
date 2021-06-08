package com.algos.ecommerce.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
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


    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public boolean addImage(Image image){
        if(!images.contains(image)){
            images.add(image);
            image.setProduct(this);
            return true;
        }
        return false;
    }

}
