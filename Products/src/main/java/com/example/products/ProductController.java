package com.example.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String displayUserList(@RequestParam(required = false) String message,
                                  @RequestParam(required = false) String error,
                                  Model model) {
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        model.addAttribute("productList", productService.getAllProducts());
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
            productService.deleteProduct(id);
            return "redirect:/?message=PRODUCT_DELETED_SUCCESSFULLY";

        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_DELETE_FAILED&error=" + exception.getMessage();

        }
    }

    @PostMapping("/sell-product")
    public String sellProduct(Product product) {
        try {
            this.productService.updateProductQuantity(product);
            return "redirect:/?message=PRODUCT_UPDATED_SUCCESS";
        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_UPDATING_FAILED&error=" + exception.getMessage();
        }

    }


    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable() Long id, Model model) {
        try {
            Product product = productService.findProductById(id);
            model.addAttribute("productItem", product);
            return "editProduct";
        } catch (Exception exception) {

            return "redirect:/?message=PRODUCT_EDIT_FAILED&error=" + exception.getMessage();
        }
    }

    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id, Product product) {
        try {
            productService.findProductById(id);
            product.setId(id);
            productService.updateProduct(product);
            return "redirect:/?message =PRODUCT_EDITED_SUCCESSFULLY";
        } catch (Exception exception) {
            return "redirect:/?message=PRODUCT_EDIT_FAILED&error=" + exception.getMessage();
        }
    }
}