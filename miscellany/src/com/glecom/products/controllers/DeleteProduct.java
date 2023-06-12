package com.glecom.products.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glecom.products.models.dao.ProductDao;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao productDao = new ProductDao();
		if(request.getParameter("delete")!=null)
			try {
				productDao.deleteProduct(request.getParameter("delete"));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					productDao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//		ArrayList<Product> productList = productDao.getAll();
//		request.setAttribute("productList", productList);
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./VendorProducts");
//		requestDispatcher.forward(request, response);
		HttpSession ses=request.getSession();
		if(ses.getAttribute("accessType").equals("admin")){
			response.sendRedirect("./VendorProducts");
			return;
		}
		response.sendRedirect("./VendorViewProduct");
	}

}
