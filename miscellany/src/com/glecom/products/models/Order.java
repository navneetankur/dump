package com.glecom.products.models;

/**
 * @author navneet
 * model class for entries in order table.
 */
public class Order {
	private int id;
	private String userId;
	private int productId;
	private int addressId;
	private String timestamp;
	
	public Order(String userId, int productId, int addressId, String timestamp) {
		this.userId = userId;
		this.productId = productId;
		this.addressId = addressId;
		this.timestamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public int getAddressId() {
		return addressId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public String getUserId() {
		return userId;
	}
}
