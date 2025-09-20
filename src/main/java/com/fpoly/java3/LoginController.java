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

		req.getRequestDispatcher("/login.jsp").forward(req, resp);

//		Bổ sung 1 giao diện đăng nhập
//		2 ô input: tên tài khoản, mật khẩu
//		Button: đăng nhập 
//		Webservlet("/login") sẽ trỏ về file jsp vừa tạo 
	}
}
