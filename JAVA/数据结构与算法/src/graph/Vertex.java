package graph;

/**
 * 顶点类
 */
public class Vertex<T> {
	public T data;	//存的数据
	public Edge edgeLink;	//定点的边链

	public Vertex(T d) {	
		data = d;
		edgeLink = null;
	}

	public Vertex(T d, Edge node) {//构造方法
		data = d;
		edgeLink = node;
	}
}
