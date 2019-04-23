package linkedList;

public class DBLList<T> {
	
	class Node
	{
		public T data;
		public Node llink;
		public Node rlink;
		
		Node(T data)	//节点构造方法
		{
			this.data=data;
			llink=rlink=null;
		}	
	}
	
	private Node head;	//头节点
	
	public DBLList()	//链表初始化
	{
		head=new Node(null);
		head.rlink=head;
		head.llink=head;
	}
	
	public void add(T data)	//增加节点
	{
		Node node=new Node(data);
		Node current=head;	//从头节点开始
		while (current.rlink!=head)
		{
			current = current.rlink; //当前节点
		}
		current.rlink = node;
		node.llink=current;
		node.rlink=head;
		head.llink=node;
	}
	
	public void insert(T data,int i)	//在指定位置插入
	{
		Node node = new Node(data);
		Node current=head;
		int index=0;
		while (current.rlink!=head)
		{
			if (index==i)
			{
				break;
			}
			current = current.rlink;
			index++;
		}
		node.rlink=current.rlink;
		node.llink=current;
		current.rlink = node;
		current.rlink.llink=node;
	}

	public void remove(int i)	//删除指定位置节点
	{
		Node current=head;
		int index=0;
		while (current.rlink!=head)
		{
			if (index==i)
			{
				break;
			}
			current = current.rlink;
			index++;
		}
		current.rlink=current.rlink.rlink;
		current.rlink.llink=current;
	}
	
	public T get(int i)
	{
		Node current=head;
		int index=0;
		while (current.rlink!=head)
		{
			if (index==i)
			{
				break;
			}
			current = current.rlink;
			index++;
		}
		return current.rlink.data;
	}
	
	public int size()
	{
		Node current=head;
		int index=0;
		while(current.rlink!=head)
		{
			index++;
			current=current.rlink;
		}
		return index;
	}
	
	public String toString()	//重写toString()方法
	{
		String str="";
		Node current=head;
		while(current.rlink!=head)
		{
			current=current.rlink;
			str+=current.data+"<—>";
		}
		str=str.substring(0, str.length()-3);
		return str;
	}
}
