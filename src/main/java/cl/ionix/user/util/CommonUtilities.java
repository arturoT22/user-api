package cl.ionix.user.util;
/*
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;*/
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
/*
	public static String cipherDes(String text) throws UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		byte[] cleartext = text.getBytes("UTF8");
		Cipher cipher = Cipher.getInstance("DES");
		String encryptedText = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
		return encryptedText;
	}*/
}
