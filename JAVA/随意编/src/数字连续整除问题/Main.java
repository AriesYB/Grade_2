package 数字连续整除问题;

public class Main {
	
	private static int count=0;	//记录移动数字的次数
	private static int[] list= {1,2,3,4,5,6,7,8,9};
//	private static int[] list= {1,2,3,4};
	
	private static long getNum(int[] arr,int n)	//得到从0到n的数字组合成的数
	{
		String numCode="";
		for (int i = 0; i <= n; i++) 
		{
			numCode+=arr[i];
		}
		return Long.parseLong(numCode);
	}
	
	private static void reSort(int[] arr,int n)	//输入数组和序列n，把n之后的所有值都向前移动1个单位
	{
		count++;
		int a=arr[n];
		for (int i = n; i < arr.length-1; i++) 
		{
			arr[i]=arr[i+1];
		}
		arr[arr.length-1]=a;
	}
	
	private static void backTrack(int[] arr,int n)	//获取数字重复后进行前一位的变换
	{
		if(count==arr.length-n)	//已经复原了,返回上一层操作
		{
			reSort(arr,n-1);	//回到上一层操作一次
			count=0;
			f(n-1);
		}
	}
	
	private static void f(int i)
	{
		if(getNum(list,i)%(i+1)!=0)	//不能整除
		{
			reSort(list, i);
			backTrack(list, i);
		}else
		{
			count=0;
			f(i+1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		f(0);
//		for (int i = 0; i < list.length; i++)	//i位数
//		{	
//			while(getNum(list, i) % (i + 1) != 0) //判断整除	
//			{
//				backTrack(list,i);
//				reSort(list, i);
//				
//			}
			
//			{
//				if (index == list.length - i) //移动的数开始出现重复
//				{
//					reSort(list, i); //进行复原
//					index = 0; //清空次数
//					i--;
//					break;
//				}
//			} 
//		}
		
		
//		String numCode="";
//		int a=0;
//		int index=0;
//		long num=0;
		
//		for (int i = 0; i < list.length; i++)
//		{
//			index++;
//			for (int j = 0; j <= i; j++) 
//			{
//				numCode+=list[j];
//			}
//			num=Long.parseLong(numCode);
//			if (num%numCode.length()!=0)
//			{
//				i--;
//				list[list.length-1]=list[i];
//				for (int k = i+1; k < list.length; k++) 
//				{
//					list[k-1]=list[k];
//				}
//				if (index==list.length-i)
//				{
//					
//				}
//			}
//			numCode="";
//			a++;
//			System.out.println(a);
//		}
		for (int number : list)	//打印数组元素
		{
			System.out.println(number);
		}
		
	}
}
