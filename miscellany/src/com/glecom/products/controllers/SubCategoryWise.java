package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glecom.handlers.Tracking;
import com.glecom.products.models.Product;
import com.glecom.products.models.dao.ProductDao;

/**
 * Servlet implementation class SubCategoryWise
 */
/**
 * @author navneet
 * sends list of product from given subcategory to display.
 */
@WebServlet("/SubCategoryWise")
public class SubCategoryWise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String category, subCategory;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		category = request.getParameter("category");
		subCategory = request.getParameter("sub-category");
		if(subCategory == null) { 
			response.sendRedirect("./home");
			return;
		}
		tracking.updateGoBack();
		ProductDao productDao = new ProductDao();
		ArrayList<Product> productList = null;
		try {
			productList = productDao.getFromSubCategory(category, subCategory, 12);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				productDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./product-list.jsp");
		request.setAttribute("products", productList);
		requestDispatcher.forward(request, response);
	}

}
