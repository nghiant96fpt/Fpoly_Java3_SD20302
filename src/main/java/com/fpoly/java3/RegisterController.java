package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
//		lấy giá trị trong các ô input của form 
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String phone = req.getParameter("phone");
		String birthDay = req.getParameter("birthDay");

//		Gửi giá trị lấy được về hiển thị thông tin ở form 
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		req.setAttribute("name", name);
		req.setAttribute("gender", gender);
		req.setAttribute("phone", phone);
		req.setAttribute("birthDay", birthDay);

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}
}