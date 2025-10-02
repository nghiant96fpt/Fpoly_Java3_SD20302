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

import org.apache.commons.beanutils.BeanUtils;

import com.fpoly.java3.beans.PostsFormBeans;
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
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		ArrayList<Category> categories = new ArrayList<Category>();

		for (int index = 0; index < 5; index++) {
			Category category = new Category(index + 1, "Category " + (index + 1));
			categories.add(category);
		}

		req.setAttribute("categories", categories);

		Part part = req.getPart("image");

		try {
			PostsFormBeans beans = new PostsFormBeans();

			BeanUtils.populate(beans, req.getParameterMap());

			req.setAttribute("beans", beans);

		} catch (Exception e) {
			// TODO: handle exception
		}

		req.getRequestDispatcher("/posts-form.jsp").forward(req, resp);
	}
}
