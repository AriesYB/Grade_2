package getSubset;

/*仅针对正整数的二进制*/
public class Binary {
	private String numCode;
	private int length;
	Binary(int num)//十进制转二进制
	{
		String str="";
		if(num==0)
		{
			str="0";
		}
		while(num!=0)//TODO 扩充范围
		{
			str=String.valueOf(num%2)+str;
			num=(num-num%2)/2;
		}
		this.numCode=str;
		this.length=str.length();
	}
	
	Binary(String str)//形如000的二进制
	{
		this.numCode=str;
		this.length=String.valueOf(Integer.parseInt(str)).length();
	}
	
	public String getBinCode()
	{
		return this.numCode;
	}
	
	public String getBinCode(int bits)//得到确定位数的二进制码
	{
		if(bits<this.numCode.length())
		{
			return this.numCode.substring(this.numCode.lastIndexOf(bits));
		}
		while(this.numCode.length() < bits)
		{
			this.numCode = "0" + this.numCode ;
		}
		return this.numCode;
	}
	
	public int length()
	{
		return this.length;
	}
	
	public static String add(String str1,String str2)
	{
		while(str1.length() > str2.length())//ͬ高位补零
		{
			str2 = "0"+ str2;
		}
		while(str1.length() < str2.length())
		{
			str1 = "0"+ str1;
		}
		int[] arr1 = new int[str1.length()];
		int[] arr2 = new int[str1.length()];
		int[] arr3 = new int[str1.length()+2];
		int[] arr4 = new int[str1.length()+1];
		char[] a = str1.toCharArray();
		char[] b = str2.toCharArray();
		for (int i = 0; i < arr1.length; i++) 
		{
			arr1[i]=Integer.parseInt(String.valueOf(a[i]));
			arr2[i]=Integer.parseInt(String.valueOf(b[i]));
		}
		for (int i = arr1.length-1; i > -1; i--)
		{
			arr4[i+1]=(arr1[i]+arr2[i]+arr3[i+1])%2;
			arr3[i]+=(arr1[i]+arr2[i]+arr3[i+1])/2;
		}
		String st="";
		for (int i = 0; i < arr4.length; i++) 
		{
			if (arr4[i]==0)
			{
				for (int j = i+1; j < arr4.length; j++) 
				{
					st += arr4[j];
				}
				break;
			}
		}
		
		return st;
	}

}
