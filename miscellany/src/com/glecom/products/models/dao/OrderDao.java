package com.glecom.products.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.glecom.products.models.Order;

/**
 * @author navneet
 * connect with db to change in order table.
 */
public class OrderDao {
	private DbConnection dbConnection;
	private String query;
	private final String tableName = "orders";
	private Order order;
	private ResultSet resultSet;
	
	/**
	 * @param order
	 * @throws SQLException
	 * add new order to table
	 */
	public void addOrder(Order order) throws SQLException {
		query = "INSERT INTO `"+tableName+"` (`user_id`, `product_id`, `address_id`, `timestamp`) VALUES ('"+order.getUserId()+"', '"+order.getProductId()+"', '"+order.getAddressId()+"', '"+order.getTimestamp()+"')";
		dbConnection.execute(query);
	}
	
	/**
	 * @param id
	 * @return order object for given id.
	 * @throws SQLException
	 */
	public Order getOrder(int id) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE id = '"+id+"'";
		resultSet = dbConnection.executeQuery(query);
		if(!resultSet.next()){return null;}
		order = getOrderFromResultSet(resultSet);
		return order;
	}
	
	/**
	 * @param timestamp
	 * @return order object for given timestamp.
	 * @throws SQLException
	 */
	public Order getOrderFromTimestamp(String timestamp) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE timestamp = '"+timestamp+"'";
		resultSet = dbConnection.executeQuery(query);
		if(!resultSet.next()){return null;}
		order = getOrderFromResultSet(resultSet);
		return order;
	}
	
	public static Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
		Order order = new Order(resultSet.getString("user_id"), resultSet.getInt("product_id"), resultSet.getInt("address_id")
				,resultSet.getString("timestamp"));
		order.setId(resultSet.getInt(1));
		return order;
	}

	public OrderDao() {
		dbConnection = new DbConnection();
	}
	
	/**
	 * @throws SQLException
	 * closes db connection
	 */
	public void close() throws SQLException {
		dbConnection.close();
	}
}
