package com.data;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBConnect;

/**
 * Servlet implementation class qwert
 */
@WebServlet("/qwert")
public class qwert extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	private static final long serialVersionUID = 1L;
	private String userId,password;
	private String enp;
	private boolean userFound;
	private boolean authorize;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("userId")==null){
			response.sendRedirect("./Login.jsp");
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
        	  HttpSession session = request.getSession(true);
              session.setAttribute("userId", userId);
			  session.setAttribute("accessType", accessType);
			  String goBack = (String)session.getAttribute("goBack");
			  if(goBack==null) goBack = "./home";
        	  dispatcher=request.getRequestDispatcher(goBack);
          }
          else{
        	  HttpSession session = request.getSession(true);
              session.setAttribute("userId", "guest");
			  session.setAttribute("accessType", "guest");
			  session.setAttribute("msg", "Invalid ID or Password.");
        	  dispatcher=request.getRequestDispatcher("./Login.jsp");

          }
        	  		dispatcher.forward(request, response);
	}

	}

