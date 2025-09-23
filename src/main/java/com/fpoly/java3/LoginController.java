package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Get method");

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		System.out.println("Post method");

//		Lấy giá trị của các ô input mà user vừa nhập vào khi ấn submit 
//		Lấy bằng getParameter 

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		System.out.println("Username: " + username);
		System.out.println("Password: " + password);

//		Trả về hiện trạng ban đầu trước khi submit
//		Trả bằng setAttribute 

		req.setAttribute("username", username);
		req.setAttribute("password", password);

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
