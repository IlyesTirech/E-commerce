package com.algos.ecommerce.product.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;


    private Double price;


    private Long categoryID;

}
