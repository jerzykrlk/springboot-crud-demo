package com.ensat.services;

import com.ensat.entities.Product;
import com.ensat.entities.ProductBuilder;
import com.ensat.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Product service implement.
 */
@Service
public class ProductServiceImpl implements ProductService {
     @Autowired
     private ProductRepository productRepository;

    

    @Override
    public Iterable<Product> listAllProducts() {
        return CollectionUtil.sorted(productRepository.findAll());
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productById = productRepository.findById(product.getId()).get();
        Product updated = new ProductBuilder()
                .withProduct(productById)
                .withProduct(product)
                .build();
        return productRepository.save(updated);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
