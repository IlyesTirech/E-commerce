package com.algos.ecommerce.product.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.algos.ecommerce.product.model.Image;
import com.algos.ecommerce.product.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageService {

    @Autowired
    private ProductImageRepository imageRepo;

    public List<Image> storeAll(MultipartFile[] files) throws IOException {
        List<Image> images = new ArrayList<>();
        for(int i =0; i < files.length; i++){
            Image image = store(files[i]);
            images.add(image);
        }
        return images;
    }

    private Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image FileDB = new Image(fileName, file.getBytes());

        return imageRepo.save(FileDB);
    }

}