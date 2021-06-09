package com.algos.ecommerce.product.request;

import lombok.Getter;

@Getter
public class EditProductRequest {

    private String name;
    private String description;
    private Double price;

}
