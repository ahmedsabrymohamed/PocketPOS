package com.pocket.pos.model;

public class BillSecondPartyModel {


    private String name;

    private String phone;

    private Long secondPartyId;


    public BillSecondPartyModel() {
    }

    public BillSecondPartyModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public BillSecondPartyModel(String name, String phone, Long secondPartyId) {
        this.name = name;
        this.phone = phone;
        this.secondPartyId = secondPartyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSecondPartyId() {
        return secondPartyId;
    }

    public void setSecondPartyId(Long secondPartyId) {
        this.secondPartyId = secondPartyId;
    }
}
