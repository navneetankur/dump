package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glecom.products.models.Product;

/**
 * Servlet implementation class Sort
 */
@WebServlet("/Sort")
public class Sort extends HttpServlet  {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Product> productList = (ArrayList<Product>)((Object)request.getParameter("p"));
		System.out.println(productList);
		Collections.sort(productList, Compare.priceComparator);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./product-list.jsp");
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
