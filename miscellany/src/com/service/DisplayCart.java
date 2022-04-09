package com.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.glecom.handlers.Tracking;
import com.glecom.products.models.Product;
/**
 * @author Sanyam tyagi
 * 
 */
/**
 * Servlet implementation class DisplayCart
 */
@WebServlet("/DisplayCart")
public class DisplayCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public boolean found = false;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		HttpSession ses =request.getSession(false);
	
		if(!tracking.isGuest()) {
			response.sendRedirect("./DisplayCartDb");
			return;
		}
        Cookie[] cookies = request.getCookies();
        StringBuffer cart=new StringBuffer();
		if(cookies != null)
        	for(Cookie cookie : cookies)
        		if(cookie.getName().equals("cart")) {
        			cart.append(cookie.getValue());
        			found = true;
        		}
		if(request.getAttribute("id")!=null){
			cart=Remove.removeDup(cart,(String)request.getAttribute("id")); 
		}
		if(!found||String.valueOf(cart).length()==0) {
			 ArrayList<Product> li=new ArrayList<Product>();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./cart.jsp");
			request.setAttribute("products", li);
			requestDispatcher.forward(request, response);
			return;
		}
		StringBuffer query=new StringBuffer();
        int i=0;
        query.append("select * from productc where ");
        String []str=(String.valueOf(cart)).split(",");
    	ArrayList<String> string=new ArrayList<String>(Arrays.asList(str));
        ses.setAttribute("productIdList", string);
        if(str.length>1){
        	for (i = 0; i < str.length-1; i++) {
        		if(!str[i].equals("")){
        			query.append("(id="+str[i]+")or");
        		}
        	}
        }
        else{
        	str[0]=String.valueOf(cart);
        }
        if(!str[i].equals("")){
            query.append("(id="+str[i]+")");
        }
        String q=String.valueOf(query);
        System.out.println(q);
        ArrayList<Product> list=FetchData.fetchData(q);
        tracking.updateGoBack();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./cart.jsp");
		request.setAttribute("products", list);
		requestDispatcher.forward(request, response);
		
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
