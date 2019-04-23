package printer;

public class Fifo extends Simulator{
	private Queue<Event> non_arrival = new Queue<Event>();//该队列是读入的数据称为未到达队列,而workload是时间到达后进入的排队队列
	private int time=0;//当前时间
	private int count=0;//记录任务次数
	private int latency=0;//记录延迟
	private int printer=0;//0表示打印机为空
	private double page=-1;//当前未打印的页数
	
	public Fifo(int i,String url) {	
		non_arrival=loadWorkLoad(url);//加载数据
		secondes_per_pages=i;//设置打印速度
	}
	
	private void arrive()//到达语句
	{
		System.out.println("\tArriving: "+non_arrival.getFront().getJ().getNumber_of_pages()+" pages from "+non_arrival.getFront().getJ().getUser()+" at "+time+" seconds");
	}
	
	private void service()//打印语句
	{
		System.out.println("\tServicing: "+workload.getFront().getJ().getNumber_of_pages()+" pages from "+workload.getFront().getJ().getUser()+" at "+time+" seconds");
		latency+=time-workload.getFront().getArrival_time();//记录延迟
	}
	
	public void simulate()
	{
		System.out.println("******************************************************");
		System.out.println("FIFO Simulation\n");
		while(!(workload.isEmpty() && non_arrival.isEmpty() && printer==0))//打印队列为空且未到达队列为空且打印机为空时不再执行 
		{
			if(!non_arrival.isEmpty())//如果未到达队列不为空
			{
				while(time==non_arrival.getFront().getArrival_time())
				{
					workload.enQueue(non_arrival.getFront());
					arrive();
					non_arrival.deQueue();
					if(non_arrival.isEmpty())//出队后，若未载入队列为空将不再判断到达时间
					{
						break;
					}
				}
			}
			if(!workload.isEmpty() && printer==0)//打印队列不为空且打印机为空才进行打印
			{
				service();
				printer=1;
				page=workload.getFront().getJ().getNumber_of_pages();
			}
			if(printer==1)//正在打印
			{			
				if((page>0))//一秒钟减少如下页数
				{
					page-=(1/(double)secondes_per_pages);
				}
				if(page==0)//打印完成
				{			
//					System.out.println("-------现在完成:"+(time+1)+"s-----------");
					count++;
					workload.deQueue();
					printer=0;
					page=-1;
				}
			}
			time++;//时间增加
		}
		System.out.println("\nTotal jobs:"+count);
		System.out.println("Aggregate latency:"+latency+"s");
		System.out.println("Mean latency:"+String.format("%.3f",(double)latency/count)+"s");
		System.out.println("******************************************************");
	}
}