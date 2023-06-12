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
 * Servlet implementation class AdminUsers
 */
@WebServlet("/AdminUsers")
public class AdminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int countUser=0;
		String dob;
		int age=0,year=2015;
		String query="select * from c_info where access_type='user'";
		ArrayList<UserBean> list = FetchData.fetchUser(String.valueOf(query));
		ArrayList<UserBean> list1 = new ArrayList<UserBean>();
		ArrayList<UserBean> list2 = new ArrayList<UserBean>();
		ArrayList<UserBean> list3 = new ArrayList<UserBean>();
		ArrayList<UserBean> list4 = new ArrayList<UserBean>();
		ArrayList<UserBean> list5 = new ArrayList<UserBean>();
		if(list.size()==0){
			out.print("NO USERS");
			return;
		}
		for(UserBean user:list) {
			countUser++;
			dob=user.getDob();
			System.out.println(dob);
			String []a=dob.split("/");
			if(a[2]!=null){
			age=year-Integer.valueOf(a[2]);
			}
			if(age<15){
				list1.add(user);
			}
			else if(age>15&&age<23){
				list2.add(user);
			}
			else if(age>=23&&age<35){
				list3.add(user);
			}
			else if(age >=35&&age<50){
				list4.add(user);
			}
			else if(age>=50){
				list5.add(user);
			}
		}
//		ArrayList<ArrayList> l= new ArrayList<>();
//		l.add(list1);
//		l.add(list2);
//		l.add(list3);
//		l.add(list4);
//		l.add(list5);
//		
//		
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./usercat.jsp");
//			request.setAttribute("users", l);
//			requestDispatcher.forward(request, response);
//		System.out.println(list1);
//		System.out.println(list2);
//		System.out.println(list3);
//		System.out.println(list4);
//		System.out.println(list5);
		int ch=Integer.valueOf(request.getParameter("ch"));
		if(ch==1){
			request.setAttribute("users", list1);
		}
		else if(ch==2){
			request.setAttribute("users", list2);
		}
		else if(ch==3){
			request.setAttribute("users", list3);
		}
		else if(ch==4){
			request.setAttribute("users", list4);
		}
		else if(ch==5){
			request.setAttribute("users", list5);
		}
		request.setAttribute("countUser", countUser);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./ViewOrders");
		requestDispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
