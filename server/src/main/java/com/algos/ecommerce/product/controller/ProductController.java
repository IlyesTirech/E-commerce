package com.algos.ecommerce.product.controller;

import com.algos.ecommerce.product.model.Image;
import com.algos.ecommerce.product.model.Product;
import com.algos.ecommerce.product.model.ProductCategory;
import com.algos.ecommerce.product.repository.ProductCategoryRepository;
import com.algos.ecommerce.product.repository.ProductImageRepository;
import com.algos.ecommerce.product.repository.ProductRepository;
import com.algos.ecommerce.product.request.CreateProductRequest;
import com.algos.ecommerce.product.service.ImageService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 36000)
@RequestMapping("api/product")
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository categoryRepository;
    @Autowired
    ProductImageRepository imageRepository;
    @Autowired
    ImageService imageService;


    // TODO: CREATE product
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(
//            @RequestParam("files") MultipartFile[] files,
            @Valid @RequestBody CreateProductRequest request) throws IOException {


        // Specify category or provide a default if none suggested
        Optional<ProductCategory> categoryById = categoryRepository.findById(request.getCategoryID());

        // if category doesnt exist
        if(!categoryById.isPresent()){
            // return default category
        }

        // Create Product
        Product product = new Product(request.getName(), request.getDescription(), request.getPrice());

        // Create Image files from data or provide a default placeholder image
        // Assign images to product
//        List<Image> images =  imageService.storeAll(files);
//        // Maps through each image and assigns it to a product
//        for(int i =0; i < images.size(); i++){
//            product.addImage(images.get(i));
//        }
        // Save Product
        productRepository.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // TODO: GET all products
    // TODO: GET one
    // TODO: EDIT one
    // TODO: DELETE one


}
