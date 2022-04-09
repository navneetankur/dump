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
 * Servlet implementation class GetProductList
 */
/**
 * @author navneet
 * gives 12 random products to display page.
 */
@WebServlet("/GetProductList")
public class GetProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		ProductDao productDao = new ProductDao();
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			productList = productDao.getRandomProducts(12);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				productDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		tracking.updateGoBack();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./product-list.jsp");
		request.setAttribute("products", productList);
		requestDispatcher.forward(request, response);
	}

}
