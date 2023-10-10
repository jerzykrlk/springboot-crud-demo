package com.ensat.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ensat.entities.Product;
import com.ensat.services.ProductService;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link ProductController#patchProduct(Product)}
     */
    @Test
    void testPatchProduct() throws Exception {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);

        Product product2 = new Product();
        product2.setId(1);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(1L));
        product2.setProductId("42");
        product2.setVersion(1);
        when(productService.saveProduct(Mockito.<Product>any())).thenReturn(product2);
        when(productService.getProductById(Mockito.<Integer>any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/product");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/product/null"));
    }

    /**
     * Method under test: {@link ProductController#saveProduct(Product)}
     */
    @Test
    void testSaveProduct() throws Exception {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);
        when(productService.saveProduct(Mockito.<Product>any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/product/null"));
    }

    /**
     * Method under test: {@link ProductController#delete(Integer)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/products"));
    }

    /**
     * Method under test: {@link ProductController#delete(Integer)}
     */
    @Test
    void testDelete2() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1);
        requestBuilder.characterEncoding("Encoding");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/products"));
    }

    /**
     * Method under test: {@link ProductController#edit(Integer, Model)}
     */
    @Test
    void testEdit() throws Exception {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);
        when(productService.getProductById(Mockito.<Integer>any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/edit/{id}", 1);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("productform"));
    }

    /**
     * Method under test: {@link ProductController#list(Model)}
     */
    @Test
    void testList() throws Exception {
        // Arrange
        when(productService.listAllProducts()).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("products"));
    }

    /**
     * Method under test: {@link ProductController#list(Model)}
     */
    @Test
    void testList2() throws Exception {
        // Arrange
        when(productService.listAllProducts()).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        requestBuilder.characterEncoding("Encoding");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("products"));
    }

    /**
     * Method under test: {@link ProductController#newProduct(Model)}
     */
    @Test
    void testNewProduct() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/new");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("productform"));
    }

    /**
     * Method under test: {@link ProductController#newProduct(Model)}
     */
    @Test
    void testNewProduct2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/new");
        requestBuilder.characterEncoding("Encoding");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("productform"));
    }

    /**
     * Method under test: {@link ProductController#showProduct(Integer, Model)}
     */
    @Test
    void testShowProduct() throws Exception {
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);
        when(productService.getProductById(Mockito.<Integer>any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1);
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("productshow"));
    }
}

