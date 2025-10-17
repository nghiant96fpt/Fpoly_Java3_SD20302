package com.fpoly.java3;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.PostsFormBeans;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.News;
import com.fpoly.java3.entities.User;
import com.fpoly.java3.services.CategoryServices;
import com.fpoly.java3.services.NewsServices;

@MultipartConfig()
@WebServlet("/editer/posts-form")
public class PostsFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category> categories = CategoryServices.getList();

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

//		Quản lý danh sẽ do admin quản lý
		List<Category> categories = CategoryServices.getList();

		req.setAttribute("categories", categories);

		try {
			PostsFormBeans beans = new PostsFormBeans();

			BeanUtils.populate(beans, req.getParameterMap());
//			Chỉ convert giá trị string, int, boolean,...

			Part part = req.getPart("image");
			beans.setImage(part);

			req.setAttribute("beans", beans);

			if (beans.getErrors().isEmpty()) {
//				TODO 
//				Lưu ảnh vào project => tên ảnh
//				Lưu các thông tin của bài viết và tên ảnh vào DB

				String type = beans.getImage().getContentType().split("/")[1];
				String name = String.valueOf(new Date().getTime()) + "." + type;

				System.out.println(name);

//				1759644669994.png, 1759644669994.jpg
//				1759644669994.jpge, 1759644669994.webp

//				image/png, image/jpg
//				getContentType()

//				Định dạng file *.png, *.jpg,....

//				Đường đẫn lưu file trong project
				String path = "/assets/images/" + name;

//				Đừng đẫn cứng lưu file bên trong server tomcat
				String tomcatPath = req.getServletContext().getRealPath(path);

//				Lưu file vào đường đãn cứng của tomcat
				beans.getImage().write(tomcatPath);

				System.out.println(tomcatPath);

//				Chuyển các dữ liệu từ bean vào entity
//				Chuyển tên ảnh đã lưu vào 
//				Lấy id user từ cookie để set vào entity
//				Sau đó mới gọi services 

//				Thread.sleep(10);

//				Lưu thông tin vào db 
//				Hiển thị bài viết ra danh sách dạng table (Quản lý)

//				Cần biết tài khoản đang đăng nhập là user nào? 
//				userId => Lưu => lấy từ cookies 

				Cookie[] cookies = req.getCookies();

//				Không cần kiểm tra vì đã biết được chắc chắn có Cookie 
//				if(cookies != null) {
//					
//				}

				String userId = "";

				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("userId")) {
						userId = cookie.getValue();
						break;
					}
				}

//				Convert dữ liệu từ bean => entity
				News news = new News();
				news.setTitle(beans.getTitle());
				news.setContent(beans.getDesc());
				news.setImage(name);
//				Kỹ thuật design pattern của java
//				Instance class 
//				"" + var + ""
				Calendar calendar = Calendar.getInstance();
				String today = String.format("%s-%s-%s", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
						calendar.get(Calendar.DAY_OF_MONTH));
				news.setCreateDate(java.sql.Date.valueOf(today));
				User user = new User();
//				Chắc chắn user có giá trị của id 
				user.setId(Integer.parseInt(userId));
				news.setUser(user);
				news.setViewCount(0);
				Category category = new Category();
				category.setId(beans.getCategory());
				news.setCategory(category);
				news.setActive(beans.getStatus() == 1);

				boolean insertNews = NewsServices.addNews(news);

				if (insertNews) {
					req.setAttribute("errNews", "Thêm bài viết thành công");
				} else {
					req.setAttribute("errNews", "Thêm bài viết thất bại");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errNews", "Thêm bài viết thất bại");
		}

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}
}
