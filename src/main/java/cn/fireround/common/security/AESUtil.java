package cn.fireround.common.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * refer to https://www.baeldung.com/java-secure-aes-key
 */
public class AESUtil {



	public static String aesDecrypt(String cipherText, Key key,
	                         IvParameterSpec iv) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
		byte[] plainText = new byte[0];
		try {
			cipherText = cipherText.replaceAll("\n", "");
			plainText = cipher.doFinal(Base64.getDecoder()
				.decode(cipherText));
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
		return new String(plainText);
	}

	public static String aesEncrypt(String input, Key key,
	                                IvParameterSpec iv) {

		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
		byte[] cipherText = new byte[0];
		try {
			cipherText = cipher.doFinal(input.getBytes());
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
		sun.misc.BASE64Encoder base64Encoder = new sun.misc.BASE64Encoder();
		return base64Encoder.encode(cipherText);
	}

	public static Key aesKeyFromString(String encodedKey) {
//		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		return new SecretKeySpec(encodedKey.getBytes(), 0, encodedKey.getBytes().length, "AES");
	}
}
