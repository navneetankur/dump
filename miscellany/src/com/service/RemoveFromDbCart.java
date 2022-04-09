package com.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.DAO.Update;

/**
 * Servlet implementation class RemoveFromDbCart
 */
@WebServlet("/RemoveFromDbCart")
public class RemoveFromDbCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession(false);
		String userId=(String)ses.getAttribute("userId");
		String data =FetchData.fetchCart(userId);
		StringBuffer sb=new StringBuffer(data);
		sb=Remove.removeDup(sb,request.getParameter("id")); 
		Update.updateCart(String.valueOf(sb), userId);
		response.sendRedirect("./DisplayCartDb");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
