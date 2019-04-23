package app.util;

/**
 * 数据操作类
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import app.model.AdjPlace;
import app.model.Car;
import app.model.Graph;
import app.model.Parking;
import app.model.Place;
import javafx.collections.ObservableList;

public class DataUtil {
	private static DataUtil d;// 该类的静态对象
	private Graph g;// 图
	private Parking pk;// 停车场
	private HashMap<String, Place> map;// 指向图里的map
	private ArrayList<String> arr;// 指向图里的arr
	
	DataUtil() {
		g = new Graph();// 新建一个图
		pk = new Parking();
		map = g.getMap();
		arr = g.getArr();
		readPlaceDetail();// 读景点信息
		readPlaceInfo();// 读路径信息
	}

	public static DataUtil getData() {// 获取Data对象
		if (DataUtil.d == null) {
			d = new DataUtil();
		}
		return d;
	}

	// 读文件
	private ArrayList<String> readFile(String url) {
		ArrayList<String> a = new ArrayList<String>();
		FileReader fr = null;
		BufferedReader bufr = null;
		try {
			fr = new FileReader(url);
			bufr = new BufferedReader(fr);
			String str = "";
			while ((str = bufr.readLine()) != null) {
//FIXME 未知bug,第一行读出来开头多一个神秘字符打印不出来(应该是txt的问题，修改成了UTF-8，之前是Unicode但是读出来时乱码)
				if (str.contains("#") || str.startsWith("#") || str.equals("")) // 如果是#开头那么它是注释行
				{
					continue;
				}
				a.add(str);
			}
			bufr.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}

	private void readPlaceInfo() {//读边信息

		ArrayList<String> list = readFile(ResourceBundle.getBundle("config").getString("url_info"));
		for (String str : list) {
			String[] a = str.split("——");
			if (map.containsKey(a[0]) && map.containsKey(a[1])) {
				AdjPlace ap1 = map.get(a[0]).getAdjList();
				while (ap1.next() != null) {
					if(ap1.next().getDestination().equals(a[1])) {//说明该节点已添加
						break;
					}
					ap1 = ap1.next();
				}
				ap1.setNext(new AdjPlace(a[1], Integer.parseInt(a[2])));

				AdjPlace ap2 = map.get(a[1]).getAdjList();
				while (ap2.next() != null) {
					if(ap2.next().getDestination().equals(a[0])) {
						break;
					}
					ap2 = ap2.next();
				}
				ap2.setNext(new AdjPlace(a[0], Integer.parseInt(a[2])));

			}
		}
	}

	private void readPlaceDetail() {//读景点信息
		ArrayList<String> list = readFile(ResourceBundle.getBundle("config").getString("url_detail"));
		for (String str : list) {
			String[] a = str.split("_");
			if (!map.containsKey(a[0])) {// 如果不存在该景点 添加景点
				map.put(a[0], new Place(a[0], a[1], Integer.parseInt(a[2]), Boolean.parseBoolean(a[3]),
						Boolean.parseBoolean(a[4])));
				arr.add(a[0]);
			}
		}
	}

	private void write(String url, ArrayList<?> arr, boolean flag) {// 文件路径 传入list 是否续写
		FileWriter fw = null;
		BufferedWriter bufw = null;
		try {
			fw = new FileWriter(url, flag);
			bufw = new BufferedWriter(fw);
			for (int i = 0; i < arr.size(); i++) {
				bufw.write(arr.get(i).toString());
				bufw.flush();
				bufw.newLine();
			}
			bufw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeString(String url, String str, boolean flag) {// 文件路径 传入字符串 是否续写
		FileWriter fw = null;
		BufferedWriter bufw = null;
		try {
			fw = new FileWriter(url, flag);
			bufw = new BufferedWriter(fw);
			bufw.write(str);
			bufw.flush();
			bufw.newLine();
			bufw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeDetailFile(ObservableList<Place> list) {// 写景点信息以及更新map
		ArrayList<Place> a = new ArrayList<>();
		a.addAll(list);
		for (Place p : list) {//添加map没有有而list有的映射
			if(map.containsKey(p.getName())) {
				continue;
			}
			map.put(p.getName(), p);
		}
		for (Place p : map.values()) {//删除map有而list没有的映射
			if(list.contains(p)) {
				continue;
			}
			map.remove(p.getName());
		}
		writeString(ResourceBundle.getBundle("config").getString("url_detail"),
				ResourceBundle.getBundle("config").getString("head_detail"), false);
		write(ResourceBundle.getBundle("config").getString("url_detail"), a, true);
	}

	public void writeInfoFile(ObservableList<Place> list) {// 写路径信息
		ArrayList<String> s = new ArrayList<>();
		s.add(ResourceBundle.getBundle("config").getString("head_info"));// 头文件 注释
		for (int i = 0; i < list.size(); i++) {
			Place p = list.get(i);
			AdjPlace ap = p.getAdjList().next();
			while (ap != null) {
				s.add(p.getName() + "——" + ap.getDestination() + "——" + ap.getDistance());
				ap = ap.next();
			}
		}
		write(ResourceBundle.getBundle("config").getString("url_info"), s, false);
	}

	public ArrayList<Place> shortestPath(String p1, String p2) {
		return g.calculateShortestPath(p1, p2);
	}

	public ArrayList<Place> traceablePath(String name) {
		return g.Traceablepath(name);
	}

	public boolean find(String name) {
		return g.find(name);
	}

	public Place getPlace(String name) {
		return g.getPlace(name);
	}

	// 进入停车场
	public boolean entryParking(String number) {
		return pk.entry(number);// 是否已停车
	}

	public Car getCar(String number) {
		return pk.get(number);
	}

	public Car outParking(String number) {
		return pk.exit(number);
	}

	public DateFormat timeFormat() {
		return pk.getDf();
	}

	public boolean checkAccount(String i, String j) {
		if (ResourceBundle.getBundle("config").getString("account").equals(i)
				&& ResourceBundle.getBundle("config").getString("password").equals(j)) {
			return true;
		}
		return false;
	}

	public String getAnnouncement() {
		ArrayList<String> a = readFile(ResourceBundle.getBundle("config").getString("url_announcement"));
		return (String) a.get(a.size()-1);
	}
	
	public void setAnnouncement(String str) {
		writeString(ResourceBundle.getBundle("config").getString("url_announcement"), str, false);
	}
	
	public ArrayList<String> getArr() {
		return arr;
	}

	public HashMap<String, Place> getMap() {
		return map;
	}

}
