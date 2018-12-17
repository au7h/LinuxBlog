package nowinski.linuxblog.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringOperations {
	public static String stringToMd5(String data) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		md5.update(data.getBytes());
		BigInteger hash = new BigInteger(1, md5.digest());
		String ready = hash.toString(16);
		if (ready.length() == 31)
			ready = "0" + ready;
		return ready;
	}
	
	public static Date parseDate(String Date) {
		String stringDate = Date;
		try {
			Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
			return parsedDate;
		} catch (ParseException ex) {
			// parseException, return actual date to avoid null pointer exception
			return new Date();
		}
	}
}
