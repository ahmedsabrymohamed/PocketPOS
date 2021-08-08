package com.pocket.pos.model;

public class BulkModel extends EntityCommonsModel {

    private Long bulkId;
    private Double buyPrice;
    private Double quantity;
    private Long productId;


    public BulkModel() {

    }


    public BulkModel(Double buyPrice, Double quantity, Long productId) {

        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.productId = productId;
    }


    public BulkModel(Long bulkId, Double buyPrice, Double quantity, Long productId) {

        this.bulkId = bulkId;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.productId = productId;
    }

    public Long getBulkId() {
        return bulkId;
    }

    public void setBulkId(Long bulkId) {
        this.bulkId = bulkId;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
