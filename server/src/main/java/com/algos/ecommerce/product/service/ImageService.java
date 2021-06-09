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
    private ProductImageRepository fileDBRepository;

    public Image store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image FileDB = new Image(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    public Image getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<Image> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}