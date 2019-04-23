package com.huawei;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {
	private Map<Integer, Vertex> vertexes = new HashMap<>();
	private Map<Integer, Edge> edges = new HashMap<>();
	private static final int INFINITY = 999999999;

	public Graph(Map<Integer, Road> roads, Map<Integer, Cross> crosses, Car car) {
		/*添加Vertex*/
		for (Cross cross : crosses.values()) {
			vertexes.put(cross.getCrossID(), new Vertex(cross.getCrossID()));
		}
		/*添加Edge*/
		for (Road road : roads.values()) {// 添加Edge
			int speed;// 确定最高速度
			double weight;// 确定路的权值 (时间的倒数)
			if (road.getSpeedMax() > car.getSpeedMax()) {
				speed = car.getSpeedMax();
			} else {
				speed = road.getSpeedMax();
			}
			weight = speed / road.getLength();

			// FIXME 权值同样受到车量影响
			// double rate = road.getCarNumber()/(road.getNumber()*road.getLength()); //使用率

			edges.put(road.getId(), new Edge(road.getId(), weight, road.isDuplex(),
					vertexes.get(road.getFrom().getCrossID()), vertexes.get(road.getTo().getCrossID())));
		}
			/*添加Vertex的adj*/
		for (Cross cross : crosses.values()) {
			List<Edge> list = new ArrayList<>();
			int ids[] = cross.getRoadID();
			Vertex v = vertexes.get(cross.getCrossID());
			for (int i : ids) {
				Road road = roads.get(i);
				if(road!=null) {
					Edge e = edges.get(road.getId());
					if(!e.isDuplex && e.getDest(v)==null) {//当单向的不通时
						continue;
					}
					list.add(e);
				}
			}
			v.setAdj(list);
		}
	}

	class Edge {// 路抽象而来的边
		int id;
		double weight;// 权值 由车速 限速 路长 拥塞程度共同决定
		boolean isDuplex;
		Vertex from;
		Vertex to;

		public Edge(int id, double weight, boolean isDuplex, Vertex from, Vertex to) {
			super();
			this.id = id;
			this.weight = weight;
			this.isDuplex = isDuplex;
			this.from = from;
			this.to = to;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		public Vertex getDest(Vertex v) {
			if (isDuplex) {// 双向需要判断起点
				if (v.equals(from)) {
					return to;
				} else if (v.equals(to)) {
					return from;
				}
			} else {
				if (v.equals(from)) {
					return to;
				}
			}
			return null;
		}
	}

	class Vertex {// 路口抽象而来的点
		int id;
		private List<Edge> adj;// 邻接的边
		private boolean visited;// 是否被访问
		private double time;// 总花费（权值）
		private Vertex prev;// 前驱

		public Vertex(int id) {
			super();
			this.id = id;
		}

		public void setAdj(List<Edge> adj) {
			this.adj = adj;
		}

	}

	private void dijkstra(Vertex s) {
		for (Vertex v : vertexes.values()) {// 初始化
			v.time = INFINITY;
			v.visited = false;
		}
		s.time = 0;
		Queue<Vertex> q = new PriorityQueue<Vertex>(new Comparator<Vertex>() {// 根据总时间排序的优先队列
			@Override
			public int compare(Vertex o1, Vertex o2) {
				if (o1.time < o2.time) {
					return -1;
				} else if (o1.time > o2.time) {
					return 1;
				}
				return 0;
			}
		});
		q.add(s);
		while (!q.isEmpty()) {
			Vertex v = q.poll();
			v.visited = true;
			for (Edge e : v.adj) {
				Vertex w = e.getDest(v);
				if (!w.visited) {
					double v_to_w = e.weight;
					if (w.time > v.time + v_to_w) {
						w.time = v.time + v_to_w;
						w.prev = v;
						q.add(w);
					}
				}
			}
		}
	}

	public ArrayList<Integer> shortestPath(int i, int j) {
		ArrayList<Integer> path = new ArrayList<>();
		LinkedList<Integer> list = new LinkedList<>();
		Vertex v1 = vertexes.get(i);
		Vertex v2 = vertexes.get(j);
		dijkstra(v1);
		do {
			list.add(v2.id);
			v2 = v2.prev;
		} while (v2 != v1);
		list.add(v2.id);
		while (!list.isEmpty()) {
			path.add(list.poll());
		}
		return path;
	}
}
