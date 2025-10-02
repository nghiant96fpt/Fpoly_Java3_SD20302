package com.fpoly.java3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Hàm xây dựng không tham số
@AllArgsConstructor // Hàm xây dựng đầy đủ tham số
@Data // getter/setter
//@Getter
//@Setter
public class Category {
	private int id;
	private String name;
}
