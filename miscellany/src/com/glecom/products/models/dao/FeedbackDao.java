package com.glecom.products.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.glecom.products.models.Feedback;

/**
 * @author navneet
 * connection with db. to change in feedback table.
 *
 */
public class FeedbackDao {
	private DbConnection dbConnection;
	private String query;
	private String tableName = "feedback";
	private Feedback feedback;
	private ResultSet resultSet;
	
	/**
	 * @param feedback
	 * @throws SQLException
	 * add new feedback.
	 */
	public void addFeedback(Feedback feedback) throws SQLException {
		query = "INSERT INTO `"+tableName+"` (`product_id`, `name`, `title`, `comment`) VALUES ('"+feedback.getProductId()+"', '"+feedback.getName()+"', '"+feedback.getTitle()+"', '"+feedback.getComment()+"')";
		dbConnection.execute(query);
	}
	/**
	 * @param id
	 * @throws SQLException
	 * deletes entry with the give id
	 */
	public void deleteFeedback(String id) throws SQLException {
		query = "DELETE FROM `"+tableName+"` WHERE `id`='"+id+"'";
		dbConnection.execute(query);
	}
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * return feedback with given id
	 */
	public Feedback getFeedback(int id) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE id = '"+id+"'";
		resultSet = dbConnection.executeQuery(query);
		if(!resultSet.next()){return feedback;}
		feedback = getFeedbackFromResultSet(resultSet);
		return feedback;
	}
	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * return arraylist with feedbacks for that product
	 */
	public ArrayList<Feedback> getAllForProductId(int id) throws SQLException {
		ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
		query = "SELECT * FROM "+tableName+" WHERE product_id = '"+id+"'";
		resultSet = dbConnection.executeQuery(query);
		while(resultSet.next()) {
			feedback = getFeedbackFromResultSet(resultSet);
			feedbackList.add(feedback);
		}
		return feedbackList;
	}
	private static Feedback getFeedbackFromResultSet(ResultSet resultSet) throws SQLException {
		Feedback feedback = new Feedback(resultSet.getInt(2), resultSet.getString("name"), resultSet.getString("title"), resultSet.getString("comment"));
		feedback.setId(resultSet.getInt(1));
		return feedback;
		
	}
	public FeedbackDao() {
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
