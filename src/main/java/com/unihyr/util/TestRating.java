//package com.unihyr.util;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.unihyr.domain.PostConsultant;
//
//public class TestRating
//{
//	public static void main(String[] args)
//	{
//		int[] arr={4,0,0,0,0,0};
//		int count=0,ii=0,ll=0;
//		Map<String, Integer> pccList=new LinkedHashMap<String,Integer>();
//		double val=0;
//		for (int i=arr.length-1;i>=0;i--)
//		{
//			if(arr[i]>val)
//			{
//				for (int jj = i+ll; jj >= i; jj--)
//				{
//					pccList.put((jj+1)+"", arr.length-ii-1);
//				}
//				count=ii;
//
//				pccList.put((i+1)+"", arr.length-ii-1);
//				val=arr[i];
//			}else{
//				ll++;
//			}
//			ii++;
//		}
//		for (Map.Entry<String, Integer> entry : pccList.entrySet())
//		{
//			String postConsultant = (String)entry.getKey();
//			count=entry.getValue();
//			System.out.println(count*100/arr.length);
//		}
//
//
//
//	}
//
//	/* for all 3 asc orders
//
//
//
//		// TODO Auto-generated method stub
//		int[] arr={0,0,1,3,3,3,4};
//		int count=0,ii=0,jj=1;
//		Map<String, Integer> pccList=new LinkedHashMap<String,Integer>();
//		double val=0;
//		for (int i=0;i<arr.length;i++)
//		{
//			if(arr[i]>val)
//			{
//				count=ii;
//				pccList.put((i+1)+"", ii);
//				val=arr[i];
//			}else{
//				pccList.put((i+1)+"", count);
//			}
//			ii++;
//		}
//
//		for (Map.Entry<String, Integer> entry : pccList.entrySet())
//		{
//			String postConsultant = (String)entry.getKey();
//			count=entry.getValue();
//			System.out.println(count*100/arr.length);
//		}
//
//
//
//
//
//
//
//
//
//	 */
//
//}
