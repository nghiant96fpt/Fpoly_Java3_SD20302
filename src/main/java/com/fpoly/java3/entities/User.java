package com.fpoly.java3.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Hàm xây dựng không tham số
@AllArgsConstructor // Hàm xây dựng đầy đủ tham số
@Data // getter/setter
//@Getter
//@Setter
public class User {
	private int id;
	private String email;
	private String password;
	private String name;
	private boolean gender;
//	true == Nam
//	false == Nữ 
	private String phone;
	private Date birthday;
	private int role;
//	0 == User
//	1 == Editer
//	2 == Admin
}
