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


    public Product findProductById(Long id) throws Exception {

        for (Product product : productRepository.findAll()) {
            if (product.getId().equals(id))
                return productRepository.findById(id).get();
        }
        throw new Exception("Product not found");

    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

    public void updateProductQuantity(Product product) {
        product.setQuantity(product.getQuantity() - 1);
        productRepository.saveAndFlush(product);

    }

    public Product updateProduct(Product product) throws Exception {

        for (Product currentProduct : productRepository.findAll()) {

            if (currentProduct.getId().equals(product.getId())) {
                currentProduct.setDescription(product.getDescription());
                currentProduct.setName(product.getName());
                currentProduct.setPrice(product.getPrice());
                currentProduct.setQuantity(product.getQuantity());
                currentProduct.setCategory(product.getCategory());
                return currentProduct;
            }
        }

        throw new Exception("product not found!");

    }
}
