package app.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 图
 */
public class Graph {
	private HashMap<String, Place> map;// 景点名称与景点对象的映射
	private ArrayList<String> arr;// 景点名称

	public Graph() {
		this.map = new HashMap<String, Place>();
		this.arr = new ArrayList<String>();
	}

	// 初始化节点访问数据
	private void resetAll() {
		for (Place p : map.values()) {
			p.setVisited(false);
			p.setTotal_distance(0);
			p.setFrom_place(null);
		}
	}

	// 遍历该节点到其余每个节点的路径
	private void calculate(Place place) {
		resetAll();
		Queue<Place> q = new PriorityQueue<Place>(new Comparator<Place>() {// 优先级队列
			@Override
			public int compare(Place p1, Place p2) {
				int i = p1.getTotal_distance() - p2.getTotal_distance();
				if (i > 0) {
					return 1;
				} else if (i == 0) {
					return 0;
				}
				return -1;
			}
		});
		q.add(place);// 将当前景点加入队列
		Place p;
		while (!q.isEmpty()) {
			p = q.poll();
			AdjPlace current = p.getAdjList().next();// 景点能到达的景点
			while (current != null) {
				if (map.get(current.getDestination()).isVisited() == false) {// 该节点未被访问
					map.get(current.getDestination()).setVisited(true);
					map.get(current.getDestination()).setFrom_place(p);
					map.get(current.getDestination()).setTotal_distance(p.getTotal_distance() + current.getDistance());
					q.add(map.get(current.getDestination()));
				} else if (map.get(current.getDestination()).getTotal_distance() > p.getTotal_distance()
						+ current.getDistance()) {// 该景点已访问且到达邻接景点距离更少
					map.get(current.getDestination()).setFrom_place(p);
					map.get(current.getDestination()).setTotal_distance(p.getTotal_distance() + current.getDistance());
				}
				current = current.next();
			}
		}
	}

	// 最短路径
	public ArrayList<Place> calculateShortestPath(String name1, String name2) {
		calculate(map.get(name1));// 计算p1到各个节点的路径
		Place p = map.get(name2);
		ArrayList<Place> a = new ArrayList<>();
		do {
			a.add(p);
			p = p.getFrom_place();
		} while (p!= map.get(name1));//相等时，最后一个还未加入
		a.add(p);
		return a;
	}

	// 最下生成树 Prim算法
	private ArrayList<Place> Prim(Place p) {
		ArrayList<Place> a = new ArrayList<Place>();// 存放生成树的节点
		resetAll();
		Queue<AdjPlace> q = new PriorityQueue<AdjPlace>(new Comparator<AdjPlace>() {// 优先级队列
			@Override
			public int compare(AdjPlace ap1, AdjPlace ap2) {
				int i = ap1.getDistance() - ap2.getDistance();
				if (i > 0) {
					return 1;
				} else if (i == 0) {
					return 0;
				}
				return -1;
			}
		});
		
		AdjPlace m = new AdjPlace(p.getName(),0);
		q.add(m);
		while(a.size()!=map.values().size()) {
			AdjPlace ap = q.poll();
			Place place = map.get(ap.getDestination());
			place.setFrom_place(ap.getFrom());
			if(!a.contains(place)) {
				a.add(place);
			}
			AdjPlace ap1 = place.getAdjList().next();
			while(ap1!=null) {
				if(a.contains(map.get(ap1.getDestination()))) {
					ap1=ap1.next();
					continue;
				}
				ap1.setFrom(place);
				q.add(ap1);
				ap1=ap1.next();
			}
		}
		return a;
	}

	//生成回路
	public ArrayList<Place> Traceablepath(String name) {
		Place p = map.get(name);
		ArrayList<Place> list = Prim(p);//最小生成树生成算法
		ArrayList<Place> x = new ArrayList<Place>();
		Tree<Place> t = new Tree<Place>(list.get(0));
		for (int j = 1; j < list.size(); j++) {//构建树
			t.add(list.get(j).getFrom_place(), list.get(j));
		}
		t.preOrder(t.getRoot(), x);
		x.add(x.get(0));
		return x;
	}

	public boolean find(String name) {
		if (map.containsKey(name)) {
			return true;
		}
		return false;
	}

	public Place getPlace(String name) {
		if (find(name)) {
			return map.get(name);
		}
		return null;
	}

	public HashMap<String, Place> getMap() {
		return map;
	}

	public ArrayList<String> getArr() {
		return arr;
	}

}
