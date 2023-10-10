package com.ensat.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ensat.entities.Product;
import com.ensat.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#listAllProducts()}
     */
    @Test
    void testListAllProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        Iterable<Product> actualListAllProductsResult = productServiceImpl.listAllProducts();

        // Assert
        assertTrue(((Collection<Product>) actualListAllProductsResult).isEmpty());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#listAllProducts()}
     */
    @Test
    void testListAllProducts2() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        Iterable<Product> actualListAllProductsResult = productServiceImpl.listAllProducts();

        // Assert
        assertEquals(1, ((Collection<Product>) actualListAllProductsResult).size());
        assertEquals("1", ((List<Product>) actualListAllProductsResult).get(0).getPrice().toString());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#listAllProducts()}
     */
    @Test
    void testListAllProducts3() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("42");
        product2.setPrice(BigDecimal.valueOf(1L));
        product2.setProductId("Product Id");
        product2.setVersion(0);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product2);
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        Iterable<Product> actualListAllProductsResult = productServiceImpl.listAllProducts();

        // Assert
        assertEquals(2, ((Collection<Product>) actualListAllProductsResult).size());
        assertEquals("1", ((List<Product>) actualListAllProductsResult).get(0).getPrice().toString());
        assertEquals("1", ((List<Product>) actualListAllProductsResult).get(1).getPrice().toString());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(Integer)}
     */
    @Test
    void testGetProductById() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        int id = 1;

        // Act
        Product actualProductById = productServiceImpl.getProductById(id);

        // Assert
        assertSame(product, actualProductById);
        assertEquals("1", actualProductById.getPrice().toString());
        verify(productRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#saveProduct(Product)}
     */
    @Test
    void testSaveProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        Product product2 = new Product();
        product2.setId(1);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(1L));
        product2.setProductId("42");
        product2.setVersion(1);

        // Act
        Product actualSaveProductResult = productServiceImpl.saveProduct(product2);

        // Assert
        assertSame(product, actualSaveProductResult);
        assertEquals("1", actualSaveProductResult.getPrice().toString());
        verify(productRepository).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(Product)}
     */
    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);
        Optional<Product> ofResult = Optional.of(product);

        Product product2 = new Product();
        product2.setId(1);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(1L));
        product2.setProductId("42");
        product2.setVersion(1);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Product product3 = new Product();
        product3.setId(1);
        product3.setName("Name");
        product3.setPrice(BigDecimal.valueOf(1L));
        product3.setProductId("42");
        product3.setVersion(1);

        // Act
        Product actualUpdateProductResult = productServiceImpl.updateProduct(product3);

        // Assert
        assertSame(product2, actualUpdateProductResult);
        assertEquals("1", actualUpdateProductResult.getPrice().toString());
        verify(productRepository).save(Mockito.<Product>any());
        verify(productRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(Integer)}
     */
    @Test
    void testDeleteProduct() {
        // Arrange
        doNothing().when(productRepository).deleteById(Mockito.<Integer>any());
        int id = 1;

        // Act
        productServiceImpl.deleteProduct(id);

        // Assert that nothing has changed
        verify(productRepository).deleteById(Mockito.<Integer>any());
    }
}

