package com.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author Sanyam tyagi
 * cretes a UserBean class type object
 */
public class GetUser {
	public UserBean getUserFromResultSet(ResultSet resultSet) throws SQLException {
		UserBean user = new UserBean(resultSet.getString("userId"), resultSet.getString("user_name")
				, resultSet.getString("user_email"), resultSet.getString("user_contact"), 
				resultSet.getString("access_type"),resultSet.getString("gender"),resultSet.getString("dob"), resultSet.getString("active"));
		//product.setId(resultSet.getInt(1));
		return user;
	}
}
