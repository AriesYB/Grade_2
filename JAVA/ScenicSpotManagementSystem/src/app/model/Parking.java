package app.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;

public class Parking {

	private HashMap<String,Car> m;
	private Stack<Car> s1;// 停车场
	private Stack<Car> s2;// 让路的场地
	private Queue<Car> q;// 便道
	private int fee;// 停车费
	private DateFormat df;// 时间格式
	private int n;// 停车位

	public Parking() {
		m = new HashMap<String,Car>();
		s1 = new Stack<Car>();
		s2 = new Stack<Car>();
		q = new LinkedList<Car>();
		fee = Integer.parseInt(ResourceBundle.getBundle("config").getString("fee"));// 从config读取
		df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		n = Integer.parseInt(ResourceBundle.getBundle("config").getString("parking_space_number"));
	}

	//准备停车返回一个布尔值 是否停车
	public boolean entry(String carNumber) {
		if (s1.size() == n) {// 停车位已满,进入便道等待
			m.put(carNumber, new Car(carNumber));
			q.add(m.get(carNumber));
			return false;
		} else {
			Date now = new Date();
			m.put(carNumber, new Car(carNumber, now));
			s1.push(m.get(carNumber));
			s1.peek().setIndex(s1.indexOf(s1.peek()));//设置车位号
			return true;
		}
	}

	//车辆离开停车场
	public Car exit(String carNumber) {
		if(!s1.contains(m.get(carNumber))) {//不包含当前的车辆
			return null;
		}
		Car current = s1.pop();
		while (!current.getNumber().equals(carNumber) && !s1.isEmpty()) {//不为空才能出栈
				s2.push(current);//当前车进入让车场地
				current = s1.pop();
		}
		Date now = new Date();
		while(!s2.isEmpty()) {//s2不为空，出栈 然后入栈s1
			s1.push(s2.pop());
		}
		for (Car car : s1) {//更新车位号
			car.setIndex(s1.indexOf(car));
		}
		while(s1.size()<n && !q.isEmpty()) {//停车场还有空位 且便道上有车，则便道上的车进入停车场
			Car car = q.poll();
			car.setArr_time(new Date());//设置到达时间
			s1.push(car);//进入停车场
			car.setIndex(s1.indexOf(car));
		}
		current.setTime(calculateTime(now,current.getArr_time()));//设置停车时间
		current.setSpend(current.getTime() * fee);//设置费用
		return current;//返回离开停车场的车
	}

	//计算两个日期差的时间 返回
	private double calculateTime(Date time1, Date time2) {
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeString1 = df.format(time1);
		String timeString2 = df.format(time2);
		long diff = time1.getTime() - time2.getTime();
		double hours=0;
		try {
			time1 = df.parse(timeString1);
			time2 = df.parse(timeString2);
			diff = time1.getTime() - time2.getTime();// 毫秒ms
			hours =	(double)diff/(60*60*1000);
			hours = (double)(Math.round(hours*100)/100.0);//保留两位小数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hours;
	}
	
	
	public DateFormat getDf() {
		return df;
	}

	public Car get(String name) {
		return m.get(name);
	}
}
