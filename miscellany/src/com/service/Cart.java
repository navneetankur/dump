package com.service;

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
 * @author Sanyam tyagi
 * 
 */
/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inCart = "0";
		StringBuffer cart=new StringBuffer();
		HttpSession ses=request.getSession(false);
        Cookie[] cookies = request.getCookies();
        String id=request.getParameter("id");
        if(cookies != null)
        	for(Cookie cookie : cookies)
        		if(cookie.getName().equals("inCart"))
        			inCart=cookie.getValue();		
        if(inCart.equals("1")){
        	if(cookies != null)
            	for(Cookie cookie : cookies)
            		if(cookie.getName().equals("cart"))
            			cart.append(cookie.getValue());
        	

	        	cart.append(","+id);
	        	cart=Remove.remove(cart);
	            //to be used request.getpara
	            Cookie c = new Cookie("cart",String.valueOf(cart));
	            response.addCookie(c);
        }
        else{
        	cart.append(id);
            //to be used request.getpara
            Cookie c = new Cookie("cart",String.valueOf(cart));
            Cookie c1=new Cookie("inCart", "1");
            response.addCookie(c);
            //c = new Cookie("cart",String.valueOf(cart));response.addCookie(c);
            response.addCookie(c1);
        }
        //add farward page
        RequestDispatcher requestDispatcher = request.getRequestDispatcher((String)ses.getAttribute("goBack"));
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
