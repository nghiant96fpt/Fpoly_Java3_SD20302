package com.fpoly.java3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.java3.services.NewsServices;

@WebServlet("/editer/post-delete")
public class PostDeleteController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		Cookie[] cookies = req.getCookies();
		String userId = "";

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userId = cookie.getValue();
				break;
			}
		}

		boolean checkDelete = NewsServices.deleteNews(Integer.parseInt(id), Integer.parseInt(userId));

		resp.sendRedirect(req.getContextPath() + "/editer/posts?status=" + (checkDelete ? 1 : 0));
//		return ?
	}
}

// doGet => truyền bằng url => user có thấy ?
// localhost/editer/delete?id=1 => tag <a/>

//script for => chay 1 => 1000 

// delete => post => input id (form)