package com.example.products.comparators;

import com.example.products.Product;

import java.util.Comparator;


public class ProductComparatorByName implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
    }

}

//    }
//    public int compareByDescription(Product o1, Product o2) {
//        return o1.getDescription().compareTo(o2.getDescription());
//
//    }
//    public int compareByCategory(Product o1, Product o2) {
//        return o1.getCategory().toString().compareTo(o2.getCategory().toString());
//
//    }
//
