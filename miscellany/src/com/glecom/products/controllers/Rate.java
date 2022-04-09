package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glecom.products.models.dao.ProductDao;

/**
 * Servlet implementation class Rate
 */
/**
 * @author navneet
 * sets rating of product.
 */
@WebServlet("/Rate")
public class Rate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		String ratingString = request.getParameter("rating");
		if(idString==null) {
			response.sendRedirect("./home");
			return;
		}
		ProductDao productDao = new ProductDao();
		int id = Integer.parseInt(idString);
		int rating = Integer.parseInt(ratingString);
		int newRating = 0;
		try {
			int previousRating = productDao.getRating(id);
			if(previousRating == -1) {
				response.sendRedirect("./home");
				return;
			}
			int numberOfRatings = productDao.getNumberOfRatings(id);
			newRating = ((previousRating * numberOfRatings) + rating)/(numberOfRatings + 1);
			productDao.setRating(id , newRating);
			productDao.setNumberOfRatings(id, numberOfRatings+1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				productDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.getWriter().print(newRating);
	}

}
