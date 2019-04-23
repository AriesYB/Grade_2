package printer;

import java.util.NoSuchElementException;

public class Queue<T> {
	
	class QueueNode<T1>{		//队列节点类
		T1 data;
		QueueNode<T1> link;
		public QueueNode() 
		{	//构造方法
			data=null;
			link=null;
		}
		
		public QueueNode(T1 data,QueueNode<T1> link)
		{
			this.data=data;
			this.link=link;
		}
	}
	
	private QueueNode<T> front;
	private QueueNode<T> rear;
	
	public Queue() 
	{
		front=rear=null;
	}
	
	public boolean isEmpty() 
	{
		return front==null;
	}
	
	public T getFront() 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		return front.data;
	}
	
	public void enQueue(T data) {
		if(front==null)
		{
			front=rear=new QueueNode<T> (data,null);
		}else 
		{
			rear=rear.link=new QueueNode<T> (data,null);
		}
	}
	
	public T deQueue() {
		if(isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		QueueNode<T> p =front;
		T retValue = p.data;
		front = front.link;	
		if(front==null) 
		{
			rear = null;
		}
		return retValue;
	}
}
