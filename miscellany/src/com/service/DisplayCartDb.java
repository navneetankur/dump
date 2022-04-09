package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Servlet implementation class DisplayCartDb
 */
@WebServlet("/DisplayCartDb")
public class DisplayCartDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		if(tracking.isGuest()) {
			response.sendRedirect("./DisplayCart");
			return;
		}
		HttpSession ses = request.getSession(false); 
		String userId= (String) ses.getAttribute("userId");
		int i=0;
		StringBuffer cart = new StringBuffer();
		StringBuffer query=new StringBuffer();
		String data =FetchData.fetchCart(userId);
		ArrayList<Product> list=new ArrayList<>();
		if(data.equals("")){
			tracking.updateGoBack();
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./cart.jsp");
			request.setAttribute("products", list);
			requestDispatcher.forward(request, response);
			return;
		}
		query.append("select * from productc where ");
		
		cart.append(data);
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
	        
	        if(!str[i].equals("")){
    			query.append("(id="+str[i]+")");
    		}
		
		list=FetchData.fetchData(String.valueOf(query));
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
