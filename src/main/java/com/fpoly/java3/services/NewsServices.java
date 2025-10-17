package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.Category;
import com.fpoly.java3.entities.News;
import com.fpoly.java3.entities.User;

public class NewsServices {
// Viết phương thức thêm bài viết vào db

//	CREATE TABLE [dbo].[news] (
//		    [id]          INT            IDENTITY (1, 1) NOT NULL,
//		    [title]       NVARCHAR (500) NOT NULL,
//		    [content]     NTEXT          NULL,
//		    [image]       VARCHAR (50)   NULL,
//		    [create_date] DATETIME2 (7)  DEFAULT (sysutcdatetime()) NOT NULL,
//		    [user_id]     INT            NOT NULL,
//		    [view_count]  INT            DEFAULT ((0)) NOT NULL,
//		    [is_active]   BIT            DEFAULT ((1)) NOT NULL,
//		    [cat_id]      INT            NOT NULL,
//		    PRIMARY KEY CLUSTERED ([id] ASC),
//		    CONSTRAINT [FK_news_categories] FOREIGN KEY ([cat_id]) REFERENCES [dbo].[categories] ([id]),
//		    CONSTRAINT [FK_news_users] FOREIGN KEY ([user_id]) REFERENCES [dbo].[users] ([id])
//		);

	public static boolean addNews(News news) {
		try {
			String sql = "INSERT INTO news VALUES(?, ?, ?, ?, ?, 0, ?, ?)";
			Connection connection = DatabaseConnect.dbConnection();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, news.getTitle());
			statement.setString(2, news.getContent());
			statement.setString(3, news.getImage());
			statement.setDate(4, news.getCreateDate());
			statement.setInt(5, news.getUser().getId());
			statement.setBoolean(6, news.isActive());
			statement.setInt(7, news.getCategory().getId());

			int rows = statement.executeUpdate();

			connection.close();

			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	danh sách bài viết của cá nhân (editer)
	public static List<News> getNewsByUserId(int userId) {
		List<News> newsList = new ArrayList<News>();
//		hiển thị danh sách bài viết có tất cả các thông tin của bài viết
//		và tên người đăng bài, tên danh mục bài đăng
		try {
//			SELECT * FROM news WHERE id=123
//			while()
//			SELECT * users => Dữ liệu luôn luôn giống nhau?
//			SELECT * category (Có 10 danh mục) => Số lượng dữ liệu tra về 
//			giống nhau lớn

			String sql = "SELECT n.id, n.title, n.content, n.image, n.is_active,"
					+ " n.create_date, n.view_count, u.name as auth_name, c.name as cat_name"
					+ " FROM news n JOIN users u ON n.user_id = u.id JOIN categories c ON n.cat_id = c.id"
					+ " WHERE n.user_id=?";
			Connection connection = DatabaseConnect.dbConnection();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getInt("id"));
				news.setTitle(resultSet.getString("title"));
				news.setContent(resultSet.getString("content"));
				news.setImage(resultSet.getString("image"));
				news.setActive(resultSet.getBoolean("is_active"));
				news.setCreateDate(resultSet.getDate("create_date"));
				news.setViewCount(resultSet.getInt("view_count"));
				User user = new User();
				user.setName(resultSet.getString("auth_name"));
				news.setUser(user);
				Category category = new Category();
				category.setName(resultSet.getString("cat_name"));
				news.setCategory(category);

				newsList.add(news);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newsList;
	}

//	danh sách tất cả bài viết (admin)
}
