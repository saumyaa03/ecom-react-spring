package com.saumya.ecom.proj.service;

import com.saumya.ecom.proj.model.Product;
import com.saumya.ecom.proj.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product); // save returns the product

    }

    public Product updateProduct(int productId, Product product, MultipartFile imageFile) throws IOException {
        Product existing = repo.findById(productId).orElse(null);

        if (existing == null) {
            return null; // controller will return 404
        }

        // Update only the necessary fields
        existing.setName(product.getName());
        existing.setBrand(product.getBrand());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setProductAvailable(product.isProductAvailable());
        existing.setStockQuantity(product.getStockQuantity());
        existing.setReleaseDate(product.getReleaseDate());

        // Update image
        existing.setImageData(imageFile.getBytes());
        existing.setImageName(imageFile.getOriginalFilename());
        existing.setImageType(imageFile.getContentType());

        return repo.save(existing); // ✅ save and return updated product
    }

    public void deleteProductById(int productId) {
        repo.deleteById(productId);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
