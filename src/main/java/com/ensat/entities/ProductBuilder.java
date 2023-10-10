package com.ensat.entities;

import java.math.BigDecimal;
import java.util.UUID;

public final class ProductBuilder {
    private Integer id;
    private Integer version;
    private String productId;
    private String name;
    private BigDecimal price;

    public ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ProductBuilder withProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductBuilder withProduct(Product product) {
        this.id = (int) UUID.randomUUID().getLeastSignificantBits();
        this.version = product.getVersion();
        this.productId = product.getProductId();
        this.name = product.getName();
        this.price = product.getPrice();
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setVersion(version);
        product.setProductId(productId);
        product.setName(name);
        product.setPrice(price);
        return product;
    }
}
