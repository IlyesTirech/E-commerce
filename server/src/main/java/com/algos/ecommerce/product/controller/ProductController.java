package com.algos.ecommerce.product.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.algos.ecommerce.product.model.Image;
import com.algos.ecommerce.product.model.Product;
import com.algos.ecommerce.product.model.ProductCategory;
import com.algos.ecommerce.product.repository.ProductCategoryRepository;
import com.algos.ecommerce.product.repository.ProductImageRepository;
import com.algos.ecommerce.product.repository.ProductRepository;
import com.algos.ecommerce.product.request.CreateCategoryRequest;
import com.algos.ecommerce.product.request.CreateProductRequest;
import com.algos.ecommerce.product.request.EditProductRequest;
import com.algos.ecommerce.product.response.ProductResponse;
import com.algos.ecommerce.product.service.ImageService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

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

    /*
        Image Section: START
    */

        // Add images to a product
        @PostMapping("{productID}/image/upload")
        public ResponseEntity<?> uploadFiles(
                @PathVariable Long productID,
                @NotNull @RequestParam("file") MultipartFile[] files)  {

            Optional<Product> optionalProduct = productRepository.findById(productID);
            if(!optionalProduct.isPresent()){
                return new ResponseEntity<>("No Product Found", HttpStatus.NOT_FOUND);
            }
            Product product = optionalProduct.get();
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    Image image = imageService.store(file);
                    product.addImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        // GET ONE image
        @GetMapping(value ="image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
        public ResponseEntity<?> getOneImage(@PathVariable String id){
            Optional<Image> image = imageRepository.findById(id);
            if(!image.isPresent()){
                return new ResponseEntity<>("No Image Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(image.get().getData(), HttpStatus.OK);
        }

    /*
        Image Section: END
    */

    /*
        Category Section: START
    */

        // CREATE category
        @PostMapping("category/create")
        public ResponseEntity<?> createCategory(
                @Valid @RequestBody CreateCategoryRequest request) throws IOException {

            ProductCategory category = new ProductCategory(request.getName());

            ProductCategory finalProduct = categoryRepository.save(category);

            return new ResponseEntity<>(finalProduct, HttpStatus.OK);
        }

        // TODO: EDIT Category

        // TODO: DELETE Category

    /*
        Category Section: END
    */

    /*
        Product Section: START
    */

    // CREATE product
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody CreateProductRequest request) throws IOException {

        // Specify category or provide a default if none suggested
        Optional<ProductCategory> categoryById = categoryRepository.findById(request.getCategoryID());
        // if category doesnt exist
        if(!categoryById.isPresent()){

            // return default category
            Long id = Long.valueOf(1);
            ProductCategory category = categoryRepository.findById(id).get();

            // Create Product
            Product product = new Product(request.getName(), request.getDescription(), request.getPrice(), category);

            Product finalProduct = productRepository.save(product);

            return new ResponseEntity<>(finalProduct, HttpStatus.OK);
        }

        // Create Product
        Product product = new Product(request.getName(), request.getDescription(), request.getPrice(), categoryById.get());

        Product finalProduct = productRepository.save(product);

        return new ResponseEntity<>(finalProduct, HttpStatus.OK);


    }
    // GET All products
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts(){
        List<ProductResponse> response = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        if(products.size() < 1){
            return new ResponseEntity<>("No Products Found", HttpStatus.NOT_FOUND);
        }
        for (Product product: products) {
            ProductResponse productResponse = new ProductResponse(product);
            response.add(productResponse);
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // GET Product
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProducts(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            return new ResponseEntity<>("No Products Found", HttpStatus.NOT_FOUND);
        }
        ProductResponse productResponse = new ProductResponse(product.get());
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


    // EDIT Product
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


    // DELETE Product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable(name = "id") Long id){
        Optional<Product> productOptional = productRepository.findById(id);
        if(!productOptional.isPresent()){
            return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
        }
        Product product = productOptional.get();
        productRepository.deleteById(product.getProductID());
        return new ResponseEntity<>("Product Deleted!", HttpStatus.OK);
    }
    /*
        Product Section: END
    */

}
