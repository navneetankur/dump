package com.glecom.handlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glecom.products.models.Product;
import com.glecom.products.models.dao.ProductDao;
import com.glecom.products.models.dao.SessionDao;

/**
 * @author navneet
 * used for creating sessions and handling cookies.
 */
public class Tracking {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private final int maxRecentProducts = 4;
	private final String recentlyViewedCookieName = "recents";
	private final int cookieMaxAge = 60*60*24*30;
	
	public Tracking(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		HttpSession session = request.getSession();
		Cookie sessionCookie = getCookie(SessionDao.cookieName);
		if(sessionCookie != null) {
			SessionDao sessionDao = new SessionDao();
			try {
				sessionDao.checkRememberMeFromSessionId(sessionCookie.getValue(), request);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					sessionDao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		String userid = (String) session.getAttribute("userId");
		if(userid==null) {
			session.setAttribute("userId", "guest");
			session.setAttribute("accessType", "guest");
		}
		String goBack = (String) session.getAttribute("goBack");
		if(goBack == null) session.setAttribute("goBack", "./home");
	}
	/**
	 * @param cookieName
	 * @return cookie object with given name.
	 */
	public Cookie getCookie(String cookieName) {
		Cookie [] cookies = request.getCookies();
		if(cookies == null) return null;
		for(Cookie cookie : cookies) {
    		if(cookie.getName().equals(cookieName))
    			return cookie;
		}
		return null;
	}

	/**
	 * @param productId
	 * adds productid to cooki for displaying in recently viewed.
	 */
	public void addToRecentlyViewed(int productId) {
		Cookie recents = getCookie(recentlyViewedCookieName);
		String [] recentArray;
		if(recents == null) {
			recentArray = new String[1];
			recentArray[0] = ""+productId;
		}
		else {
			recentArray = recents.getValue().split(" ");
		}
		for(String id : recentArray) {
			if(id.equals(productId))
				return;
		}
		ArrayList<String> recentList = new ArrayList<String>(Arrays.asList(recentArray));
		recentList.add(""+productId);
		if(recentList.size() > maxRecentProducts) {
			recentList.remove(0);
		}
		StringBuilder sb = new StringBuilder();
		for(String id: recentList) {
			sb.append(id+" ");
		}
		recents = new Cookie("recents", sb.toString());
		recents.setMaxAge(cookieMaxAge);
		response.addCookie(recents);
	}
	/**
	 * @return arraylist containing products whose ids are found in cookie
	 */
	public ArrayList<Product> getRecentlyViewed() {
		ArrayList<Product> productList = new ArrayList<Product>();
		Cookie recents = getCookie(recentlyViewedCookieName);
		if(recents == null) return productList;
		String [] recentArray = recents.getValue().split(" ");
		ArrayList<String> recentList = new ArrayList<String>(Arrays.asList(recentArray));
		ProductDao productDao = new ProductDao();
		try {
			for(String id : recentList) {
				Product product = productDao.getProduct(Integer.parseInt(id));
				if(product != null)
					productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				productDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}
	public String getGoBackPath() throws IOException {
		HttpSession session = request.getSession();
		String goBack = (String)session.getAttribute("goBack");
		if(goBack == null) goBack = "./home";
		return goBack;
	}
	public void updateGoBack() {
		HttpSession session = request.getSession();
		String path = "."+request.getServletPath();
		String queryString = request.getQueryString();
		if(queryString != null) path = path+"?"+queryString;
		session.setAttribute("goBack", path);
	}
	public boolean isAdmin() {
		HttpSession session = request.getSession();
		String accessType = (String)session.getAttribute("accessType");
		if(accessType == null) accessType = "guest";
		if(accessType.equals("admin")) return true;
		else return false;
	}
	/**
	 * @return true if users access type is user
	 */
	public boolean isUser() {
		HttpSession session = request.getSession();
		String accessType = (String)session.getAttribute("accessType");
		if(accessType == null) accessType = "guest";
		if(accessType.equals("user")) return true;
		else return false;
	}
	/**
	 * @return true if users access type is guest
	 */
	public boolean isGuest() {
		HttpSession session = request.getSession();
		String accessType = (String)session.getAttribute("accessType");
		if(accessType == null) accessType = "guest";
		if(accessType.equals("guest")) return true;
		else return false;
	}
	/**
	 * @return true if users access type is vendor
	 */
	public boolean isVendor() {
		HttpSession session = request.getSession();
		String accessType = (String)session.getAttribute("accessType");
		if(accessType == null) accessType = "guest";
		if(accessType.equals("vendor")) return true;
		else return false;
	}
	/**
	 * @return userid of current user.
	 */
	public String getUserId() {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		if(userId == null) userId = "guest";
		return userId;
	}

}
