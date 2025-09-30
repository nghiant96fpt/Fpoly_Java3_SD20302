package com.fpoly.java3;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fpoly.java3.entities.Category;

@MultipartConfig()
@WebServlet("/admin/posts-form")
public class PostsFormController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < 5; index++) {
			Category category = new Category(index + 1, "Category " + (index + 1));
			categories.add(category);
		}

		req.setAttribute("categories", categories);

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < 5; index++) {
			Category category = new Category(index + 1, "Category " + (index + 1));
			categories.add(category);
		}

		req.setAttribute("categories", categories);

		Part part = req.getPart("image");

		if (part == null) {
			System.out.println("Không chọn file");
		} else {
//			tên file được lưu trên máy user 
			System.out.println(part.getSubmittedFileName());
//			Kích thước file được upload lên (byte)
			System.out.println(part.getSize());
//			Loại file được upload 
//			image/png
//			audio/mp3
//			application/pdf
			System.out.println(part.getContentType());

//			25MB => byte
//			int maxSize = 1024 * 1024 * 25;

//			3d => ms
//			int day = 1000 * 60 * 60 * 24 * 3;
		}

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}
}
