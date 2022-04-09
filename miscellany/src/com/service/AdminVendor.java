package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.FetchData;
import com.DAO.UserBean;

/**
 * Servlet implementation class AdminVendor
 */
@WebServlet("/AdminVendor")
public class AdminVendor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String query="select * from c_info where access_type='vendor' and active=1";
		ArrayList<UserBean> list = FetchData.fetchUser(String.valueOf(query));
		

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayVendor.jsp");
			request.setAttribute("users", list);
			requestDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
