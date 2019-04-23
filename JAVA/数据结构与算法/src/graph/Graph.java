package graph;

import seqList.SeqList;

/**
 * 图类
 */
public class Graph<T> {

	private SeqList<Vertex<T>> vexList;// 存顶点的顺序表
	private int verNum = 0; // 顶点数
	private int edgeNum = 0;// 边数

	public boolean isEmpty() {
		return vexList.isEmpty();
	}

	public int getVerNum() {
		return verNum;
	}

	public int getEdgeNum() {
		return edgeNum;
	}

	public T getValue(int i) {
		if (i >= 0 && i < vexList.size()) {
			return vexList.get(i).data;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public int getVertexPos(T v) {
		return vexList.find(new Vertex<T>(v));
	}

	public int getFirstNeighbor(int v) {
		// 查找顶点 v 第一个邻接顶点在邻接表中的位置
		if (v >= 0 && v < vexList.size()) {
			Edge e = vexList.get(v).edgeLink;
			if (e != null) {
				return e.adjvex;
			}
		}
		return -1;
	}

	public int getNextNeighbor(int v1, int v2) {
		// 查找顶点 v1 在邻接顶点 v2 后下一个邻接顶点
		if (v1 >= 0 && v1 < vexList.size()) {
			Edge e = vexList.get(v1).edgeLink;
			while (e != null) {
				if (e.adjvex == v2 && e.link != null)
					return e.link.adjvex;
				// 返回下一个邻接顶点在邻接表中的位置
				else
					e = e.link;
			}
		}
		return -1; // 没有查到下一个邻接顶点返回-1
	}
}
