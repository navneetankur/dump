package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.glecom.products.models.Product;
import com.glecom.products.models.dao.ProductDao;

public class FetchData {
	String result = "ERROR";
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	/**
	 * @author Sanyam tyagi
	 * returns a ArrayList<Product> type containg all the requested products from the query.
	 *
	 */
public static ArrayList<Product> fetchData(String query){
	{
		 ArrayList<Product> list = new ArrayList<>();
		try
		{
			con = ConnectDB.getConnection();
			try {
				pstmt = con.prepareStatement(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()){
					Product product =ProductDao.getProductFromResultSet(rs);
					list.add(product);
				
			}
			} catch (SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		}
		finally
		{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
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
		
		return list;
	}
}
/**
 * @author Sanyam tyagi
 * returns a ArrayList<UserBean> type containg all the requested users from the query.
 *
 */
public static ArrayList<UserBean> fetchUser(String query){
	{
		
		ArrayList<UserBean> list = new ArrayList<>();
		try
		{
			con = ConnectDB.getConnection();
			try {
				pstmt = con.prepareStatement(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()){
					UserBean user=new GetUser().getUserFromResultSet(rs);
					list.add(user);
				
			}
			} catch (SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		}
		finally
		{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
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
		
		return list;
	}
	
}
/**
 * @author Sanyam tyagi
 * returns a Stirng containing all the product id's in the user's cart based on its user id
 *
 */
public static String fetchCart(String userId){
	{	
		String cart=null;
		String query="select cart from user_info where userId='"+userId+"'";

		try
		{
			con = ConnectDB.getConnection();
			try {
				pstmt = con.prepareStatement(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while(rs.next()){
					cart=rs.getString("cart");
				}
			} catch (SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(cart!=null){
				
			}
			else{
				pstmt = con.prepareStatement("insert into user_info (userId,cart,wishlist) values (?,?, ? )");
	            pstmt.setString(1,userId);
	            pstmt.setString(2,"0");
	            pstmt.setString(3,"0");
	            pstmt.executeUpdate();
	            cart="0";
	            return cart;
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
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
		
		return cart;
	}
	
}
/**
 * @author Sanyam tyagi
 * returnsa Stirng containing all the product id's in the user's wishlist based on its user id
 */
public static String fetchWishlist(String userId) {
	// TODO Auto-generated method stub
	String wishlist=null;
	String query="select wishlist from user_info where userId='"+userId+"'";

	try
	{
		con = ConnectDB.getConnection();
		try {
			pstmt = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				wishlist=rs.getString("wishlist");
			}
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(wishlist!=null){
			
		}
		else{
			pstmt = con.prepareStatement("insert into user_info (userId,cart,wishlist) values (?,?, ? )");
            pstmt.setString(1,userId);
            pstmt.setString(2,"0");
            pstmt.setString(3,"0");
            pstmt.executeUpdate();
            wishlist="0";
            return wishlist;
		}
		
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
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
	
	return wishlist;
}
/**
 * @author Sanyam tyagi
 * returnsa Stirng containing all the product id's form the table order that contains all the orders of that user
 */
public static String fetchProducts(String userId) {
	StringBuffer p=new StringBuffer();
	StringBuffer id=new StringBuffer();
	System.out.println(userId);
	String query="select product_id,id from orders where user_id='"+userId+"'";
	System.out.println(query);
	try
	{
		con = ConnectDB.getConnection();
		try {
			pstmt = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				
				p.append(rs.getString("product_id")+",");
				id.append(rs.getString("id")+",");
				
			}
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	finally
	{
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
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
	if(p!=null||!p.equals("")){
	p.append("/");
	p.append(id);}
	System.out.println("fetch" + p);
	return String.valueOf(p);
}
//nav begin
public static void addToForgotPassword(String hash, String userId) {
	String query="INSERT INTO `forgot_password` (`reset_hash`, `user_id`) VALUES (?, ?)";
	con = ConnectDB.getConnection();
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1,hash);
		pstmt.setString(2, userId);
		pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally
	{
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
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

public static String getUserIdFromResetHash(String hash) {
	String query="Select user_id FROM forgot_password WHERE reset_hash = '"+hash+"'";
	con = ConnectDB.getConnection();
	try {
		pstmt = con.prepareStatement(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		rs = pstmt.executeQuery();
	} catch (SQLException e) {
		System.out.println(query);
		e.printStackTrace();
	}
	try {
		if(rs.next()) {
			return rs.getString("user_id");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
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
	return null;
}

public static void cleanForgotTableForUserId(String userId) {
	String query = "DELETE FROM `forgot_password` WHERE `user_id`='"+userId+"'";
	con = ConnectDB.getConnection();
	try {
		pstmt = con.prepareStatement(query);
		pstmt.executeUpdate();
	} catch (SQLException e) {
		System.out.println(query);
		e.printStackTrace();
	}
	finally
	{
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
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

public static void removeForgotPasswordHash(String hash) {
	String query = "DELETE FROM `forgot_password` WHERE `reset_hash`='"+hash+"'";
	con = ConnectDB.getConnection();
	try {
		pstmt = con.prepareStatement(query);
		pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
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


//nav end
}

