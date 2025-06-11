package com.saumya.ecom.proj.controller;


import com.saumya.ecom.proj.model.Product;
import com.saumya.ecom.proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product p = service.getProductById(id);

        if (p != null)
            return ResponseEntity.ok(p);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public  ResponseEntity<?> addProduct(@RequestPart Product product,
                                         @RequestPart MultipartFile imageFile) {
        try {
            System.out.println(product);
            Product p1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(p1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductID(@PathVariable int productId) {

        Product p = service.getProductById(productId);
        byte[] imageFile = p.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(p.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{productId}")
    public  ResponseEntity<String> updateProduct(@PathVariable int productId,
                                            @RequestPart Product product,
                                            @RequestPart MultipartFile imageFile) {

        try {
            Product updated = service.updateProduct(productId, product, imageFile);
            if (updated != null) {
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            }

            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Update failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        Product p = service.getProductById(productId);

        if(p != null) {
            service.deleteProductById(productId);
            return ResponseEntity.ok("deleted");
        }
        return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> search(@RequestParam String keyword) {
        List<Product> products = service.searchProducts(keyword);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
