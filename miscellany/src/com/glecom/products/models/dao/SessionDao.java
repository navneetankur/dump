package com.glecom.products.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glecom.products.models.SessionBean;

/**
 * @author navneet
 * db connection for change in session table.
 */
public class SessionDao {
	private DbConnection dbConnection = new DbConnection();
	private String query;
	private String tableName = "session";
	private ResultSet resultSet;
	public static final String cookieName = "userSession";
	public static final String sessionIdAttributeName = "userSessionId";
	private final int maxAge = 60*60*24*30;
	
	/**
	 * @param sessionBean
	 * @throws SQLException
	 * saves user session
	 */
	public void saveSession(SessionBean sessionBean) throws SQLException {
		query = "INSERT INTO `"+tableName+"` (`session_id`, `user_id`, `access_type`) VALUES ('"+sessionBean.getSessionId()+"', '"+sessionBean.getUserId()+"', '"+sessionBean.getAccessType()+"')";
		dbConnection.execute(query);
	}
	
	public void removeSession(int id) throws SQLException {
		query = "DELETE FROM `"+tableName+"` WHERE `id`='"+id+"'";
		dbConnection.execute(query);
	}
	
	/**
	 * @param sessionId
	 * @throws SQLException
	 * remove session with given sessionid
	 */
	public void removeSessionBySessionId(String sessionId) throws SQLException {
		query = "DELETE FROM `"+tableName+"` WHERE `session_id`='"+sessionId+"'";
		dbConnection.execute(query);
	}
	
	/**
	 * @param sessionId
	 * @return sessionbean obj with given sessionid
	 * @throws SQLException
	 */
	public SessionBean getSessionFromSessionId(String sessionId) throws SQLException {
		query = "SELECT * FROM "+tableName+" WHERE session_id = '"+sessionId+"'";
		resultSet = dbConnection.executeQuery(query);
		if(resultSet.next()) {
			SessionBean sessionBean = getSessionBeanFromResultSet(resultSet);
			return sessionBean;
		}
		else return null;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param userId
	 * @param accessType
	 * @throws SQLException
	 * saves user session
	 */
	public void doRememberMe( HttpServletRequest request, HttpServletResponse response, String userId, String accessType) throws SQLException {
		UUID id1 = UUID.randomUUID();
		saveSession(new SessionBean(id1.toString(), userId, accessType));
		Cookie cookie = new Cookie(cookieName, id1.toString());
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("accessType", accessType);
		session.setAttribute(sessionIdAttributeName, id1.toString());
	}
	
	public void checkRememberMeFromSessionId(String sessionId, HttpServletRequest request) throws SQLException {
		SessionBean sessionBean = getSessionFromSessionId(sessionId);
		if(sessionBean == null) return;
		HttpSession session = request.getSession();
		session.setAttribute("userId", sessionBean.getUserId());
		session.setAttribute("accessType", sessionBean.getAccessType());
		session.setAttribute(sessionIdAttributeName, sessionBean.getSessionId());
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws SQLException
	 * removes session for current user. used when user logs out
	 */
	public void forgetMe(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute(sessionIdAttributeName);
		if(sessionId == null) return;
		else {
			Cookie cookie = new Cookie(cookieName, "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			removeSessionBySessionId(sessionId);
		}
	}
	
	private static SessionBean getSessionBeanFromResultSet(ResultSet resultSet) throws SQLException {
		SessionBean sessionBean = new SessionBean(resultSet.getString("session_id"), resultSet.getString("user_id"), resultSet.getString("access_type"));
		sessionBean.setId(resultSet.getInt(1));
		return sessionBean;
	}
	
	public void close() throws SQLException {
		dbConnection.close();
	}
}
