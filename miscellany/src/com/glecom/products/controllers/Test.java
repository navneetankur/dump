package com.glecom.products.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glecom.handlers.MailSender;



/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = request.getRequestURL().toString();
		String [] s = url.split("/");
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length-1;i++) {
			sb.append(s[i]+"/");
		}
		System.out.println(sb);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rating = request.getParameter("rating");
		String id = request.getParameter("id");
		System.out.println(rating);
		System.out.println(id);
	}

}
