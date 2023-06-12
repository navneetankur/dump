package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.DAO.Update;
import com.glecom.products.models.Address;
import com.glecom.products.models.Order;
import com.glecom.products.models.Product;
import com.glecom.products.models.dao.AddressDao;
import com.glecom.products.models.dao.ProductDao;
import com.service.Remove;

/**
 * Servlet implementation class OrderDetails
 */
/**
 * @author navneet
 * sends order details to display after placing order.
 */
@WebServlet("/OrderDetails")
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<Order> orderList = (ArrayList<Order>)session.getAttribute("orders");
		if(orderList == null) {
			response.sendRedirect("./home");
			return;
		}
		ArrayList<Product> productList = new ArrayList<Product>();
		ProductDao productDao = new ProductDao();
		Product product;
		try {
			for(Order order: orderList) {
				product = productDao.getProduct(order.getProductId());
				if(product != null)
					productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				productDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		session.removeAttribute("orders");
		AddressDao addressDao = new AddressDao();
		Address address = null;
		try {
			address = addressDao.getAddress(orderList.get(0).getAddressId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				addressDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(address == null) {
			response.sendRedirect("./home");
			return;
		}
		String userId=(String)session.getAttribute("userId");
		String data =FetchData.fetchCart(userId);
		StringBuffer sb=new StringBuffer(data);
		for (Product product2 : productList) {
			sb=Remove.removeDup(sb,String.valueOf(product2.getId()));
		}
		 
		Update.updateCart(String.valueOf(sb), userId);
		request.setAttribute("address", address);
		request.setAttribute("products", productList);
		request.setAttribute("orders", orderList);
		request.getRequestDispatcher("./order-details.jsp").forward(request, response);
	}

}
