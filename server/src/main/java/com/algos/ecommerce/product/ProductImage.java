package com.algos.ecommerce.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageID;

    private String imageName;

    private byte[] imageData;

    public ProductImage(String imageName, byte[] imageData) {


        this.imageName = imageName;
        this.imageData = imageData;
    }
}