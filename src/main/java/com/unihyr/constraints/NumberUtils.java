package com.unihyr.constraints;
/**
 * used to generate amount in words 
 * @author Rohit Tiwari
 *
 */
public class NumberUtils
{// This snippet may be used freely, as long as the authorship note remains in
	// the source code.

	private static final String[] lowNames =
	{ "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };

	private static final String[] tensNames =
	{ "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

	private static final String[] bigNames =
	{ "thousand", "million", "billion" };
	
	public static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	
	/**
	 * Converts an integer number into words (american english).
	 * 
	 * @author Christian d'Heureuse, Inventec Informatik AG, Switzerland,
	 *         www.source-code.biz
	 **/
	public static String convertNumberToWords(int n)
	{
		if (n < 0)
		{
			return "minus " + convertNumberToWords(-n);
		}
		if (n <= 999)
		{
			return convert999(n);
		}
		String s = null;
		int t = 0;
		while (n > 0)
		{
			if (n % 1000 != 0)
			{
				String s2 = convert999(n % 1000);
				if (t > 0)
				{
					s2 = s2 + " " + bigNames[t - 1];
				}
				if (s == null)
				{
					s = s2;
				} else
				{
					s = s2 + ", " + s;
				}
			}
			n /= 1000;
			t++;
		}
		return s;
	}

	// Range 0 to 999.
	private static String convert999(int n)
	{
		String s1 = lowNames[n / 100] + " hundred";
		String s2 = convert99(n % 100);
		if (n <= 99)
		{
			return s2;
		} else if (n % 100 == 0)
		{
			return s1;
		} else
		{
			return s1 + " " + s2;
		}
	}

	// Range 0 to 99.
	private static String convert99(int n)
	{
		if (n < 20)
		{
			return lowNames[n];
		}
		String s = tensNames[n / 10 - 2];
		if (n % 10 == 0)
		{
			return s;
		}
		return s + " " + lowNames[n % 10];
	}
	/**
	 * Used to convert Integer number into comma seprated string
	 * @param num data type String, which will be converted into commoa seprated string
	 * @return comma seprated string
	 */
	public static String convertNumberToCommoSeprated(Double digits){
		String num=Integer.toString((Double.valueOf(digits)).intValue());
		char[] arr=num.toCharArray();
 		String word="";
 		int l=arr.length;
		switch (num.length())
		{
		case 9:{
			for(int i=0;i<arr.length-3;i=i+2){
				word+=word+""+arr[i]+""+arr[i+1]+",";
			}
			word+=arr[2]+""+arr[1]+""+arr[0];
			break;
		}
		case 8:{
			word=word+arr[0]+",";
			for(int i=1;i<arr.length-3;i=i+2){
				word+=word+""+arr[i]+""+arr[i+1]+",";
			}
			word+=arr[l-3]+""+arr[l-2]+""+arr[l-1];
			break;
		}
		case 7:{
			word=arr[0]+""+arr[1]+","+arr[2]+""+arr[3]+","+arr[4]+""+arr[5]+""+arr[6];
			break;
		}
		case 6:{
			word=arr[0]+","+arr[1]+""+arr[2]+","+arr[3]+""+arr[4]+""+arr[5];
			break;
		}
		case 5:{
			word=arr[0]+""+arr[1]+","+arr[2]+""+arr[3]+""+arr[4];
			break;
		}
		case 4:{
			word+=arr[0]+","+arr[1]+""+arr[2]+""+arr[3];
			break;
		}
		case 3:{

			word+=arr[0]+""+arr[1]+""+arr[2];;
			break;
		}
		case 2:{
			
			word+=arr[0]+""+arr[1];
			break;
		}
		case 1:{
			
			word+=arr[0];
			break;
		}
		default:
			break;
		}
		
		return word;
	}
}
