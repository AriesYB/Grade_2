package app.model;

import java.util.ArrayList;

@SuppressWarnings("hiding")
public class Tree<T> {
	class Node {
		T data;
		Node child;// 孩子节点
		Node sibling;// 兄弟节点

		public Node(T data) {
			super();
			this.data = data;
		}

		public Node(T data, T t) {
			super();
			this.data = data;
			this.sibling = new Node(t);
		}

	}

	private Node root;

	public Tree() {
		super();
	}

	public Tree(T data) {
		super();
		this.root = new Node(data);
	}

	public void preOrder(Node n, ArrayList<T> a) {
		if (n != null) {
			a.add(n.data);
			preOrder(n.child, a);
			preOrder(n.sibling, a);
		}
	}

	public Node find(Node current, T t) {// 从current开始寻找t节点
		if (current == null) {// 没有该节点
			return null;
		}
		if (current.data.equals(t)) {
			return current;
		}
		Node p;
		if ((p = find(current.child, t)) != null) {
			return p;
		}
		return find(current.sibling, t);
	}

	public Node findKey(T t) {
		return find(root, t);
	}

	//给定父节点，添加子节点
	public void add(T parent, T child) {
		Node n = findKey(parent);// 找到parent
		if (n.child == null) {// parent的孩子为空，就直接添加到孩子
			n.child = new Node(child);
		} else {// 添加到孩子的兄弟末尾
			Node p = n.child;
			while (p.sibling != null) {
				p = p.sibling;
			}
			p.sibling = new Node(child);
		}
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

}
