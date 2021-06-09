package com.algos.ecommerce.product.request;

import com.algos.ecommerce.product.model.Image;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateProductRequest {

    private String name;
    private String description;
    private Double price;
    private Long categoryID;
    private int[] imageIDs;
}
