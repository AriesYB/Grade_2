package getSubset;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;


public class GetSubsets {

	public static void main(String[] args) {
		System.out.println("请输入集合的元素以英文逗号隔开");
		String str = new Scanner(System.in).next();
		String[] element = str.split(",");
		ArrayList<ArrayList<String>> subsets = new ArrayList<ArrayList<String>>();
		ArrayList<String> sub = new ArrayList<String>();
		Binary bin0 = new Binary(0);
		String binCode0 = bin0.getBinCode(element.length);//得到与元素个数一致的0000...
		Binary bin1 = new Binary((int) (Math.pow(2, element.length)-1));
		String binCode1 = bin1.getBinCode();
		for (int i = 0; i < element.length; i++) 
		{					
			sub.add(element[i]);
		}
		subsets.add(sub);
		while(!binCode0.equals(binCode1))//遍历取元素
		{
			ArrayList<String> subset = new ArrayList<String>();//此处若使用subset.clear() 输出全为所有元素？？？
			int j = 0;
			for (int i = 0; i < binCode1.length(); i++) 
			{
				if (binCode0.substring(i, i+1).equals("1")) 
				{
					subset.add(element[i]);
					j = 1000;
				}
			}
	
			if(j==0)
			{
				subset.add("∅");
			}
			subsets.add(subset);
			binCode0=Binary.add(binCode0, "1");
//			System.out.print(binCode0+" ");
		}
		
		
		for (int i = 0; i < subsets.size()-1; i++)	//冒泡排序(要-1)
		{
			for (int j = 0; j < subsets.size()-1-i; j++)
			{
				if(subsets.get(j).size() > subsets.get(j+1).size())
				{
					ArrayList<String> al = subsets.get(j+1);
					subsets.set(j+1, subsets.get(j));
					subsets.set(j, al);
				}
			}
		}
		
		System.out.println("一共有"+subsets.size()+"个子集");
		System.out.print("{");
		for (ArrayList<String> arrayList : subsets)
		{
			System.out.print("{"+String.join(",", arrayList)+"}"+" ");
		}
		System.out.print("}");
	}
}
