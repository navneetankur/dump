package com.data;



import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBConnect;

/**
 * Servlet implementation class ChangePass
 */
@WebServlet("/ForgetPass")
public class ForgetPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String password, enp, npass,nepass, userId;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	DBConnect db =new DBConnect();
	password=request.getParameter("password");
	Pass_Encrp en = new Pass_Encrp();
	
	npass=request.getParameter("npass");
	try {
		nepass=(String)en.MD5(npass);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	HttpSession session = request.getSession();				
	 String userId = (String) session.getAttribute("user_id");
	 
		
		db.UpdatePass(userId, nepass);
		System.out.println("Success");
		RequestDispatcher dispatcher;
		dispatcher=request.getRequestDispatcher("./Login.jsp");
		dispatcher.include(request,response);
	}
}


