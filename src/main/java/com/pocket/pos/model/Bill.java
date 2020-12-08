package com.pocket.pos.model;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public  class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	@Column(nullable = false)
	int billNumber;
	@Column(nullable = false)
	Long total;
	@Column(nullable = false)
	Long paid;
	@Column(nullable = false)
	Long remainder;
	@CreationTimestamp
	LocalDateTime creatDateTime;
	@UpdateTimestamp
	LocalDateTime updateDateTime;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	BillType billType;
	@Column(nullable = false)
	boolean deleted;
	@ManyToOne(optional = false)
	BillSecondParty secondParty;
	@ElementCollection
	@CollectionTable(name = "Bill_Item")
	Collection<BillItem> items;
	
	

	public Bill() {
		
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public LocalDateTime getCreatDateTime() {
		return creatDateTime;
	}

	public void setCreatDateTime(LocalDateTime creatDateTime) {
		this.creatDateTime = creatDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public long getId() {
		return id;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getPaid() {
		return paid;
	}

	public void setPaid(Long paid) {
		this.paid = paid;
	}

	public Long getRemainder() {
		return remainder;
	}

	public void setRemainder(Long remainder) {
		this.remainder = remainder;
	}

	public BillType getBillType() {
		return billType;
	}

	public void setBillType(BillType partyType) {
		this.billType = partyType;
	}
	

	public Collection<BillItem> getItems() {
		return items;
	}

	public void setItems(Collection<BillItem> items) {
		this.items = items;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public BillSecondParty getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(BillSecondParty secondParty) {
		this.secondParty = secondParty;
	}
	
	
}
