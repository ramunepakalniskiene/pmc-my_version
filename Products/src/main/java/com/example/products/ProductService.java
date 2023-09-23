package com.example.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


    public Product createProduct(Product product) throws Exception {
        if (product.getName().isEmpty() || product.getPrice() == 0 ||
                product.getCategory().toString() == null)
            throw new Exception("All lines should be filled in, please re-check");
        else {

            product.setQuantity(product.getInitial_quantity());
            productRepository.saveAndFlush(product);
        }
        return product;
    }


    private void findProduct(Long id) {
        productRepository.findById(id);
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }
}
