package com.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.FetchData;
import com.DAO.UserBean;
import com.glecom.handlers.MailSender;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if(email == null) {
			request.getRequestDispatcher("./forgot-password.jsp").forward(request, response);
			return;
		}
		ArrayList<UserBean> userList = FetchData.fetchUser("SELECT * FROM c_info WHERE `user_email` = '"+email+"'");
		if(userList.size() == 0){
			request.setAttribute("msg", "That email Id is not registered.");
			request.getRequestDispatcher("./forgot-password.jsp").forward(request, response);
			return;
		}
		UserBean userBean = userList.get(0);
		FetchData.cleanForgotTableForUserId(userBean.getUserId());
		UUID id1 = UUID.randomUUID();
		String hash = id1.toString();
		FetchData.addToForgotPassword(hash, userBean.getUserId());
		request.setAttribute("msg", "Password reset link is sent to your email Id. Please check your spam folder if you can't find it.");
		String url = request.getRequestURL().toString();
		String [] s = url.split("/");
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length-1;i++) {
			sb.append(s[i]+"/");
		}
		sb.append("ResetPassword?hash="+hash);
		String text = "Hello "+userBean.getUserName()+" forgot your password. No worries you can reset it by going to this url "+sb;
		MailSender.SendMail(userBean.geteMail(), "Password reset link", text);
		request.getRequestDispatcher("./msg-display.jsp").forward(request, response);
	}

}
