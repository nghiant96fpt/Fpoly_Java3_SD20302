package com.fpoly.java3.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsFormBeans {
	private int id = 0;
	private String title;
	private String desc;
	private Part image;
	private int category;
	private int status;

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

//		Tiều đề không rỗng
//		Mô tả phải có từ 10 -> 300 ký tự
//		Danh mục bắt buộc chọn
//		Trạng thái bắt buộc chọn
//		File: Phải là ảnh, kích thước tối đa 20KB 
//		Viết trước những câu lệnh if 

//		if(title rỗng ){
//			TODO 
//		}

		if (title.isBlank()) {
			map.put("errTitle", "Tiêu đề không rỗng");
		}

//		String[] descArr = desc.trim().split(" ");
//		descArr.length > 300

//		[01, 01, 2025].join("/") => 01/01/2025

		if (desc.trim().length() < 10 || desc.trim().length() > 300) {
			map.put("errDesc", "Mô tả phải từ 10 đến 300 ký tự");
		}

		if (category < 1) {
			map.put("errCat", "Danh mục bắt buộc chọn");
		}

		if (status == 0) {
			map.put("errStatus", "Trạng thái bắt buộc chọn");
		}

		int maxSize = 1024 * 1024 * 100;

		if (image == null) {
			map.put("errImage", "Ảnh bắt buộc chọn");
		} else if (!image.getContentType().startsWith("image/")) {
			map.put("errImage", "File upload phải là ảnh");
		} else if (image.getSize() > maxSize) {
			map.put("errImage", "File upload có kích thước tối đa 20kb");
		}

		return map;
	}

//	public Map<String, String> getErrors() {
//		key = tên biến đang kiểm tra
//		value = nội dung lỗi 
//		if tiêu đề rỗng
//		map.put("title", "Tiêu đề không rỗng")

//		return map
//	}

//	jsp => beans.errors.title // Hiển thị giá trị lỗi

}
