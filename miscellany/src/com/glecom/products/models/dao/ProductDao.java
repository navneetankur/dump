package com.glecom.products.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.glecom.products.models.Product;

/**
 * @author navneet
 * dbconnection for change in product table.
 */
public class ProductDao {
	private DbConnection dbConnection;// = new DbConnection();
	private String query;
	private int offset = 0;
	private String tableName = "productc";
	private Product product;
	private final int numberOfProducts = 178;
	private ResultSet resultSet;
	
	/**
	 * @param product
	 * @throws SQLException
	 * add product to table
	 */
	public void addProduct(Product product) throws SQLException {
		query = "INSERT INTO `"+tableName+"` (`name`, `description`, `category`, `sub_category`, `price`, `brand`, `color`, `image`, `discount`, `vendor`, `size`) VALUES ('"+product.getName()+"', '"+product.getDescription()+"', '"+product.getCategory()+"', '"+product.getSubCategory()+"', '"+product.getPrice()+"', '"+product.getBrand()+"', '"+product.getColor()+"', '"+product.getImagePath()+"', '"+product.getDiscount()+"', '"+product.getVendor()+"', '"+product.getSize()+"')";
		dbConnection.execute(query);
	}
	
	/**
	 * @param id
	 * @throws SQLException
	 * delte product with given id.
	 */
	public void deleteProduct(String id) throws SQLException {
		query = "DELETE FROM `"+tableName+"` WHERE `id`='"+id+"'";
		dbConnection.execute(query);
	}
	
	public Product[] getNext10() throws SQLException{
		return getNext10(offset);
	}
	
	public Product[] getNext10(int o) throws SQLException {
		offset = o;
		query = "SELECT * FROM "+tableName+" LIMIT 10 OFFSET "+offset;
		offset += 10;
		ResultSet resultSet = dbConnection.executeQuery(query);
		Product productArray[] = new Product[10]; 
		for(int i=0;i<10 && resultSet.next();i++) {
			productArray[i] = getProductFromResultSet(resultSet);
		}
		return productArray;
	}
	
	/**
	 * @param id
	 * @return product with given id.
	 * @throws SQLException
	 */
	public Product getProduct(int id) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE id = '"+id+"'";
		resultSet = dbConnection.executeQuery(query);
		if(!resultSet.next()){return null;}
		product = getProductFromResultSet(resultSet);
		return product;
	}
	public ArrayList<Product> getProducts(int limit) throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();
		query = "SELECT * FROM "+tableName+" LIMIT "+limit;
		resultSet = dbConnection.executeQuery(query);
		while(resultSet.next()) {
			product = getProductFromResultSet(resultSet);
			productList.add(product);
		}
		return productList;
	}
	
	public ArrayList<Product> getRandomProducts(int limit) throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();
		Random random = new Random();
		query = "SELECT * FROM "+tableName+" LIMIT "+limit+" OFFSET "+(1+random.nextInt(numberOfProducts-14));
		resultSet = dbConnection.executeQuery(query);
		while(resultSet.next()) {
			product = getProductFromResultSet(resultSet);
			productList.add(product);
		}
		return productList;
	}
	
	public ArrayList<Product> getAll() throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();
		query = "SELECT * FROM "+tableName;
		resultSet = dbConnection.executeQuery(query);
		while(resultSet.next()) {
			product = getProductFromResultSet(resultSet);
			productList.add(product);
		}
		return productList;
	}
	public ArrayList<Product> getFromCategory(String category) throws SQLException {
		return getFromCategory(category, 100);
	}
	public ArrayList<Product> getFromCategory(String category, int limit) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE category = '"+category+"' LIMIT "+limit;
		ArrayList<Product> productList = new ArrayList<Product>();
		resultSet = dbConnection.executeQuery(query);
		while(resultSet.next()) {
			product = getProductFromResultSet(resultSet);
			productList.add(product);
		}
		return productList;
	}
	public ArrayList<Product> getFromSubCategory(String category, String subCategory, int limit) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE category = '"+category+"' AND sub_category = '"+subCategory+"' LIMIT "+limit;
		ArrayList<Product> productList = new ArrayList<Product>();
		resultSet = dbConnection.executeQuery(query);
		while(resultSet.next()) {
			product = getProductFromResultSet(resultSet);
			productList.add(product);
		}
		return productList;
	}
	public ArrayList<Product> getFromSubCategory(String category, String subCategory) throws SQLException {
		return getFromSubCategory(category, subCategory, 100);
	}
	
	public void setActiveValue(int id, int activeValue) throws SQLException {
		query = "UPDATE `"+tableName+"` SET `active`='"+activeValue+"' WHERE `id`='"+id+"'";
		dbConnection.execute(query);
	}
	
	public void setQuantity(int id, int quantity) throws SQLException {
		query = "UPDATE `"+tableName+"` SET `quantity`='"+quantity+"' WHERE `id`='"+id+"'";
		dbConnection.execute(query);
	}
	
	public void setRating(int id, int rating) throws SQLException {
		query = "UPDATE `"+tableName+"` SET `rating`='"+rating+"' WHERE `id`='"+id+"'";
		dbConnection.execute(query);
	}
	
	public int getRating(int id) throws SQLException {
		query = "SELECT `rating` FROM `"+tableName+"` WHERE `id`='"+id+"'";
		resultSet = dbConnection.executeQuery(query);
		int rating = 0;
		if(!resultSet.next()) rating =-1;
		else
			rating = resultSet.getInt("rating");
		return rating;
	}
	
	public int getNumberOfRatings(int id) throws SQLException {
		query = "SELECT `number_of_ratings` FROM `"+tableName+"` WHERE `id`='"+id+"'";
		resultSet = dbConnection.executeQuery(query);
		int numberOfRatings = 0;
		if(resultSet.next())
			numberOfRatings = resultSet.getInt("number_of_ratings");
		else numberOfRatings = -1;
		return numberOfRatings;
	}
	
	public void setNumberOfRatings(int id, int numberOfRatings) throws SQLException {
		query = "UPDATE `"+tableName+"` SET `number_of_ratings`='"+numberOfRatings+"' WHERE `id`='"+id+"'";
		dbConnection.execute(query);
	}

	public static Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
		Product product = new Product(resultSet.getString("name"), resultSet.getString("description")
				, resultSet.getString("category"), resultSet.getString("sub_category"), resultSet.getString("price")
				, resultSet.getString("brand"), resultSet.getString("color"), resultSet.getString("image")
				, resultSet.getInt("discount"), resultSet.getString("vendor"), resultSet.getString("size")
				, resultSet.getInt("rating"), resultSet.getString("number_of_ratings")
				, resultSet.getString("quantity"));
		product.setId(resultSet.getInt(1));
		return product;
	}

	public ProductDao() {
		dbConnection = new DbConnection();
	}
	
	public void close() throws SQLException  {
		dbConnection.close();
	}


}
