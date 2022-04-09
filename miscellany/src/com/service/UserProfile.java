package com.service;
/**
 * @author Sanyam tyagi
 * 
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.DAO.UserBean;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses=request.getSession(false);
		String userId=(String)ses.getAttribute("userId");
		if(userId!=null){
			String q="select * from c_info where userId='"+userId+"'";
			ArrayList<UserBean> list = FetchData.fetchUser(q);
			request.setAttribute("users", list);
			RequestDispatcher rd=request.getRequestDispatcher("./EditProfile.jsp");
			rd.forward(request, response);
		}
		else{
			String goBack=(String)request.getAttribute("goBack");
			if(goBack==null)goBack="./home";
			RequestDispatcher rd=request.getRequestDispatcher(goBack);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
