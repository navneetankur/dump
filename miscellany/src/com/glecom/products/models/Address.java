package com.glecom.products.models;

/**
 * @author navneet
 * model class for entries in address table.
 */
public class Address {
	private int id;
	private String userId;
	private String address;
	private String pincode;
	private String name;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public String getAddress() {
		return address;
	}

	public Address(String userId, String name, String address, String pincode, String phone) {
		this.userId = userId;
		this.address = address;
		this.pincode = pincode;
		this.name = name;
		this.phone = phone;
	}

	public String getPincode() {
		return pincode;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public Address(String name, String address, String pincode, String phone) {
		this.address = address;
		this.pincode = pincode;
		this.name = name;
		this.phone = phone;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
