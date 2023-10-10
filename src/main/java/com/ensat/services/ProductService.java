package com.ensat.services;

import com.ensat.entities.Product;

public interface ProductService {

    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Integer id);

}
