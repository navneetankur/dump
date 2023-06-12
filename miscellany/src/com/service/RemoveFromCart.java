package com.service;
/**
 * @author Sanyam tyagi
 * 
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer cart=new StringBuffer();
		String c;
		HttpSession ses=request.getSession(false);
        Cookie[] cookies = request.getCookies();
		if(cookies != null)
        	for(Cookie cookie : cookies)
        		if(cookie.getName().equals("cart"))
        			cart.append(cookie.getValue());
		cart=Remove.removeDup(cart,request.getParameter("id")); 
		request.setAttribute("id", request.getParameter("id"));
		Cookie cook = new Cookie("cart",String.valueOf(cart));
        response.addCookie(cook);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayCart");
        //response.setHeader("refresh", "0.1");
		requestDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
