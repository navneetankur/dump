package com.service;
/**
 * @author Sanyam tyagi
 * 
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.Update;

/**
 * Servlet implementation class UpdateUserProfile
 */
@WebServlet("/UpdateUserProfile")
public class UpdateUserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String user_name, user_email,  user_contact,userId,dob,sex;
		HttpSession ses = request.getSession(false);
		userId=(String)ses.getAttribute("userId");
		user_name=request.getParameter("user_name");
		user_email=request.getParameter("user_email");
		user_contact=request.getParameter("user_contact");
		dob=request.getParameter("bday");
		sex=request.getParameter("sex");
		Update.updateProfile(user_name,user_email,user_contact,dob,sex,userId);
		RequestDispatcher rd=request.getRequestDispatcher("./MyProfile.jsp");
		rd.forward(request, response);
	
	}

}
