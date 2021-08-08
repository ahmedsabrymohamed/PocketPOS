package com.pocket.pos.model;

import java.util.Collection;

import com.pocket.pos.entity.BillType;

public class BillModel extends EntityCommonsModel {

    private Double paid;
    private BillType billType;
    private Long secondParty;
    private Long billId;
    private Double total;
    private Double remainder;
    private Collection<BillItemModel> items;
    protected boolean deleted = false;


    public BillModel() {

    }


    public BillModel(Double paid, BillType billType, Long secondParty, Collection<BillItemModel> items) {
        this.paid = paid;
        this.billType = billType;
        this.secondParty = secondParty;
        this.items = items;
    }


    public BillModel(Long billId, Double paid, BillType billType, Long secondParty,
                     Collection<BillItemModel> items) {
        this.billId = billId;
        this.paid = paid;
        this.billType = billType;
        this.secondParty = secondParty;
        this.items = items;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public Long getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(Long secondParty) {
        this.secondParty = secondParty;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Collection<BillItemModel> getItems() {
        return items;
    }

    public void setItems(Collection<BillItemModel> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
