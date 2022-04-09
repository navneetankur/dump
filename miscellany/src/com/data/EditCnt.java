package com.data;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.DBConnect;


@WebServlet("/EditCnt")
public class EditCnt  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String user_contact, userId;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			
		user_contact=request.getParameter("user_contact");
	
		HttpSession session = request.getSession();		
		String userId = (String) session.getAttribute("username");
		//Cookie[] cookies = request.getCookies();
			//if(cookies!=null){
			//for(Cookie cookie : cookies){
				//if(cookie.getName().equals("userId")) 
				//userId = cookie.getValue();
			
			
			
			DBConnect db=new DBConnect();
			if(db.setContact(user_contact, userId)){
				out.println("Updation Success");
				
			}
				else
					out.println("Updation Failed");
	
	dispatcher=request.getRequestDispatcher("./EditCnt.jsp");
	dispatcher.include(request,response);

}
	}



