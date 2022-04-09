package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glecom.handlers.Tracking;
import com.glecom.products.models.Feedback;
import com.glecom.products.models.dao.FeedbackDao;

/**
 * Servlet implementation class AddFeedback
 */
/**
 * @author navneet
 * used for adding new comment.
 */
@WebServlet("/AddFeedback")
public class AddFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String productId, name, title, comment;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		productId = request.getParameter("product-id");
		if(productId == null) {
			response.sendRedirect(tracking.getGoBackPath());
			return;
		}
		name = request.getParameter("name");
		title = request.getParameter("title");
		comment = request.getParameter("comment");
		FeedbackDao feedbackDao = new FeedbackDao();
		try {
			feedbackDao.addFeedback(new Feedback(Integer.parseInt(productId), name, title, comment));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				feedbackDao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("./ProductDisplay?product-id="+productId);
	}
	

}
