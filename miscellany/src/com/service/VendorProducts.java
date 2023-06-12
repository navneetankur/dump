package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.glecom.products.models.Product;

/**
 * Servlet implementation class VendorProducts
 */
@WebServlet("/VendorProducts")
public class VendorProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		String userId=(String) request.getParameter("userId");
		String query="select * from productc where vendor='"+userId+"'";
		ArrayList<Product> productList=new ArrayList<>();
		productList=FetchData.fetchData(query);
	
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
