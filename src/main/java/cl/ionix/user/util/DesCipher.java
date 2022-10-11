package cl.ionix.user.util;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class DesCipher {
    public static String cipher(String plainRut) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8")); SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); byte[] cleartext = plainRut.getBytes("UTF8");
        Cipher cipher = Cipher.getInstance("DES");
        String encryptedRut = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));

        return encryptedRut;}

}