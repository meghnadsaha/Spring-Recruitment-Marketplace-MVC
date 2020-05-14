package com.unihyr.constraints;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to perform validations.
 * @author Rohit Tiwari
 *
 */
public class Validation
{
	/**
	 * used to check pattern of email is valid or not
	 * @param email contains email which is supposed to validate
	 * @return either true or false
	 */
	public static boolean isValidEmailAddress(String email) 
	{
	   String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	   Pattern pattern = Pattern.compile(regex);
	   Matcher matcher = pattern.matcher(email);

	   return matcher.matches();
	}
	
		public static void main(String... args)throws Exception{
			isValidEmailAddress("rt@dfg.f.@g");
			long x=1<<32;
			System.out.println(x);
			
			BigInteger big=new BigInteger(1000, 8, Random.class.newInstance());
			//System.out.println(big.);
			System.out.println(BigInteger.probablePrime(1000, Random.class.newInstance()));
	        Integer a = 2;
	        Field valField = a.getClass().getDeclaredField("value");
	        valField.setAccessible(true);
	        valField.setInt(a, 5);
	        Integer c = 1;
	        Integer b = 2;
	        System.out.println("b+c : " + (b+c) ); // b+c : 4
		}
	
}
