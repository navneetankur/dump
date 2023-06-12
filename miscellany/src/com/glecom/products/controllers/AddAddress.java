package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glecom.handlers.Tracking;
import com.glecom.products.models.Address;
import com.glecom.products.models.dao.AddressDao;

/**
 * Servlet implementation class AddAddress
 */
/**
 * @author navneet
 * used for adding new address.
 */
@WebServlet("/AddAddress")
public class AddAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tracking tracking = new Tracking(request, response);
		String name = request.getParameter("name");
		if(name==null) {
			request.getRequestDispatcher("./add-address.jsp").forward(request, response);
			return;
		}
		String userId = null;
		if(!tracking.isGuest()) {
			userId = tracking.getUserId();
		}
		String phone = request.getParameter("phone");
		String addressString = request.getParameter("address");
		String pincode = request.getParameter("pincode");
		Address addressObj = new Address(name, addressString, pincode, phone);
		AddressDao addressDao = new AddressDao();
		try {
			if(userId != null) {
				addressObj.setUserId(userId);
				addressDao.addUserAddress(addressObj);
			}
			else {
				addressDao.addGuestAddress(addressObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				addressDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("addressId", ""+addressObj.getId());
		request.getRequestDispatcher("./AddressBook").forward(request, response);
	}

}
