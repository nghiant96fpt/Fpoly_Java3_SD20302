package com.fpoly.java3.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fpoly.java3.config.DatabaseConnect;
import com.fpoly.java3.entities.User;

public class UserServices {
//	tạo các phương thức thêm sửa xoá cho đối tượng user 

	public static boolean register(User user) throws Exception {
		Connection connection = DatabaseConnect.dbConnection();
//		Kiểm tra email có tồn tại chưa
//		Nếu chưa mới thực hiện đăng ký 

		String checkEmailSQL = "SELECT * FROM users WHERE email= ?";
		PreparedStatement statementCheckEmail = connection.prepareStatement(checkEmailSQL);
		statementCheckEmail.setString(1, user.getEmail());

		ResultSet resultSet = statementCheckEmail.executeQuery();

		if (resultSet.next()) {
//			Email tồn tại 
			connection.close();

			throw new Exception("Email đã tồn tại");
//			== return 
		}

		String insertSQL = "INSERT INTO users(email, password, name, birthday, gender, phone, role) VALUES(?, ?, ?, ?, ?, ?, ?)";

//		Statement => insert db 
//		PreparedStatement
		PreparedStatement statement = connection.prepareStatement(insertSQL);
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getName());
		statement.setDate(4, user.getBirthday());
		statement.setBoolean(5, user.isGender());
		statement.setString(6, user.getPhone());
		statement.setInt(7, user.getRole());

		int rows = statement.executeUpdate();

		connection.close();

		return rows > 0;
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
		try {
			String deleteSQL = "DELETE FROM users WHERE id=?";

			Connection connection = DatabaseConnect.dbConnection();

			PreparedStatement statement = connection.prepareStatement(deleteSQL);
			statement.setInt(1, id);

			boolean insertBoolean = statement.execute();

			connection.close();

			return insertBoolean;

//			PreparedStatement => Kiểm tra giá trị truyền vào có 
//			chứa lệnh của sql hay không?

//			Statement => không kiểm tra giá trị đầu vào 

//			DELETE FROM users WHERE email='abc@gmail.com' OR '1'='1'

//			abc@gmail.com' OR '1'='1

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean changePassword(int id, String newPassword, String oldPassword) {

//		Nếu trùng thì mới thực hiện cập nhật mật khẩu mới 

		try {
//			So sánh mk cũ có trùng với db hay không. Nếu không => false 
//			Password có mã hoá 
			String selectPassword = "SELECT password FORM users WHERE id=?";

			Connection connection = DatabaseConnect.dbConnection();

			PreparedStatement statement = connection.prepareStatement(selectPassword);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String password = resultSet.getString("password");
//				so sánh với oldPassword nếu trùng thì cập nhật newPassword
//				ngược lại trả về false 
				if (!password.equals(oldPassword)) {
					return false;
				}

				String updateSQL = "UPDATE users SET password=? WHERE id = ?";
				PreparedStatement statementUpdate = connection.prepareStatement(updateSQL);
				statementUpdate.setString(1, newPassword);
				statementUpdate.setInt(2, id);

				boolean updateCheck = statementUpdate.execute();
				connection.close();
				return updateCheck;
			}

			connection.close();
//			User id không tồn tại 
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean login(String email, String password) {
		try {
			String sql = "SELECT * FORM users WHERE email=?";

			Connection connection = DatabaseConnect.dbConnection();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String passwordDB = resultSet.getString("password");
				connection.close();
				if (password.equals(passwordDB)) {
					return true;
				} else {
					return false;
				}
			}
			connection.close();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
