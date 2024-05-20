package com.redhat.order.models;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Objects;

public class OrderEntity {

    // Order
//     public String orderName;
//     public int customerId;
//     public int productId;
//     public int orderTotal;
//     public LocalDate createdDate;



    private ObjectId id;
    private String orderName;
    private int customerId;
    private int productId;
    private float orderTotal;
    private Date created;

    public OrderEntity() {
    }

    public OrderEntity(ObjectId id, String orderName, int customerId, int productId, Float orderTotal, Date created) {
        this.id = id;
        this.orderName = orderName;
        this.customerId = customerId;
        this.productId = productId;
        this.orderTotal = orderTotal;

        this.created = created;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderName != null ? orderName.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(orderName, that.orderName)) return false;
        if (!Objects.equals(customerId, that.customerId)) return false;
        if (!Objects.equals(productId, that.productId)) return false;
        if (!Objects.equals(orderTotal, that.orderTotal)) return false;

        return Objects.equals(created, that.created);
    }

    @Override
    public String toString() {
        return "OrderEntity{" + "id=" + id + ", orderName='" + orderName + '\'' + ", orderTotal='" + orderTotal + '\'' + ", createdAt=" + created + '}';
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
}
