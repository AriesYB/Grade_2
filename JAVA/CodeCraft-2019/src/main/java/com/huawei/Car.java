package com.huawei;

import java.util.ArrayList;
import java.util.LinkedList;

public class Car {
	private int id;// 车辆id
	private Cross start;// 始发地
	private Cross destination;// 目的地
	private int speedMax;// 最高速度
	private int time;// 计划时间
	private int startTime;// 出发时间
//	public static final int length = 1;// 车身长度
	private int speed;// 当前速度
	private int x;// 在道路上的坐标(从1开始)
	private boolean isEnd;// 是否为终止状态
	private boolean isHandle;// 是否处理
	private Road road;// 车辆所在的道路
	private int channel;// 车道
	private Road next;// 下一条路
	private ArrayList<Integer> passedRoad;
	private LinkedList<Road> roadPath;
	private LinkedList<Cross> crossPath;

	public Car(int id, Cross start, Cross destination, int s, int time) {
		this.id = id;
		this.start = start;
		this.destination = destination;
		this.speedMax = s;
		this.time = time;
		startTime = -1;
		speed = 0;
		x = 0;
		isEnd = true;
		isHandle = false;
		channel = 0;
		passedRoad = new ArrayList<>();
	}

	public void runEndCar() {
		if (Console.isChecked(this) && isEnd) {
			if (startTime == -1) {
				setStartTime();// 设置出发时间
			}
			x += speed;
			Console.addEndCar(this);
		}
	}

	public void runWaitCar() {
		System.out.println("---------------"+id);
		if (!isEnd) {
			if (isOver()) {// 到达终点
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stop();
			} else {
				x = road.getLength();
				if (next.freeChannel(crossPath.getFirst()) == 0) {// 车道均没有空位 则不过路口
					x += 0;
				} else {
					int newSpeed;
					if (next.getSpeedMax() > speedMax) {// 确定速度
						newSpeed = speedMax;
					} else {
						newSpeed = road.getSpeedMax();
					}
					int hasGo = road.getLength() - x;
					int needGo = newSpeed - hasGo;
					if (needGo > 0) {// 可以过路口
						enterNext(next.freeChannel(crossPath.poll()), newSpeed, needGo);
					}
					x += 0;
				}
			}
		}
		isEnd = true;
		Console.addEndCar(this);
	}

	public void enterNext(int channel, int newSpeed, int needGo) {// 进入新道路
		passedRoad.add(road.getId());
		road.removeCar(this, this.channel);// 移除走了的车
		this.channel = channel;
		road = next;// FIXME 如果是next空呢
		next = roadPath.poll();
		road.addCar(this, channel);// 添加新的车
		speed = newSpeed;
		if (isBlock()) {// 阻挡 贴脸终止（FIXME 被等待阻挡也终止 防止死锁）
			x = road.getFrontCar(this).getX() - 1;
		} else {
			x = needGo;
		}
	}

	public boolean start() {
		road = roadPath.poll();// 放在路上
		Cross cross =crossPath.poll();
		channel = road.freeChannel(cross);// 将进入有空位的车道
		if(channel == 0) {//出发点被堵住了
			return false;
		}
		road.addCar(this, channel);// 路添加车辆
		next = roadPath.poll();
		return true;
	}

	private boolean isBlock() {
		Car front = road.getFrontCar(this);
		if (front != null) {
			if (front.isEnd()) {
				if (front.getX() - this.getX() - 1 < this.getSpeed()) {
					return true;
				}
			} else {
				if ((front.getX() - this.getX() - 1 < this.getSpeed())) {// 被等待车辆阻挡
					setEnd(false);
					return true;
				}
			}
		}
		return false;
	}

	public void checkCar() {// 过路口的才设置为等待 被过路口的阻挡的未处理
		if (isBlock() && road.getFrontCar(this).isEnd()) {// 阻挡的车为终止才设置速度
			speed = road.getFrontCar(this).getX() - x - 1;// 直接贴脸
		} else if (!isBlock()) {
			if (speedMax > road.getSpeedMax()) {
				speed = road.getSpeedMax();
			} else {
				speed = speedMax;
			}
			if (isPass()) {// 判断是否过路口
				road.getTo(this).addPassingCar(this);// 加入路口
				setEnd(false);// 设为等待状态
			}
		}
		Console.addCheckedCar(this);
	}

	private boolean isPass() {// 通过路口
		if (road.getFrontCar(this) == null) {// 前面没车
			if (road.getLength() - x + 1 <= speed) {
				return true;
			}
		}
		return false;
	}

	private boolean isOver() {// 到达终点
		if (road.getTo(this).equals(destination)) {
			return true;
		}
		return false;
	}

	private void stop() {
		System.out.println("车辆"+id+"停止"+"----------------------");
		passedRoad.add(road.getId());
		road.removeCar(this, channel);
		Console.finished(this);
	}

	public int getId() {
		return id;
	}

	public int getTime() {
		return time;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Road getRoad() {
		return road;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getSpeed() {
		return speed;
	}
	public int getSpeedMax() {
		return speedMax;
	}

	public Road getNext() {
		return next;
	}

	public void setNext(Road next) {
		this.next = next;
	}

	public void setPath(LinkedList<Road> roadPath, LinkedList<Cross> crossPath) {
		this.roadPath = roadPath;
		this.crossPath = crossPath;
	}

	public Cross getStart() {
		return start;
	}

	public Cross getDestination() {
		return destination;
	}

	public void setStartTime() {
		startTime = Console.getTime();
	}

	@Override
	public String toString() {
		String string = "(" + id + "," + startTime + ",";
		for (Integer id : passedRoad) {
			string += id + ",";
		}
		string.substring(0, string.length() - 1);
		string += ")";
		return string;
	}

	public boolean isHandle() {
		return isHandle;
	}

}
