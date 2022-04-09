package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Update {
	/**
	 * @author Sanyam tyagi
	 * makes the user vendor by changing its access type to vendor from user
	 */
	public static void update(String accessType,String userId){
		 Connection con=  ConnectDB.getConnection();
		  PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement("UPDATE C_info SET access_type=? where userId=?");
			preparedStatement.setString(1,accessType);
			preparedStatement.setString(2,userId);
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
	        
	        if(preparedStatement!=null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	      
	}
	/**
	 * @author Sanyam tyagi
	 * updates user profile details
	 */
	public static void updateProfile(String user_name, String user_email, String user_contact, String dob, String sex, String userId){
		 Connection con=  ConnectDB.getConnection();
		  PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement("UPDATE C_info SET user_name=?,user_email=?,user_contact=?,dob=?,gender=? where userId=?");
			preparedStatement.setString(1,user_name);
			preparedStatement.setString(2,user_email);
			preparedStatement.setString(3,user_contact);
			preparedStatement.setString(4,dob);
			preparedStatement.setString(5,sex);
			preparedStatement.setString(6,userId);
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
	        
	        if(preparedStatement!=null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	      
	}
	/**
	 * @author Sanyam tyagi
	 * updates the cart list
	 */
	public static void updateCart(String cart,String userId){
		 Connection con=  ConnectDB.getConnection();
		  PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement("UPDATE user_info SET cart=? where userId=?");
			preparedStatement.setString(1,cart);
			preparedStatement.setString(2,userId);
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
	        
	        if(preparedStatement!=null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	      
	}
	/**
	 * @author Sanyam tyagi
	 * updates the wishlist list
	 */
	public static void updateWishlist(String list, String userId) {
		// TODO Auto-generated method stub
		Connection con=  ConnectDB.getConnection();
		  PreparedStatement preparedStatement=null;
		try {
			preparedStatement = con.prepareStatement("UPDATE user_info SET wishlist=? where userId=?");
			preparedStatement.setString(1,list);
			preparedStatement.setString(2,userId);
			preparedStatement.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
	        
	        if(preparedStatement!=null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	}
	 
}
