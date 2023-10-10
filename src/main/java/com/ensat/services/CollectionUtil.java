package com.ensat.services;

import com.ensat.entities.Product;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtil {
    static List<Product> sorted(List<Product> products) {
        if (products == null || CollectionUtils.isEmpty(products)) {
            return Collections.emptyList();
        }

        List<Product> result = new ArrayList<>(products);
        Collections.sort(result, Comparator.nullsFirst(Comparator.comparing(product -> product.getName().toLowerCase())));
        return result;
    }
}
