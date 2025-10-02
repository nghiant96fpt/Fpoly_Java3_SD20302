package com.fpoly.java3.beans;

import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsFormBeans {
	private String title;
	private String desc;
	private Part image;
	private int category;
	private int status;

//	public Map<String, String> getErrors() {
//		key = tên biến đang kiểm tra
//		value = nội dung lỗi 
//		if tiêu đề rỗng
//		map.put("title", "Tiêu đề không rỗng")

//		return map
//	}

//	jsp => beans.errors.title // Hiển thị giá trị lỗi
}
