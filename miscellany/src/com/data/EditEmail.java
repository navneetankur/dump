package com.data;

 
import java.io.IOException;
import java.io.PrintWriter;

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
	 * Servlet implementation class ChangeEmail
	 */
	@WebServlet("/EditEmail")
	public class EditEmail extends HttpServlet {
		private static final long serialVersionUID = 1L;
		String user_email, userId;
	    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher;
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
				
			user_email=request.getParameter("user_email");
			HttpSession session = request.getSession();
			session.getAttribute("username");
			 String userId = (String) session.getAttribute("username");
				//Cookie[] cookies = request.getCookies();
				//if(cookies!=null){
				//for(Cookie cookie : cookies){
					//if(cookie.getName().equals("userId")) 
					//userId = cookie.getValue();
				
				
				
				DBConnect db=new DBConnect();
				if(db.setEmail(user_email, userId)){					
				 out.println("Updation Success");
					
				}
					else
						out.println("Updation Failed");
		
		dispatcher=request.getRequestDispatcher("./EditEmail.jsp");
		dispatcher.include(request,response);

	}
		}

		




