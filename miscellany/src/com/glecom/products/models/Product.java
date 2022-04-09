package com.glecom.products.models;

/**
 * @author navneet
 * model class for entries in product table.
 */
public class Product {
	private int id;
	private String category;
	private String name;
	private String description;
	private String price;
	private String brand;
	private String subCategory;
	private String color;
	private String imagePath;
	private int discount;
	private String vendor;
	private String size;
	private int rating;
	private String numberOfRatings;
	private String active;
	private String quantity;
	
	public Product(String name, String description, String category,
			String subCategory, String price, String brand, String color,
			String imagePath,int discount, String vendor, String size,
			int rating, String numberOfRatings, String quantity) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.subCategory = subCategory;
		this.price = price;
		this.brand = brand;
		this.color = color;
		this.imagePath = imagePath;
		this.discount = discount;
		this.vendor = vendor;
		this.size = size;
		this.rating = rating;
		this.quantity = quantity;
		this.numberOfRatings = numberOfRatings;
	}

	public Product(ProductBuilder productBuilder) {
		id = productBuilder.getId();
		name = productBuilder.name;
		description = productBuilder.description;
		category = productBuilder.category;
		subCategory = productBuilder.subCategory;
		price = productBuilder.price;
		brand = productBuilder.brand;
		color = productBuilder.color;
		imagePath = productBuilder.imagePath;
		discount = productBuilder.discount;
		vendor = productBuilder.vendor;
		size = productBuilder.size;
		rating = productBuilder.rating;
		quantity = productBuilder.quantity;
		active = "1";
	}

	public int getId() {
		return id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getPrice() {
		return price;
	}

	public String getBrand() {
		return brand;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public String getColor() {
		return color;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getVendor() {
		return vendor;
	}

	public int getDiscount() {
		return discount;
	}

	public String getSize() {
		return size;
	}

	public int getRating() {
		return rating;
	}

	public String getActive() {
		return active;
	}

	public String getQuantity() {
		return quantity;
	}

	public int getDiscountedPrice() {
		return Integer.parseInt(price) - Integer.parseInt(price)*discount/100;
	}

	public String getNumberOfRatings() {
		return numberOfRatings;
	}
	
	public double getRatingMax5() {
		return rating/2.0;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( !(obj instanceof Product) ) return false;
		Product that = (Product)obj;
		return this.id == that.id;
	}

	@Override
	public int hashCode() {
		return 37*21+id+name.hashCode();
	}




}
