//package com.unihyr.constraints;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
///**
//  Used to convert Number in words
//  @Author Rohit Tiwari
// */
//public class numbertowordindian {
//	public static void main(String args[]) throws Exception {
//String word=numToWordIndian("123456");
//	System.out.println(word);
//	}
//
//	public static String numToWordIndian(String  N){
//
//		char arr[] = N.toCharArray();
//		int l = arr.length;
//		String word="";
//		switch (l) {
//		case 9: {
//			word=fortwo(arr, 0,word);
//			if(arr[0]!='0'|| arr[1]!='0')
//				word=	place(l,word);
//			fortwo(arr, 2,word);
//			if(arr[2]!='0'|| arr[3]!='0')
//				word=	place(l - 2,word);
//			word=fortwo(arr, 4,word);
//			if(arr[4]!='0'|| arr[5]!='0')
//				word=	place(l - 4,word);
//			word=forthree(arr, 6,word);
//		}
//			break;
//		case 8: {
//			word=singleplace(arr[0],word);
//			if(arr[0]!='0')
//				word=	place(l,word);
//			word=fortwo(arr, 1,word);
//			if(arr[1]!='0'|| arr[2]!='0')
//				word=	place(l - 1,word);
//			word=	fortwo(arr, 3,word);
//			if(arr[3]!='0'|| arr[4]!='0')
//				word=		place(l - 3,word);
//			word=	forthree(arr, 5,word);
//		}
//			break;
//		case 7: {
//			word=	fortwo(arr, 0,word);
//			if(arr[0]!='0'|| arr[1]!='0')
//				word=	place(l,word);
//			word=fortwo(arr, 2,word);
//			if(arr[2]!='0'|| arr[3]!='0')
//				word=place(l - 2,word);
//			word=forthree(arr, 4,word);
//		}
//			break;
//		case 6: {
//			word=singleplace(arr[0],word);
//			if(arr[0]!='0')
//				word=place(l,word);
//			word=fortwo(arr, 1,word);
//			if(arr[1]!='0'|| arr[2]!='0')
//				word=place(l - 1,word);
//			word=forthree(arr, 3,word);
//		}
//			break;
//		case 5: {
//			word=fortwo(arr, 0,word);
//			if(arr[0]!='0'|| arr[1]!='0')
//				word=place(l,word);
//			word=forthree(arr, 2,word);
//		}
//			break;
//		case 4: {
//			word=singleplace(arr[0],word);
//			if(arr[0]!='0')
//				word=place(l,word);
//			word=forthree(arr, 1,word);
//		}
//			break;
//		case 3: {
//			word=forthree(arr, 0,word);
//		}
//			break;
//		case 2:
//			word=fortwo(arr, 0,word);
//			break;
//
//		case 1:
//			word=singleplace(arr[0],word);
//			break;
//
//		default:
//			System.out.println("Invalid Input");
//		}
//
//
//		return word;
//	}
//
//	static String fortwo(char arr[], int l,String word) {
//		word=tensplace(arr[l], arr[l + 1],word);
//		if (arr[l] != '1' && arr[l] != '0')
//			word=singleplace(arr[l + 1],word);
//		return word;
//	}
//
//	static String forthree(char arr[], int l, String word) {
//		word=singleplace(arr[l],word);
//		if(arr[l]!='0')
//			word=place(3,word);
//		word=fortwo(arr, l + 1,word);
//
//		return word;
//	}
//
//	static String place(int l, String word) {
//
//		switch (l) {
//		case 8:
//		case 9:
//		{
//			word=word+"Crores ";
//			System.out.print("Crores ");
//			break;
//		}
//		case 6:
//		case 7:
//		{
//			word=word+"lacs ";
//			break;
//		}case 4:
//		case 5:
//		{
//			word=word+"Thousand ";
//			break;
//		}case 3:
//		{
//			word=word+"Hundred ";
//			break;
//		}
//		}
//		return word;
//	}
//
//	static String singleplace(int n,String word) {
//		switch (n) {
//		case '1':
//			word=word+"One ";
//			break;
//		case '2':
//			word=word+"Two ";
//			break;
//		case '3':
//			word=word+"Three ";
//			break;
//		case '4':
//			word=word+"Four ";
//			break;
//		case '5':
//			word=word+"Five ";
//			break;
//		case '6':
//			word=word+"Six ";
//			break;
//		case '7':
//			word=word+"Seven ";
//			break;
//		case '8':
//			word=word+"Eight ";
//			break;
//		case '9':
//			word=word+"Nine ";
//			break;
//		}
//		return word;
//	}
//
//	static String onetensplace(int n2,String word) {
//		switch (n2) {
//		case '0':
//			word=word+"Ten ";
//			break;
//		case '1':
//			word=word+"Eleven ";
//			break;
//		case '2':
//			word=word+"Twelve ";
//			break;
//		case '3':
//			word=word+"Thirteen ";
//			break;
//		case '4':
//			word=word+"Fourteen ";
//			break;
//		case '5':
//			word=word+"Fifteen ";
//			break;
//		case '6':
//			word=word+"Sixteen ";
//			break;
//		case '7':
//			word=word+"Seventeen ";
//			break;
//		case '8':
//			word=word+"Eighteen ";
//			break;
//		case '9':
//			word=word+"Nineteen ";
//			break;
//		}
//		return word;
//	}
//
//	static String tensplace(int n1, int n2,String word) {
//		switch (n1) {
//		case '0':
//			word=singleplace(n2,word);
//			break;
//		case '1':
//			word=onetensplace(n2,word);
//			break;
//		case '2':
//			word=word+"Twenty ";
//			break;
//		case '3':
//			word=word+"Thirty ";
//			break;
//		case '4':
//			word=word+"Forty ";
//			break;
//		case '5':
//			word=word+"Fifty ";
//			break;
//		case '6':
//			word=word+"Sixty ";
//			break;
//		case '7':
//			word=word+"Seventy ";
//			break;
//		case '8':
//			word=word+"Eighty ";
//			break;
//		case '9':
//			word=word+"Ninety ";
//			break;
//		}
//		return word;
//	}
//}