package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glecom.handlers.Tracking;
import com.glecom.products.models.Order;
import com.glecom.products.models.dao.OrderDao;

/**
 * Servlet implementation class DoOrder
 */
/**
 * @author navneet
 * updates the order table with current order.
 */
@WebServlet("/DoOrder")
public class DoOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order;
		Tracking tracking = new Tracking(request, response);
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<String> productsOrderedIdList = (ArrayList<String>)session.getAttribute("productIdList");
		if(productsOrderedIdList == null) {
			response.sendRedirect("./home");
			return;
		}
		String addressIdString = (String)request.getAttribute("addressId");
		if(addressIdString==null) {
			response.sendRedirect("./home");
			return;
		}
		int addressId = Integer.parseInt(addressIdString);
		OrderDao orderDao = new OrderDao();
		ArrayList<Order> orderList = new ArrayList<Order>();
		String timestamp;
		int pId;
		try {
			for(String productId : productsOrderedIdList) {
				timestamp = new Timestamp(new Date().getTime()).toString();
				if(productId==null) continue;
				try {
					pId=Integer.parseInt(productId);
					if(pId==0)continue;
					order = new Order(tracking.getUserId(), pId, addressId ,timestamp);
				} catch (NumberFormatException e) {
					/*System.out.println("number exception");*/
					continue;
				}
				orderDao.addOrder(order);
				orderList.add(orderDao.getOrderFromTimestamp(order.getTimestamp()));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				orderDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		session.removeAttribute("productIdList");
		session.setAttribute("orders", orderList);
		response.sendRedirect("./Payment");
	}

}
