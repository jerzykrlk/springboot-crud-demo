package com.ensat.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.util.COWArrayList;
import com.ensat.entities.Product;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CollectionUtilTest {
    /**
     * Method under test: {@link CollectionUtil#sorted(List)}
     */
    @Test
    void testSorted() {
        List<Product> actualSortedResult = CollectionUtil.sorted(new ArrayList<>());
        assertTrue(actualSortedResult.isEmpty());
    }

    /**
     * Method under test: {@link CollectionUtil#sorted(List)}
     */
    @Test
    void testSorted2() {
        List<Product> actualSortedResult = CollectionUtil.sorted(null);
        assertTrue(actualSortedResult.isEmpty());
    }

    /**
     * Method under test: {@link CollectionUtil#sorted(List)}
     */
    @Test
    void testSorted3() {
        Product product = new Product();
        product.setId(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setProductId("42");
        product.setVersion(1);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        List<Product> actualSortedResult = CollectionUtil.sorted(products);
        assertEquals(1, actualSortedResult.size());
        assertEquals("1", actualSortedResult.get(0).getPrice().toString());
    }

    /**
     * Method under test: {@link CollectionUtil#sorted(List)}
     */
    @Test
    void testSorted4() {
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

        ArrayList<Product> products = new ArrayList<>();
        products.add(product2);
        products.add(product);
        List<Product> actualSortedResult = CollectionUtil.sorted(products);
        assertEquals(2, actualSortedResult.size());
        assertEquals("1", actualSortedResult.get(0).getPrice().toString());
        assertEquals("1", actualSortedResult.get(1).getPrice().toString());
    }

    /**
     * Method under test: {@link CollectionUtil#sorted(List)}
     */
    @Test
    void testSorted5() {
        COWArrayList<Product> products = mock(COWArrayList.class);
        when(products.isEmpty()).thenReturn(true);
        List<Product> actualSortedResult = CollectionUtil.sorted(products);
        assertTrue(actualSortedResult.isEmpty());
        verify(products).isEmpty();
    }
}

