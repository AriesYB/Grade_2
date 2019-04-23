package linkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		
//		LinkedList<Integer> list = new LinkedList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(0);
//		System.out.println(list.toString());
//		list.remove(2);
//		System.out.println(list.toString());
//		list.insert(5, 3);
//		System.out.println(list.toString());
//		System.out.println("*************************");
//		DBLList<Integer> list2 = new DBLList<Integer>();
//		list2.add(1);
//		list2.add(2);
//		list2.add(3);
//		list2.add(4);
//		list2.add(0);
//		System.out.println(list2.toString());
//		list2.remove(2);
//		System.out.println(list2.toString());
//		list2.insert(5, 3);
//		System.out.println(list2.toString());
//		System.out.println("************************");
//		System.out.println(list.get(3));
//		System.out.println(list2.get(3));
		
		LinkedList<String> linkedList = new LinkedList<String>();
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
			linkedList.add((String) st.nextElement());
		}
		
		System.out.println("是否为空:"+linkedList.isEmpty());
		System.out.println("大小:"+linkedList.size());
		System.out.println("现在查找元素“呵呵he");
		System.out.println("它的位置是"+linkedList.find("呵呵he"));
		System.out.println();
		for (int i = 0; i < linkedList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+linkedList.get(i));
		}
		System.out.println();
		System.out.println("现在删除第2个元素、“呵呵he”");
		linkedList.remove(2);
		linkedList.remove("呵呵he");
		
		for (int i = 0; i < linkedList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+linkedList.get(i));
		}
		System.out.println();
		System.out.println("现在在第二个位置添加元素“嘻嘻");
		linkedList.insert("嘻嘻",2);
		
		for (int i = 0; i < linkedList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+linkedList.get(i));
		}
		
		System.out.println();
		System.out.println("现在修改元素2为“哈哈");
		linkedList.set(1, "哈哈");
		for (int i = 0; i < linkedList.size(); i++) 
		{
			System.out.println("元素"+i+"为:"+linkedList.get(i));
		}
	}
}

