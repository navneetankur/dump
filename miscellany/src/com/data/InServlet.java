package com.data;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.DAO.Update;
import com.database.DBConnect;
import com.glecom.products.models.dao.SessionDao;
import com.service.Remove;

/**
 * Servlet implementation class InServlet
 */
@WebServlet("/InServlet")
public class InServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String userId,password;
	private String enp;
	private boolean userFound;
	private boolean authorize;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	    HttpSession session = request.getSession(true);

		String goBack=(String)session.getAttribute("goBack");
		if(goBack==null)goBack="./home";
		if(request.getParameter("userId")==null){
			response.sendRedirect(goBack);
			return;
		}
          userId=request.getParameter("userId");
          password=request.getParameter("password");
          String accessType="guest";
                    
          Pass_Encrp en = new Pass_Encrp();
			try {
				 enp = en.MD5(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          DBConnect db = new DBConnect();
          ResultSet rst = db.getUser(userId);
          try{
        	  if(!rst.next()) {
        		  userFound=false;
//        		  System.out.println("user not found");
        	  }
        	  else {
        		  userFound=true;
        		  accessType=rst.getString("access_type");
//        		  System.out.println("user identified");
        	  }
        	  
          }catch (SQLException e){
        	  
        	  e.printStackTrace();        	 
          }
          if(userFound){
        	  try{
        		  authorize=enp.equals(rst.getString("enp"));
        		  
        	  }catch(SQLException e){
        		  
        		  e.printStackTrace();
        	  }
          }
          else authorize = false;
          db.close();
          RequestDispatcher dispatcher;
          if(authorize){
        	   session = request.getSession(true);
              session.setAttribute("userId", userId);
			  session.setAttribute("accessType", accessType);
			   goBack = (String)session.getAttribute("goBack");
			  if(goBack==null) goBack = "./home";
			  String rememberMe = request.getParameter("remember-me");
			  if(rememberMe != null) {
				  if(rememberMe.equals("true")) {
					  SessionDao sessionDao = new SessionDao();
					  try {
						sessionDao.doRememberMe(request, response, userId, accessType);
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
			  }
			  //sanyam
		      Cookie[] cookies = request.getCookies();
		      StringBuffer cart=new StringBuffer();
			  if(cookies != null)
	            	for(Cookie cookie : cookies)
	            		if(cookie.getName().equals("cart"))
	            			cart.append(cookie.getValue());
			  if(cart!=null){
				  String data =FetchData.fetchCart(userId);	
				  cart.append(","+data);
				  cart=Remove.remove(cart);
				  Update.updateCart(String.valueOf(cart), userId);
				  cart=new StringBuffer();
				  cart.append("0");
				  Cookie c = new Cookie("cart",String.valueOf(cart));
		            response.addCookie(c);
				  
			}
				  response.sendRedirect(goBack);
			  
			  
          }
          else{
              session.setAttribute("userId", "guest");
			  session.setAttribute("accessType", "guest");
			  session.setAttribute("msg", "Invalid ID or Password.");
			  response.sendRedirect(goBack);
				return;
          }
        	  		
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
}