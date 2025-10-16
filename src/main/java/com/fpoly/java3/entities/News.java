package com.fpoly.java3.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {
	private int id;
	private String title;
	private String content;
	private String image;
	private Date createDate;
	private User user;
	private int viewCount;
	private Category category;
	private boolean isActive;
}