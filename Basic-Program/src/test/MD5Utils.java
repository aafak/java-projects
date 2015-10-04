package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Utility class to encrypt the plain message by MD5
 * @author Aafak
 */
public class MD5Utils {
	public static String encrypt(String message) throws Exception{
		String givenMessage = message; // Value to encrypt
		System.out.println("givenMessage :"+givenMessage);

		MessageDigest mdigest;
		try {
			mdigest = MessageDigest.getInstance("MD5");// Encryption algorithm
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Error", e);
		}
		mdigest.update(givenMessage.getBytes(), 0, givenMessage.length());
		String encryptedMessage = new BigInteger(1, mdigest.digest()).toString(16); // Encrypted the plain message by md5 to match in DB
		System.out.println("encryptedMessage:"+encryptedMessage);
		return encryptedMessage;
	}
}
