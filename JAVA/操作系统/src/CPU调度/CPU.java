package CPU调度;

import java.util.ArrayList;

public class CPU {
	public int cpuTime;
	public Queue q = new Queue();// 自动排序队列
	public ArrayList<PCB> processes = new ArrayList<PCB>();// 保存正在执行或者执行后等待的进程
	public ArrayList<PCB> list = new ArrayList<PCB>();//所有进程
	public CPU() {
		super();
		// 创建进程
		list.add(new PCB("P1", 2, 1));
		list.add(new PCB("P2", 3, 5));
		list.add(new PCB("P3", 1, 3));
		list.add(new PCB("P4", 2, 4));
		list.add(new PCB("P5", 4, 2));
		// 创建进程的优先级队列
		for(PCB p : list) {
			q.add(p);
		}
	}

	public void execute(PCB p) {
		q.poll();
		p.setState("Working");
		if (!processes.contains(p)) {// 若不存在该进程，则添加
			processes.add(p);
		}
		for (PCB P : processes) {// 遍历集合增加时间
			P.updateRunTime();
		}
		p.updatePriority();// 减少优先级
		p.updateNeedTime();// 减少需要的时间
		cpuTime++;// 增加cpu时间
		if (p.getNeedTime() == 0)// 执行完成出队
		{
			p.setState("Finsh");
			processes.remove(processes.indexOf(p));
		} else {
			q.add(p);
		}
		print();
		if(processes.contains(p)) {			
			p.setState("Ready");
		}
	}

	public void print() {
		System.out.println("CPUTime:" + cpuTime);
		for (PCB pcb : list) {// 打印情况
			if (pcb == null) {
				continue;
			}
			System.out.println(" " + pcb.getName() + "  	 " + pcb.getRunTime() + "  	  " + pcb.getNeedTime()
					+ "	    " + pcb.getPriority() + "        " + pcb.getState() + "  ");
		}
	}
}
