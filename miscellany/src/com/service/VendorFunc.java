package com.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.Update;


@WebServlet("/VendorFunc")
public class VendorFunc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(false);
		String id ="vendor";
		String type=(String)ses.getAttribute("accessType");
		String user = (String) ses.getAttribute("userId");
		if(type.equalsIgnoreCase("user")){
			ses.setAttribute("accessType", id);
			type=(String)ses.getAttribute("accessType");
			Update.update(type, user);
			/*RequestDispatcher rd= request.getRequestDispatcher((String)ses.getAttribute("goBack"));
			rd.forward(request, response);*/
			response.sendRedirect("./vendor-verification-dummy.jsp");
		}
		
		else if(type.equalsIgnoreCase("guest")) {
			ses = request.getSession(true);
			ses.setAttribute("accessType",id);
			RequestDispatcher rd= request.getRequestDispatcher("./Registration.html");
			rd.forward(request, response);
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
