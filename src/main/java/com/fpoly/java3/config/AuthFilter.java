package com.fpoly.java3.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/admin/*", "/editer/*", "/user/*" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		0 == User
//		1 == Editer
//		2 == Admin

//		Lấy userid và role từ cookie ra 
//		Kiểm tra nếu userid và role có ít nhất 1 giá trị null => Trả về trang login 
//		Nếu cả 2 khác null
//		role == 0 && path bắt đầu bằng /user/ => Cho đi tiếp qua controller
//		role == 1 && path bắt đầu bằng /editer/ => Cho đi tiếp qua controller
//		role == 2 && path bắt đầu bằng /admin/ => Cho đi tiếp qua controller
//		Ngược lại thì trở về trang login

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();

//		TH: Cookie không tồn tại || chưa đăng nhập
		if (cookies == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String userID = null;
		String role = null;
//		Lấy giá trị user id và role ở cookie 
		for (Cookie cookie : cookies) {
//			cookie.getName() => key 
			if (cookie.getName().equals("userId")) {
				userID = cookie.getValue();
			}

			if (cookie.getName().equals("role")) {
				role = cookie.getValue();
			}
		}

		if (userID == null || role == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

//		role == 0 && path bắt đầu bằng /user/ => Cho đi tiếp qua controller
//		role == 1 && path bắt đầu bằng /editer/ => Cho đi tiếp qua controller
//		role == 2 && path bắt đầu bằng /admin/ => Cho đi tiếp qua controller

		String path = req.getRequestURI();
//		/ctx/path

		if (path.contains("/user/") && !role.equals("0") && !role.equals("1")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		if (path.contains("/editer/") && !role.equals("1")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		if (path.contains("/admin/") && !role.equals("2")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		// cho chạy tiếp qua servlet => Next
		chain.doFilter(request, response);
	}

}
