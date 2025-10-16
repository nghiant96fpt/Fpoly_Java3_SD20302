package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.Category;

public class CategoryServices {
// Viết 1 phương thức lấy danh sách danh mục từ db

//	CREATE TABLE [dbo].[categories] (
//		    [id]   INT            IDENTITY (1, 1) NOT NULL,
//		    [name] NVARCHAR (255) NOT NULL,
//		    PRIMARY KEY CLUSTERED ([id] ASC)
//		);
//

	public static List<Category> getList() {
		List<Category> categories = new ArrayList<Category>();
		try {
			String select = "SELECT * FROM categories";

			Connection connection = DatabaseConnect.dbConnection();
			PreparedStatement statement = connection.prepareStatement(select);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				categories.add(category);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
}
