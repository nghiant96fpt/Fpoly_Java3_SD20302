package com.fpoly.java3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java3.entities.News;
import com.fpoly.java3.services.NewsServices;

@WebServlet("/editer/posts")
public class PostListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Cookie[] cookies = req.getCookies();
			String userId = "";

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					userId = cookie.getValue();
					break;
				}
			}

			List<News> news = NewsServices.getNewsByUserId(Integer.parseInt(userId));

			req.setAttribute("news", news);

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/posts-list.jsp").forward(req, resp);
	}
}
