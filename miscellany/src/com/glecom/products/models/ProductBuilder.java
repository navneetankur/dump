package com.glecom.products.models;

import org.apache.commons.fileupload.FileItem;

/**
 * @author navneet
 * helper class to build a object of product class.
 */
public class ProductBuilder {
	private int id;
	public String category;
	public String name;
	public String description;
	public String price;
	public String brand;
	public String subCategory;
	public String color;
	public String imagePath;
	public String vendor;
	public int discount;
	public String size;
	public int rating = 0;
	public String quantity;

	public void add(FileItem item) {
		String value = item.getString();
		switch(item.getFieldName()) {
		case "name":
			name = value;
			break;
		case "description":
			description = value;
			break;
		case "price":
			price = value;
			break;
		case "brand":
			brand = value;
			break;
		case "sub-category":
			subCategory = value;
			break;
		case "category":
			category = value;
			break;
		case "color":
			color = value;
			break;
		case "discount":
			discount = Integer.parseInt(value);
			break;
		case "vendor":
			vendor = value;
			break;
		case "size":
			size = value;
			break;
		case "quantity":
			quantity = value;
			break;
		}
		System.out.println(item.getFieldName()+" : "+value);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
