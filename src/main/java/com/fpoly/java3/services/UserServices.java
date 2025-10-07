package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.User;

public class UserServices {
//	tạo các phương thức thêm sửa xoá cho đối tượng user 

	public boolean insert(User user) {
		try {
			String insertSQL = "INSERT INTO users(email, password, name, birthday, gender, phone, role) VALUES(?, ?, ?, ?, ?, ?, ?)";

//			Statement => insert db 
//			PreparedStatement

			Connection connection = DatabaseConnect.dbConnection();
			PreparedStatement statement = connection.prepareStatement(insertSQL);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setDate(4, user.getBirthday());
			statement.setBoolean(5, user.isGender());
			statement.setString(6, user.getPhone());
			statement.setInt(7, user.getRole());

			boolean insertBoolean = statement.execute();

			connection.close();

			return insertBoolean; // true || false
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateInfo(User user) {
		try {
			String updateSQL = "UPDATE users SET name=?, birthday=?, gender=?, phone=? WHERE id = ?";

			Connection connection = DatabaseConnect.dbConnection();
			PreparedStatement statement = connection.prepareStatement(updateSQL);
			statement.setString(1, user.getName());
			statement.setDate(2, user.getBirthday());
			statement.setBoolean(3, user.isGender());
			statement.setString(4, user.getPhone());
			statement.setInt(5, user.getId());

			boolean insertBoolean = statement.execute();

			connection.close();

			return insertBoolean; // true || false
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteUser(int id) {

	}

	public boolean changePassword(int id, String newPassword, String oldPassword) {
//		So sánh mk cũ có trùng với db hay không. Nếu không => false 
//		Nếu trùng thì mới thực hiện cập nhật mật khẩu mới 
	}

//	CREATE TABLE [dbo].[users] (
//		    [id]       INT            IDENTITY (1, 1) NOT NULL,
//		    [email]    VARCHAR (50)   NOT NULL,
//		    [password] VARCHAR (50)   NOT NULL,
//		    [name]     NVARCHAR (255) NULL,
//		    [birthday] DATE           NULL,
//		    [gender]   INT            NULL,
//		    [phone]    VARCHAR (10)   NULL,
//		    [role]     INT            NOT NULL,
//		    CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED ([id] ASC)
//		);
//

}
