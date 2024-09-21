package testCases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class Dummy {
	
	public static void main(String[] args) {
		System.out.println(getDateTime());
	}
	
	public static String randomString() {
		return RandomStringUtils.randomAlphabetic(6);
	}
	
	public static String randomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public static String getDateTime() {
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss.a").format(new Date());
		return timeStamp;
	}

}
