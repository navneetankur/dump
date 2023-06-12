package com.database;

import java.sql.*;

public class DBConnect {
	private Connection connect;
	private Statement stmt;
	private ResultSet rst;
	private String query;
	
	public DBConnect()
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/global_logic_project", "root", "root");
			stmt=connect.createStatement();
							
		}
		catch(Exception e){
			System.out.println("Error:" +e);
		}
	}
	public void execute(String query){
		try{
			stmt.execute(query);
		}catch(SQLException e){
			System.out.println(e);
			//e.printStackTrace();
			System.out.println(query);
		}
	}
	public ResultSet executeQuery(String query){
			try{
				rst=stmt.executeQuery(query);
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println(query);
			}
			return rst;
	}
	public boolean addUser(String userId, String user_name, String user_email, String user_contact, String enp,String accessType, String sex, String dob,String active){
		try{
			if(getUser(userId).next()){
				return false;			
				}
		}
		catch(Exception e){
			System.out.println(e);
		}
		query="INSERT INTO c_info (userId,user_name,user_email,user_contact,enp,access_type,gender,dob,active)"
				+ " VALUES('"+userId+"','"+user_name+"','"+user_email+"','"+user_contact+"',"
						+ "'"+enp+"','"+accessType+"','"+sex+"','"+dob+"','"+active+"')";
		execute(query);
		return true;
		
	}
	public ResultSet getUser(String userId) {
	query="SELECT * FROM c_info WHERE userId='"+userId+"'";
	return executeQuery(query);
	}
	public ResultSet getEmail(String userId) {
		query="SELECT user_email FROM c_info WHERE userId='"+userId+"'";
		return executeQuery(query);
		}
	public Boolean setEmail(String user_email, String userId) {
		query="UPDATE c_info set user_email='"+user_email+"' WHERE userId='"+userId+"'";
		execute(query);
		return true;
		}
	public Boolean setContact(String user_contact, String userId) {
		query="UPDATE c_info set user_contact='"+user_contact+"' WHERE userId='"+userId+"'";
		execute(query);
		return true;
		}
	public ResultSet getPass(String enp) {
		query="SELECT * FROM c_info WHERE enp='"+enp+"'";
		return executeQuery(query);
		}
	public Boolean UpdatePass(String userId, String nepass) {
		query="UPDATE c_info set enp='"+nepass+"' WHERE userId='"+userId+"'";
		execute(query);
		return true;
		}
	public ResultSet getUserData(String userId) {
		query="SELECT * FROM c_info WHERE userId='"+userId+"'";
		return executeQuery(query);
		}
	
	public void close(){
		try{
			stmt.close();
			connect.close();
			rst.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
		
	



