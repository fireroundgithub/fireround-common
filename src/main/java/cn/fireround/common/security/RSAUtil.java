package cn.fireround.common.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAUtil {

	public static String rsaWithPublicKey(PublicKey publicKey, byte[] content) {
		Cipher encryptCipher = null;
		try {
			encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
		try {
			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
		byte[] encryptedMessageBytes = new byte[0];
		try {
			encryptedMessageBytes = encryptCipher.doFinal(content);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
		sun.misc.BASE64Encoder base64Encoder = new sun.misc.BASE64Encoder();
		String encodedMessage = base64Encoder.encode(encryptedMessageBytes);
		return encodedMessage;
	}

	public static String rsaWithPrivateKey(PrivateKey privateKey, byte[] encryptedMessageBytes) {
		Cipher decryptCipher = null;
		try {
			decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		}
		try {
			decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
		byte[] decryptedMessageBytes = new byte[0];
		try {
			decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
		String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
		return decryptedMessage;
	}

}
