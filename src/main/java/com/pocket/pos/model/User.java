package com.pocket.pos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "pos_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;
	@Column(nullable = false)
	String name;
	@Column(nullable = false,unique = true)
	String phone;
	@Column(nullable = false)
	String password;
	@Column(nullable = false, unique = true)
	String userName;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	Role role;
	@Column(nullable = false)
	boolean deleted;
	@CreationTimestamp
	LocalDateTime creatDateTime;
	@UpdateTimestamp
	LocalDateTime updateDateTime;
	
	
	
	public User(){
		
	}
	public long getId() {
		return id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (phone == null) {
			if (other.getPhone() != null) {
				return false;
			}
		} else if (!phone.equals(other.getPhone())) {
			return false;
		}
		if (userName == null) {
			if (other.getUserName() != null) {
				return false;
			}
		} else if (!userName.equals(other.getUserName())) {
			return false;
		}
		return true;
	}
	
	
	
}
