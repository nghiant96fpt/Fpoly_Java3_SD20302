package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.LoginBeans;
import com.fpoly.java3.entities.User;
import com.fpoly.java3.services.UserServices;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Login Get method");

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		try {
			LoginBeans beans = new LoginBeans();

			BeanUtils.populate(beans, req.getParameterMap());

			req.setAttribute("bean", beans);

			if (beans.getErrors().isEmpty()) {
				User user = UserServices.login(beans.getUsername(), beans.getPassword());

				if (user != null) {
					System.out.println("Đăng nhập thành công");

//					3 vai trò
//					role == 0 => User
//					role == 1 => Editer
//					role == 2 => Admin

//					làm sao để lấy ra được thông tin userId và role của user

//					Cách 1: Đổi giá trị trả về của login => User || null
//					Cách 2: Viết thêm 1 services lấy thông tin user theo email

//					if (user.getRole() == 0) {
//						resp.sendRedirect(req.getContextPath() + "/home");
//						return;
//					}
//
//					if (user.getRole() == 1) {
//						resp.sendRedirect(req.getContextPath() + "/editer/posts");
//						return;
//					}
//
//					if (user.getRole() == 2) {
//						resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
//						return;
//					}

//					Thực hiện lưu userid và role vào Cookie 

					Cookie cookieUserId = new Cookie("userId", String.valueOf(user.getId()));
					cookieUserId.setMaxAge(60 * 60 * 24 * 7); // giây => 7day
//					cookieUserId.setPath("/admin/*");
//					cookieUserId.setHttpOnly(false)

					Cookie cookieRole = new Cookie("role", String.valueOf(user.getRole()));
					cookieRole.setMaxAge(60 * 60 * 24 * 7); // giây => 7day

					resp.addCookie(cookieUserId);
					resp.addCookie(cookieRole);

					switch (user.getRole()) {
					case 0:
						resp.sendRedirect(req.getContextPath() + "/home");
						return;
					case 1:
						resp.sendRedirect(req.getContextPath() + "/editer/posts");
						return;
					case 2:
						resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
						return;
					}

//					3 dòng 

//					String[] paths = { "/home", "/editer/posts", "/admin/dashboard" };
//					resp.sendRedirect(req.getContextPath() + paths[user.getRole()]);
//					return;

				} else {
					req.setAttribute("errLogin", "Email hoặc mật khẩu không đúng");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

//		System.out.println("Post method");
//
////		Lấy giá trị của các ô input mà user vừa nhập vào khi ấn submit 
////		Lấy bằng getParameter 
//
////		Check email 
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//
//		System.out.println("Username: " + username);
//		System.out.println("Password: " + password);
//
////		Trả về hiện trạng ban đầu trước khi submit
////		Trả bằng setAttribute 
//
//		req.setAttribute("username", username);
//		req.setAttribute("password", password);

		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
