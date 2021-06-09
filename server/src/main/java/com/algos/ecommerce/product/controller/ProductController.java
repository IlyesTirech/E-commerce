package com.algos.ecommerce.product.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.algos.ecommerce.product.model.Image;
import com.algos.ecommerce.product.model.Product;
import com.algos.ecommerce.product.model.ProductCategory;
import com.algos.ecommerce.product.repository.ProductCategoryRepository;
import com.algos.ecommerce.product.repository.ProductImageRepository;
import com.algos.ecommerce.product.repository.ProductRepository;
import com.algos.ecommerce.product.request.CreateProductRequest;
import com.algos.ecommerce.product.request.EditProductRequest;
import com.algos.ecommerce.product.request.ImageTemplateRequest;
import com.algos.ecommerce.product.service.ImageService;
import jdk.jfr.Category;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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


    @PostMapping("/image/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        try {
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    imageService.store(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return new ResponseEntity<>(files, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>("Images Not Saved", HttpStatus.OK );
        }
    }


    // TODO: CREATE product
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(
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

//      // Maps through each image and assigns it to a product
//        Image[] images = request.getImages();
//        for(int i =0; i < images.length; i++){
//            String imageName = images[i].getImageName();
//            Image newImage = new Image(imageName, images[i].getImageData());
//            Image savedImage = imageRepository.save(newImage);
//            product.addImage(savedImage);
//        }
        // Save Product
        Product finalProduct = productRepository.save(product);

        return new ResponseEntity<>(finalProduct, HttpStatus.OK);
    }


    // TODO: GET all products
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts(){
        List<Product> product = productRepository.findAll();
        if(product.size() < 1){
            return new ResponseEntity<>("No Products Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // TODO: GET one
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProducts(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            return new ResponseEntity<>("No Products Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // TODO: EDIT one
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @RequestBody EditProductRequest request){
        Optional<Product> productOptional = productRepository.findById(id);
        boolean fieldsUpdated = false;
        if(!productOptional.isPresent()){
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
        Product product = productOptional.get();
        // If name is provided then update the name
        if(request.getName() != null && request.getName().length() > 1){
            product.setName(request.getName());
            fieldsUpdated = true;
        }
        // If description is provided then update the name
        if(request.getDescription() != null && request.getDescription().length() > 1){
            product.setDescription(request.getDescription());
            fieldsUpdated = true;
        }
        // If price is provided then update the name
        if(request.getPrice() != null){
            product.setPrice(request.getPrice());
            fieldsUpdated = true;
        }
        if(!fieldsUpdated){
            return new ResponseEntity<>("Nothing was updated", HttpStatus.OK);
        }

        productRepository.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    // TODO: DELETE one


}
