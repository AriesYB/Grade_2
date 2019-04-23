package app.model;

import java.util.Date;

public class Car {
	private String number;// 车号
	private Date arr_time;// 到达时间
	private double time;// 停车时间
	private double spend;// 花费
	private int index;// 车位号

	public Car(String number) {
		super();
		this.number = number;
	}

	public Car(String number, Date time) {
		super();
		this.number = number;
		this.arr_time = time;
	}

	public Date getArr_time() {
		return arr_time;
	}

	public void setArr_time(Date arr_time) {
		this.arr_time = arr_time;
	}

	public String getNumber() {
		return number;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getSpend() {
		return spend;
	}

	public void setSpend(double spend) {
		this.spend = spend;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
