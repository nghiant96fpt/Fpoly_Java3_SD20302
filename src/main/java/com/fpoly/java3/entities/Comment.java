package com.fpoly.java3.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
	private int id;
	private String content;
	private String image;
	private Date createDate;
	private User user;
	private News news;
}
