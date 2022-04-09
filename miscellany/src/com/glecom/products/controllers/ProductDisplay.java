package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glecom.handlers.Tracking;
import com.glecom.products.models.Feedback;
import com.glecom.products.models.Product;
import com.glecom.products.models.dao.FeedbackDao;
import com.glecom.products.models.dao.ProductDao;

/**
 * Servlet implementation class ProductDisplay
 */
/**
 * @author navneet
 * sends details of a single product to display.
 */
@WebServlet("/ProductDisplay")
public class ProductDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Product product;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		String id = request.getParameter("product-id");
		if(id == null) { 
			response.sendRedirect("./home");
			return;
		}
		ProductDao productDao = new ProductDao();
		FeedbackDao feedbackDao = new FeedbackDao();
		ArrayList<Feedback> feedbackList = null;
		ArrayList<Product> similarProductList = null;
		try {
			product = productDao.getProduct(Integer.parseInt(id));
			if(product == null) {
				response.sendRedirect("./home");
				return;
			}
			feedbackList = feedbackDao.getAllForProductId(product.getId());
			similarProductList = productDao.getFromSubCategory(product.getCategory(),product.getSubCategory(),4);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				productDao.close();
				feedbackDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ArrayList<Product> recentlyViewedProductList = tracking.getRecentlyViewed();
		HashSet<Product> set = new HashSet<Product>(recentlyViewedProductList);
		recentlyViewedProductList = new ArrayList<Product>(set);
		request.setAttribute("product", product);
		request.setAttribute("feedbacks", feedbackList);
		request.setAttribute("similars", similarProductList);
		request.setAttribute("recents", recentlyViewedProductList);
		tracking.updateGoBack();
		RequestDispatcher dispatcher = request.getRequestDispatcher("./product-display.jsp");
		dispatcher.forward(request, response);
	}

}
