package com.gocabs.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {

	private String usernameOrEmail;
	private String password;
	
	@Override
    public String toString() {
        return "Login{" +
                "username='" + usernameOrEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
