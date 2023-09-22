package com.example.products;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {

    @Id
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private Category category;



}
