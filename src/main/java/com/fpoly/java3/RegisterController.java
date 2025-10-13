package com.fpoly.java3;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.RegisterBean;
import com.fpoly.java3.entities.User;
import com.fpoly.java3.services.UserServices;

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

//			Lấy dữ liệu từ các ô input gán vào beans
//			Kiểm tra lỗi ở các dữ liệu nhận được
//			Hiển thị lỗi nếu có và giá trị đã nhập ở các ô input
			RegisterBean bean = new RegisterBean();

			BeanUtils.populate(bean, req.getParameterMap());

			req.setAttribute("bean", bean);

//			Nếu không có lỗi thực hiện tương tác với sql 

			if (bean.getErrors().isEmpty()) {
				User user = new User();
//				Gán các giá trị ở bean vào entity tuong ứng 
				user.setEmail(bean.getEmail());
				user.setPassword(bean.getPassword());
				user.setName(bean.getName());
				user.setGender(bean.getGender() == 1);
				user.setPhone(bean.getPhone());
				Date birthDay = Date.valueOf(bean.getBirthDay());
				user.setBirthday(birthDay);
				user.setRole(0);

//				UserServices userServices = new UserServices();
				boolean registerCheck = UserServices.register(user);

				if (registerCheck) {
//					đăng ký thành công

//					req.setAttribute("errRegister", "Đăng ký thành công");

					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				} else {
//					đăng ký thất bại 

					req.setAttribute("errRegister", "Có lỗi khi đăng ký tài khoản");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("errRegister", e.getMessage());
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