package com.fpoly.java3;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.PostsFormBeans;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.services.CategoryServices;

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
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}
}
