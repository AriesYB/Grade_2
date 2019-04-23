package linkedList;

public class LinkedList<T> {
	
	class Node
	{
		public T data;
		public Node link;
		
		Node(T data)	//节点构造方法
		{
			this.data=data;
			link=null;
		}	
	}
	
	private Node head;	//头节点
	
	public LinkedList()	//链表初始化
	{
		head=new Node(null);
	}
	
	public void add(T data)	//在末尾增加节点
	{
		Node current=head;	//从头节点开始
		while (current.link!=null)
		{
			current = current.link; //当前节点
		}
		current.link = new Node(data);
	}
	
	public void insert(T data,int i)	//在指定位置插入
	{
		Node current=head;
		Node node = new Node(data);
		int index=0;
		while (current.link!=null)
		{
			if (index==i)
			{
				break;
			}
			current = current.link;
			index++;
		}
		node.link=current.link;
		current.link = node;
	}

	public void remove(int i)	//删除指定位置节点
	{
		Node current=head;
		int index=0;
		while (current.link!=null)
		{
			if (index==i)
			{
				break;
			}
			current = current.link;
			index++;
		}
		current.link=current.link.link;
	}
	
	public void remove(T data)	//删除所有某个元素
	{
		Node current=head;
		while (current.link!=null)
		{
			if (current.link.data.equals(data))
			{
				current.link=current.link.link;
			}
			current = current.link;
		}
	}
	
	public T get(int i)
	{
		Node current=head;
		int index=0;
		while (current.link!=null)
		{
			if (index==i)
			{
				break;
			}
			current = current.link;
			index++;
		}
		return current.link.data;
	}
	
	public void set(int i,T data)
	{
		Node current=head;
		int index=0;
		while (current.link!=null)
		{
			if (index==i)
			{
				break;
			}
			current = current.link;
			index++;
		}
		current.link.data=data;
	}
	
	public int size()
	{
		Node current=head;
		int index=0;
		while(current.link!=null)
		{
			index++;
			current=current.link;
		}
		return index;
	}
	
	public void clear()
	{
		head.link=null;
	}
	
	public int find(T data)
	{
		Node current = head;
		int index=0;
		while(current.link!=null)
		{
			if(current.link.data.equals(data))
			{
				return index;
			}
			index++;
			current=current.link;
		}
		return -1;
	}
	public String toString()	//重写toString()方法
	{
		String str="";
		Node current=head;
		while(current.link!=null)
		{
			current=current.link;
			str+=current.data+"—>";
		}
		str=str.substring(0, str.length()-2);
		return str;
	}
	
	public boolean isEmpty() 
	{
		return head.link==null;
	}
}
