package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.News;

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

	public boolean addNews(News news) {
		try {
			String sql = "INSERT INTO news VALUES(null, ?, ?, ?, ?, ?, 0, ?, ?)";
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
}
