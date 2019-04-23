package graph;

/**
 * 边类
 */
public class Edge {
	public int adjvex; // 邻接点
	public int weight; // 权值
	public Edge link; // 其他边

	public Edge(int d, Edge next) { // 无权值
		adjvex = d;
		weight = 0;
		link = next;
	}

	public Edge(int d, int w, Edge next) { // 有权值
		adjvex = d;
		weight = w;
		link = next;
	}

}
