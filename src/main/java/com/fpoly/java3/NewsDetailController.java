package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java3.entities.News;
import com.fpoly.java3.services.NewsServices;

@WebServlet("/news-detail")
public class NewsDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String newsId = req.getParameter("id"); // => urrl

			String userId = "0";
			Cookie[] cookies = req.getCookies();

			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("userId")) {
						userId = cookie.getValue();
						break;
					}
				}
			}

//			if (Integer.parseInt(userId) == 0) {
////				Chưa đăng nhập và chưa yêu thích 
//			}

			News news = NewsServices.getNewsById(Integer.parseInt(newsId), Integer.parseInt(userId));

			req.setAttribute("news", news);

//			if (news.isFav()) {
////				Có yêu thích
//			} else {
////				Chưa yêu thích
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("/news-detail.jsp").forward(req, resp);
	}
}
