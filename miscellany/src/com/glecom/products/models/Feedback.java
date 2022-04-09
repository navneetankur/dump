package com.glecom.products.models;

/**
 * @author navneet
 * model class for entries in feedback table.
 */
public class Feedback {
	private int productId;
	private int id;
	private String name;
	private String comment;
	private String title;
	
	public Feedback(int productId, String name, String title, String comment) {
		this.productId = productId;
		this.name = name;
		this.title = title;
		this.comment = comment;
	}

	public int getProductId() {
		return productId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
}
