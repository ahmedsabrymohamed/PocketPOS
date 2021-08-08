package com.pocket.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pos_bill_second_party")
public class BillSecondParty extends EntityCommons {
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String phone;

	public BillSecondParty() {

	}

	public BillSecondParty(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BillSecondParty)) {
			return false;
		}
		BillSecondParty other = (BillSecondParty) obj;
		if (phone == null) {
			if (other.getPhone() != null) {
				return false;
			}
		} else if (!phone.equals(other.getPhone())) {
			return false;
		}
		return true;
	}

}
