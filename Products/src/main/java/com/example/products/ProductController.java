package com.example.products;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-list")
    public String displayUserList(@RequestParam(required = false) String message,
                                  @RequestParam(required = false) String error,
                                  Model model) {
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        model.addAttribute("productList", this.productService.getAllProducts());
        return "productList";
    }

    @GetMapping("/add-product")
    public String displayAddProductPage() {
        return "addProduct";
    }

    @PostMapping("/add-product")
    public String createProduct(Product product) {
        try {
            this.productService.createProduct(product);
            return "redirect:/?message=PRODUCT_CREATED_SUCCESS";
        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_CREATION_FAILED&error=" + exception.getMessage();
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable() Long id) {
        try {
            this.productService.deleteProduct(id);
            return "redirect:/?message=PRODUCT_DELETED_SUCCESSFULLY";

        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_DELETE_FAILED&error=" + exception.getMessage();

        }
    }
}