package encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import utils.FileUtils;

public class EncryptDecryptStringWithDES {

	public static void main(String[] args) throws IOException, InvalidKeySpecException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String encrypted = encrypt(FileUtils.readLineByLineJava8("dll3"));
		FileUtils.strToFile("dll3encrypted", encrypted);
	}

	public static String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		byte[] byteKey = { 34, 78, 14, -73, 96, 89, -39, -20 };
		SecretKeySpec key = new SecretKeySpec(byteKey, "DES");
		Cipher ecipher = Cipher.getInstance("DES");
		ecipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] utf8 = str.getBytes("UTF8");
		byte[] enc = ecipher.doFinal(utf8);
		enc = BASE64EncoderStream.encode(enc);
		return new String(enc);

	}

	public static String decrypt(String str) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

		byte[] byteKey = { 34, 78, 14, -73, 96, 89, -39, -20 };
		SecretKeySpec key = new SecretKeySpec(byteKey, "DES");
		Cipher dcipher = Cipher.getInstance("DES");
		dcipher.init(Cipher.DECRYPT_MODE, key);
		byte[] dec = BASE64DecoderStream.decode(str.getBytes());
		byte[] utf8 = dcipher.doFinal(dec);
		return new String(utf8, "UTF8");

	}

}