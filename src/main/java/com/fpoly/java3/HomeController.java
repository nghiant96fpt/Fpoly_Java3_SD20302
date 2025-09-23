package com.fpoly.java3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java3.entities.User;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		lấy ra các thông tin khi người dùng truy cập vào trang home
//		Các thông tin sẽ được lấy ra từ request 
//		- Protocol
//		- URL
//		- URI
//		- Context Path
//		- Server name
//		dùng system.out để in các giá trị này ra 

//		String protocol = req.getProtocol();
//		System.out.println("Protocol: " + protocol);

//		String contextPath = req.getContextPath();
//		System.out.println("Context Path: " + contextPath);

//		String titleParam = req.getParameter("title");
//		System.out.println("Title: " + titleParam);

//		lấy các thông tin như protocol, contextPath 
//		Sau đó gửi qua jsp để hiển thị lên giao diện 

		String protocol = req.getProtocol();
		String contextPath = req.getContextPath();

		req.setAttribute("pro", protocol);
		req.setAttribute("ctxPath", contextPath);

//		Tạo 1 đối tượng user
//		email, password, name, gender, phone, birtday, role 
//		Có getter/setter 
//		Ở HomeController thực hiện khởi tạo đối tượng user và gán gtrị 
//		Gửi cả đổi tượng user qua jsp để hiển thị thông tin 

		User user = new User();
		user.setEmail("email@gmail.com");
		user.setPassword("1234567");
		user.setName("Nguyen Van A");
		user.setGender(true);
		user.setPhone("1234567890");

//		Nhập đúng ngày tháng năm sinh của cá nhân 
		Date birthDay = new Date(); // => Ngày hiện tại.
//		03/04/2005
		birthDay.setDate(3);
		birthDay.setMonth(3);
		birthDay.setYear(2025);
		user.setBirthday(birthDay);

		user.setRole(1); // Hiển thị nếu 0 == User || 1 == Admin
		// Gửi 1 đối tượng từ Controller qua JSP thông qua Model
		req.setAttribute("user", user);

//		Tạo 1 mảng user với 3 item 
//		Gửi mảng user qua jsp và hiển thị tên user của 3 item đó 

		User[] users = new User[3];
		users[0] = new User();
		users[0].setName("Nguyen Van Index 0");

		users[1] = new User();
		users[1].setName("Nguyen Van Index 1");

		users[2] = new User();
		users[2].setName("Nguyen Van Index 2");

		req.setAttribute("users", users);

		ArrayList<User> usersArrayList = new ArrayList<User>();

		req.setAttribute("usersArr", usersArrayList);

//		dùng vòng lập for 
//		for (int index = 0; index < users.length; index++) {
//			users[index] = new User();
//			users[index].setName("Nguyen Van Index " + index);
//		}

//		foreach 
//		for(User userTemp : users) {
//			userTemp = new User();
//		}

		// dòng trỏ về giao diện này phải nằm cuối phương thức
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
