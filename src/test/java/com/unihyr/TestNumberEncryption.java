package com.unihyr;

import com.unihyr.util.IntegerPerm;

public class TestNumberEncryption
{
	public static void main(String[] args)
	{
		int i=IntegerPerm.encipher(12);
		System.out.println();
		int or=IntegerPerm.decipher(i);
		System.out.println();
	}
}
