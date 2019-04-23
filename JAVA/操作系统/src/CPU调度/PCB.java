package CPU调度;

public class PCB {
	private String name;// 进程名
	private int needTime;// 要求运行时间
	private int priority;// 优先级
	private String state;// 状态
	private int runTime;//实际使用时间

	public PCB(String name, int needTime, int priority) {
		super();
		this.name = name;
		this.needTime = needTime;
		this.priority = priority;
		state = "Ready";
		runTime=0;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public int getNeedTime() {
		return needTime;
	}

	public int getPriority() {
		return priority;
	}

	public void updatePriority() {
		this.priority--;
	}

	public void updateNeedTime() {
		this.needTime--;
	}

	
	public int getRunTime() {
		return runTime;
	}

	public void updateRunTime() {
		this.runTime++;
	}

//	@Override
//	public int compareTo(PCB arg0) {
//		PCB current = arg0;
//		if (current.priority > this.priority) {
//			return 1;
//		} else if (current.priority == this.priority) {
//			if(current.name.hashCode()>this.name.hashCode()) {
//				return 1;
//			}
//		}
//		return -1;
//	}

}
