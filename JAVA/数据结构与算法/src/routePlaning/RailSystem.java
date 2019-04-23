package routePlaning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RailSystem {

	private Map<String, City> map = new HashMap<String, City>();// 储存城市

	public RailSystem() {
		load_services("D://data/services.txt");
	}

	// 读取数据
	private void load_services(String url) {
		FileReader fr = null;
		BufferedReader bufr = null;
		try {
			fr = new FileReader(url);
			bufr = new BufferedReader(fr);
			String str = "";
			while ((str = bufr.readLine()) != null) {
				String[] a = str.split(" ");
				Service newService = new Service(a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]));
				// 若存在该城市的键,添加Service;否则新建一个键
				if (map.containsKey(a[0])) {
					Service current = map.get(a[0]).link;
					while (current.next != null) {
						current = current.next;
					}
					current.next = newService;
				} else {
					map.put(a[0], new City(a[0], new Service(a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]))));
				}
			}
			bufr.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void resetAll() {
		for (City city : map.values()) {
			reset(city);
		}
	}

	// 重置City对象的数据成员
	private void reset(City city) {
		city.visited = false;
		city.total_distance = 0;
		city.total_fee = 0;
		city.from_city = null;
	}

	private void calculate(City c) {// 遍历该节点到每个节点的路径
		Queue<City> q = new PriorityQueue<City>(new Comparator<City>() {// 优先级队列
			@Override
			public int compare(City c1, City c2) {
				int i = c1.total_fee - c2.total_fee;
				if (i > 0) {
					return 1;
				} else if (i == 0) {
					return 0;
				}
				return -1;
			}
		});
		q.add(c);// 将当前城市加入队列
		City city;
		while (!q.isEmpty()) {
			city = q.poll();
			Service current = city.link;// 城市所到达的服务
			while (current != null) {
				if (map.get(current.destination).visited == false) {// 该节点未被访问
					map.get(current.destination).visited = true;
					map.get(current.destination).from_city = city;
					map.get(current.destination).total_distance = city.total_distance + current.distance;
					map.get(current.destination).total_fee = city.total_fee + current.fee;
					q.add(map.get(current.destination));
				} else if (map.get(current.destination).total_fee > city.total_fee + current.fee) {// 该城市已访问且到达service城市花费更少
					map.get(current.destination).from_city = city;
					map.get(current.destination).total_distance = city.total_distance + current.distance;
					map.get(current.destination).total_fee = city.total_fee + current.fee;
				}
				current = current.next;
			}
		}
	}

	// 使用正加权最短路径算法进行计算路线
	public int[] calc_route(String from, String to) {
		calculate(map.get(from));
		City city = map.get(to);
		return new int[] { city.total_fee, city.total_distance };
	}

	// 打印路线
	public void recover_route(String from, String to) {
		City b = map.get(from);
		City c = map.get(to);
		path(b,c);
	}

	private void path(City b,City c) {
		ArrayList<City> arr = new ArrayList<City>();
		do{
			arr.add(c);
			c = c.from_city;
		}while (!c.name.equals(b.name));
		arr.add(b);
		String str="";
		for (int i = arr.size() - 1; i >= 0; i--) {
			str+=arr.get(i).name+"——>";
		}
		str=str.substring(0, str.lastIndexOf("——>"));
		System.out.println(str);
	}

	public Map<String, City> getMap() {
		return map;
	}

	public boolean find(String city) {
		if (map.containsKey(city)) {
			return true;
		}
		return false;
	}
}
