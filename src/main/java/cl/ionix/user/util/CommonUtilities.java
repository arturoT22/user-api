package cl.ionix.user.util;
/*
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;*/
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
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
	public static String cipher(String param) {
		SecretKeyFactory keyFactory;
		String paramEncripted = null;
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
			DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes(StandardCharsets.UTF_8));
			SecretKey ks = keyFactory.generateSecret(keySpec);

			byte[]  cleartext = param.getBytes(StandardCharsets.UTF_8);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, ks);
			paramEncripted = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return paramEncripted;
	}
}
