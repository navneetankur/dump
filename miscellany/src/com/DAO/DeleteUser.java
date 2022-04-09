package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * @author Sanyam tyagi
 * sets users active status =0 when a delete request is made
 *
 */
public class DeleteUser {
		 Connection connection;
		 PreparedStatement preparedStatement;
		    public DeleteUser() {
		        connection = ConnectDB.getConnection();
		    }
		    public void deleteUser(String userId) {
		        try {
		            preparedStatement = connection.prepareStatement("UPDATE C_info SET active='0' where userId='"+userId+"'");
		            //preparedStatement.setString(1, userId);
		            preparedStatement.executeUpdate();

		        } catch (SQLException e) {
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
				    if(connection!=null){
						try {
							connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

}
}