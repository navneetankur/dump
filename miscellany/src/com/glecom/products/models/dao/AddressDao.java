package com.glecom.products.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.glecom.products.models.Address;

/**
 * @author navneet
 * Creates connection with database. Used for altering address table
 */
public class AddressDao {
	private DbConnection dbConnection;
	private String query;
	private final String tableName = "address_book";
	private ResultSet resultSet;
	private Address address;
	
	/**
	 * @param address
	 * @throws SQLException
	 * Add a new address to address table
	 */
	public void addUserAddress(Address address) throws SQLException {
		query = "INSERT INTO `"+tableName+"` (`userId`, `name`, `address`, `pincode`, `phone`, `guest`) VALUES ('"+address.getUserId()+"', '"+address.getName()+"', '"+address.getAddress()+"', '"+address.getPincode()+"', '"+address.getPhone()+"', '"+0+"')";
		dbConnection.execute(query);
	}
	
	public void addGuestAddress(Address address) throws SQLException {
		query = "INSERT INTO `"+tableName+"` (`name`, `address`, `pincode`, `phone`, `guest`) VALUES ('"+address.getName()+"', '"+address.getAddress()+"', '"+address.getPincode()+"', '"+address.getPhone()+"', '"+1+"')";
		dbConnection.execute(query);
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 * Returns arraylist with addresses of the give userid.
	 */
	public ArrayList<Address> getForUser(String userId) throws SQLException {
		ArrayList<Address> addressList = new ArrayList<Address>();
		query = "SELECT * FROM "+tableName+" WHERE userId = '"+userId+"'";
		resultSet = dbConnection.executeQuery(query);
		while(resultSet.next()) {
			address = getAddressFromResultSet(resultSet);
			addressList.add(address);
		}
		return addressList;
	}
	
	private static Address getAddressFromResultSet(ResultSet resultSet) throws SQLException {
		Address address = new Address(resultSet.getString("userId"), resultSet.getString("name")
				, resultSet.getString("address"), resultSet.getString("pincode"), resultSet.getString("phone"));
		address.setId(resultSet.getInt(1));
		return address;
	}

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * returns address from given addressid
	 */
	public Address getAddress(int id) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE id = '"+id+"'";
		resultSet = dbConnection.executeQuery(query);
		if(!resultSet.next()){return null;}
		address = getAddressFromResultSet(resultSet);
		return address;
	}

	public AddressDao() {
		dbConnection = new DbConnection();
	}
	
	/**
	 * @throws SQLException
	 * closes connection with db.
	 */
	public void close() throws SQLException {
		dbConnection.close();
	}

}
