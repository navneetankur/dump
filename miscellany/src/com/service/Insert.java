package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.DAO.ConnectDB;
/**
 * @author Sanyam tyagi
 * 
 */
public class Insert {
	public static void addCart(String cart){
		    Connection connection;
		    connection = ConnectDB.getConnection();
		        try {
		            PreparedStatement preparedStatement = connection.prepareStatement("insert into user_info(user_id,cart)");
		            preparedStatement.executeUpdate();

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		      
		    }
}
