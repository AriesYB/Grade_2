package seqList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args)
	{
		SeqList<String> seqList = new SeqList<String>();
		//读文件
		String str= null;
		String str1="";
		
		FileReader fr=null;
		BufferedReader bufr=null;
		try {
			fr= new FileReader("D:/test1.txt");
			bufr= new BufferedReader (fr);
			while ((str=bufr.readLine())!=null)
			{
				str1+=str;
			}
			bufr.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		StringTokenizer st = new StringTokenizer(str1, ",");
		while (st.hasMoreElements())
		{
			seqList.add((String) st.nextElement());
		}
		
		System.out.println("是否为空:"+seqList.isEmpty());
		System.out.println("大小:"+seqList.size());
		System.out.println("现在查找元素“呵呵he");
		System.out.println("它的位置是"+seqList.find("呵呵he"));
		System.out.println();
		for (int i = 0; i < seqList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+seqList.get(i));
		}
		System.out.println();
		System.out.println("现在删除第2个元素、“呵呵he”");
		seqList.remove(2);
		seqList.remove("呵呵he");
		
		for (int i = 0; i < seqList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+seqList.get(i));
		}
		System.out.println();
		System.out.println("现在在第二个位置添加元素“嘻嘻");
		seqList.add("嘻嘻",2);
		
		for (int i = 0; i < seqList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+seqList.get(i));
		}
		
		System.out.println();
		System.out.println("现在修改元素2为“哈哈");
		seqList.set(1, "哈哈");
		for (int i = 0; i < seqList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+seqList.get(i));
		}
	}

}
