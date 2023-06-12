package com.service;

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
import com.glecom.handlers.Tracking;
import com.glecom.products.models.Product;
/**
 * @author Sanyam tyagi
 * 
 */
/**
 * Servlet implementation class DisplayWishlist
 */
@WebServlet("/DisplayWishlist")
public class DisplayWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		HttpSession ses = request.getSession(false); 
		String userId= (String) ses.getAttribute("userId");
		if(userId!=null){
			int i=0;
			StringBuffer wishlist = new StringBuffer();
			StringBuffer query=new StringBuffer();
			String data =FetchData.fetchWishlist(userId);
			ArrayList<Product> list=new ArrayList<>();
			if(data.equals("")){
				tracking.updateGoBack();
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./wishlist.jsp");
				request.setAttribute("products", list);
				requestDispatcher.forward(request, response);
				return;
			}
			query.append("select * from productc where ");
			
			wishlist.append(data);
			 String []str=(String.valueOf(wishlist)).split(",");
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
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./wishlist.jsp");
				request.setAttribute("products", list);
				requestDispatcher.forward(request, response);
			
		}
		else{
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("./wishlist.jsp");
				requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
