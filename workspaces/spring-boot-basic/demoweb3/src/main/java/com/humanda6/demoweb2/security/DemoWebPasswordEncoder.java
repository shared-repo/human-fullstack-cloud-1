package com.humanda6.demoweb2.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class DemoWebPasswordEncoder implements PasswordEncoder {

	private static final String ALGORITHM = "SHA-256";
	
	@Override
	public String encode(CharSequence rawPassword) {
		String passwd = rawPassword.toString();
		// return Util.getHashedString(passwd, ALGORITHM);
		return passwd;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
//		String hashedPassword = Util.getHashedString(rawPassword.toString(), ALGORITHM);
//		return hashedPassword.equals(encodedPassword);
		
		return encodedPassword.equals(rawPassword.toString());
	}
	
}
