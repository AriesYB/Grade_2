package seqList;

public class SeqList<Type> {

	public static final int DEFAULT_CAPACITY = 10;
	private Type[] elements;
	private int size;
	
	public SeqList()
	{
		elements = (Type[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	public SeqList(int sz)
	{
		elements = (Type[]) new Object[sz];
		size = sz;
	}
	
	public void clear() //清空
	{
		size=0;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public Type get(int index)
	{
		if(index<0||index>=size) 
		{
			throw new ArrayIndexOutOfBoundsException("不在范围内");
		}
		return elements[index];
	}
	
	public void set(int index,Type newVal)
	{
		if(index<0||index>=size) 
		{
			throw new ArrayIndexOutOfBoundsException("不在范围内");
		}else
		{			
			elements[index] = newVal;
		}
	}
	
	public void add(Type element)
	{
		if(elements.length <= size)
		{
			ensureCapacity(size*2+1);
		}
			elements[size]=element;
			size++;
	}
	
	public void add(Type element,int index)
	{
		if(index<0||index>size) 
		{
			throw new ArrayIndexOutOfBoundsException("未知位置");
		}else
		{
			if(elements.length<=size)	//需要扩容
			{
				ensureCapacity(size*2+1);
			}
			for (int i = size; i > index; i--) //将插入元素位置之后的全部后移一个单位
			{
				elements[i]=elements[i-1];
			}
			elements[index]=element;
			size++;
		}	
		
	}
	
	public void remove(int index)	//删除某个位置的元素
	{
		if(index<0||index>size) 
		{
			throw new ArrayIndexOutOfBoundsException("未知位置");
		}
		for (int i = 0; i < size; i++) 
		{
			if (elements[i].equals(elements[index])) 
			{
				for (int j = i; j < size; j++)
				{
					elements[j]=elements[j+1];					
				}
				break;
			}
		}
		size--;
	}
	
	public void remove (Type element)	//删除所有某个元素
	{
		int j=0;
		for (int i = 0; i < size; i++) 
		{
			if(elements[i].equals(element))
			{
				remove(i);
				i--;
				j=-1;
			}
		}

	}
	
	public int find(Type element)
	{
		if(element==null)		//null是没有equal()的
		{
			for (int i = 0; i < size; i++) 
			{
				if(elements[i].equals(element))
				{
					return i;
				}
			}
			return -1;
		}else
		{
			for (int i = 0; i < size; i++)
			{
				if(elements[i].equals(element))
				{
					return i;
				}
			}
			return -1;
		}
	}
	
	public void ensureCapacity(int newSize)	//扩容
	 {
		 if(newSize < size)
		 {
			 System.out.println("新的大小不得小于原大小");
			 return;
		 }
		 Type[] old = elements;
		 elements = (Type[])new Object[newSize];
		 for(int i=0; i<size; i++)
		 {
			 elements[i]=old[i];
		 }
	 }
	 public void trimToSize()	//除去空余的空间
	 {
		 ensureCapacity(size);
	 }

}