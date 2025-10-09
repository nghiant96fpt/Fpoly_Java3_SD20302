package com.fpoly.java3.beans;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterBean {
	private String email;
	private String password;
	private String name;
	private int gender;
	private String phone;
	private String birthDay;

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

		try {
			String[] birthDayArr = birthDay.split("-");
//			=> ["2025", "10", "07"]

			int year = Integer.parseInt(birthDayArr[0]);
			System.out.println();

			Calendar calendar = Calendar.getInstance();

			if (calendar.get(Calendar.YEAR) - year < 18) {
				map.put("errBirthDay", "Tuổi phải lớn hơn 18");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errBirthDay", "Tuổi phải lớn hơn 18");
		}

		if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
			map.put("errEmail", "Email không đúng định dạng");
		}

		if (password.length() < 6) {
			map.put("errPassword", "Mật khẩu tối thiểu 6 ký tự");
		}

		if (name.isBlank()) {
			map.put("errName", "Tên không được rỗng");
		}

		if (gender == 0) {
			map.put("errGender", "Giới tính bắt buộc chọn");
		}

		if (!phone.matches("^0\\d{9}$")) {
			map.put("errPhone", "Số điện thoại không đúng định dạng");
		}

		return map;
	}
}
