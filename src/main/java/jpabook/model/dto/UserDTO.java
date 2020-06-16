package jpabook.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {

	private String username;
	
	private int age;

	@Builder
	public UserDTO(String username, int age) {
		this.username = username;
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", age=" + age + "]";
	}
}
