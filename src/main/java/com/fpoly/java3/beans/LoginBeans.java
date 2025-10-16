package com.fpoly.java3.beans;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginBeans {
	private String username;
	private String password;

	public Map<String, String> getErrors() {
		Map<String, String> map = new HashMap<String, String>();

		if (!username.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			map.put("errUsername", "Error");
		}

		if (password.isEmpty()) {
			map.put("errPassword", "Error Password");
		}

		return map;
	}
}
