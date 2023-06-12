package com.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.FetchData;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hash = request.getParameter("hash");
		if(hash==null) {
			response.sendRedirect("./home");
			return;
		}
		String userId = FetchData.getUserIdFromResetHash(hash);
		if(userId == null ){
			request.setAttribute("msg", "This Link has expired");
			request.getRequestDispatcher("./msg-display.jsp").forward(request, response);
			return;
		}
		request.getSession().setAttribute("user_id", userId);
		FetchData.removeForgotPasswordHash(hash);
		request.getRequestDispatcher("./ForgetPass.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
