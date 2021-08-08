package com.pocket.pos.model;

public class BillItemModel extends EntityCommonsModel {

    private Double price;
    private Double quantity;
    private Long bulkId;
    private boolean newBulk;
    private Long productId;

    public BillItemModel() {

    }

    public BillItemModel(Double price, Double quantity, Long bulkId, boolean newBulk, Long productId) {
        this.price = price;
        this.quantity = quantity;
        this.bulkId = bulkId;
        this.newBulk = newBulk;
        this.productId = productId;
    }




    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getBulkId() {
        return bulkId;
    }

    public void setBulkId(Long bulkId) {
        this.bulkId = bulkId;
    }

    public boolean isNewBulk() {
        return newBulk;
    }

    public void setNewBulk(boolean newBulk) {
        this.newBulk = newBulk;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
