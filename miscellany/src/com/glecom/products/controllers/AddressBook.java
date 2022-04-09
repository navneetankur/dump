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
import com.glecom.products.models.Address;
import com.glecom.products.models.dao.AddressDao;

/**
 * Servlet implementation class AddressBook
 */
/**
 * @author navneet
 * address book of user.
 */
@WebServlet("/AddressBook")
public class AddressBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		if(tracking.isGuest()) {
			request.setAttribute("msg", "Please login to order");
			request.getRequestDispatcher("./msg-display.jsp").forward(request, response);
			return;
		}
		String userId = tracking.getUserId();
		AddressDao addressDao = new AddressDao();
		ArrayList<Address> addresslist = null;
		try {
			addresslist = addressDao.getForUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				addressDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(addresslist == null) {
			addresslist = new ArrayList<Address>();
		}
		request.setAttribute("addressList", addresslist);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./address-book.jsp");
		dispatcher.forward(request, response);
	}

}
