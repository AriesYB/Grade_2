package printer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {
	protected int secondes_per_pages;
	protected Queue<Event> workload = new Queue<>();

	public Queue<Event> loadWorkLoad(String url)//载入打印数据并依次存入队列(先进先出)
	{
		Queue<Event> q = new Queue<Event>();
		FileReader fr= null;
		BufferedReader bufr = null;
		try {
			fr = new FileReader(url);
			bufr = new BufferedReader(fr);
			String str = "";
			while ((str=bufr.readLine())!=null)
			{
				String[] a=str.split(" ");
				q.enQueue((new Event(new Job(a[2], Integer.parseInt(a[1])), Integer.parseInt(a[0]))));
			}
			bufr.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return q;
	}
}
