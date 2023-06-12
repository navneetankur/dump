package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class VendorViewProduct
 */
@WebServlet("/VendorViewProduct")
public class VendorViewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request,response);
		PrintWriter out=response.getWriter();
		String userId=null;
		/*Cookie[] cookies = request.getCookies();
        if(cookies != null)
        	for(Cookie cookie : cookies)
        		if(cookie.getName().equals("userId"))
        			userId = cookie.getValue();*/
		HttpSession ses = request.getSession(false);
		userId=(String) ses.getAttribute("userId");
		String query="select * from productc where vendor='"+userId+"'";
		//Product p=new Product();
		ArrayList<Product> productList=new ArrayList<>();
		productList=FetchData.fetchData(query);
		tracking.updateGoBack();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./VendorProductDisplay.jsp");
			request.setAttribute("products", productList);
			requestDispatcher.forward(request, response);
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	}
