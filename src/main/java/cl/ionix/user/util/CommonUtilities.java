package cl.ionix.user.util;

import java.security.SecureRandom;
import java.util.Base64;

public class CommonUtilities {

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	private CommonUtilities(){}

	public static String generateToken() {
		var randomBytes = new byte[50];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}
}
