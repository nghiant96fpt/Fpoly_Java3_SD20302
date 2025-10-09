package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.RegisterBean;

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

		try {
			RegisterBean bean = new RegisterBean();

			BeanUtils.populate(bean, req.getParameterMap());

			req.setAttribute("bean", bean);

			System.out.println(bean.getBirthDay());

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/register.jsp").forward(req, resp);
	}

//	tạo RegisterBean
//	Thực hiện kiểm tra lỗi 
//	Email đúng định dạng
//	Password tối thiểu 6 ký tự
//	Tên không rỗng
//	Giới tính bắt buộc chọn 
//	Số điện thoại bắt đầu bằng 0 và có 10 ký tự
//	Ngày sinh > 18 tuổi
}