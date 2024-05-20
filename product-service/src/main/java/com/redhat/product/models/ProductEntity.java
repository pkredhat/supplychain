package com.redhat.product.models;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Objects;

public class ProductEntity {


    private ObjectId id;
    private String productName;
    private String productDescription;
    private Date created;

    public ProductEntity() {
    }

    public ProductEntity(ObjectId id, String productName, String productDescription, Date created) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.created = created;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productDescription != null ? productDescription.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(productName, that.productName)) return false;
        if (!Objects.equals(productDescription, that.productDescription)) return false;
        return Objects.equals(created, that.created);
    }

    @Override
    public String toString() {
        return "ProductEntity{" + "id=" + id + ", productName='" + productName + '\'' + ", productDescription='" + productDescription + '\'' + ", createdAt=" + created + '}';
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String name) {
        this.productName = name;
    }

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String headquarters) {
        this.productDescription = productDescription;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
}
