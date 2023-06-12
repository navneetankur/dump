package com.data;


	import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpRetryException;
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

import com.database.DBConnect;

	/**
	 * Servlet implementation class ChangePass
	 */
	@WebServlet("/EditPass")
	public class EditPass  extends HttpServlet {
		private static final long serialVersionUID = 1L;
		String password, enp, npass,nepass, userId;

		
		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DBConnect db =new DBConnect();
		HttpSession ses=request.getSession(false);
        userId=(String)ses.getAttribute("userId");
		password=request.getParameter("password");
		Pass_Encrp en = new Pass_Encrp();
		try {
			enp =(String) en.MD5(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rst = db.getUser(userId);
		boolean userFound=false;
        try{
      	  
		if(!rst.next()) {
      		  userFound=false;
      	  }
      	  else {
      		  userFound=true;
      		  
      	  }
      	  
        }catch (SQLException e){
      	  
      	  e.printStackTrace();        	 
        }
        if(!userFound){
        	RequestDispatcher dispatcher;
			dispatcher=request.getRequestDispatcher("./ChangePassword.jsp");
			request.setAttribute("msg", "wrong");
			dispatcher.forward(request, response);
			return;
        }
		npass=request.getParameter("npass");
		try {
			nepass=(String)en.MD5(npass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			  HttpSession session = request.getSession();				
			  String userId = (String) session.getAttribute("userId");
			
			db.UpdatePass(userId, nepass);
			System.out.println("Success");
			db.close();
			RequestDispatcher dispatcher;
			dispatcher=request.getRequestDispatcher("./MyProfile.jsp");
			dispatcher.forward(request, response);
		}
	}


