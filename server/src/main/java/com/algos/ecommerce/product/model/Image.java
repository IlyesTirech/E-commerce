package com.algos.ecommerce.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImageID;

    private String imageName;

    private byte[] imageData;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="product_productID")
    @JsonIgnore
    private Product product;

    public Image(String imageName, byte[] imageData) {
        this.imageName = imageName;
        this.imageData = imageData;
    }
}