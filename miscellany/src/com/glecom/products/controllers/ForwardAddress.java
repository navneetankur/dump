package com.glecom.products.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForwardAddress
 */
@WebServlet("/ForwardAddress")
public class ForwardAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addressId = request.getParameter("address-id");
		if(addressId == null) {
			response.sendRedirect("./AddressBook");
			return;
		}
		request.setAttribute("addressId", addressId);
		request.getRequestDispatcher("./DoOrder").forward(request, response);
	}

}
