package com.huawei;

import java.util.ArrayList;

public class Cross {

	private int id;
	private int[] roadID;
	private ArrayList<Car> passingCars;
//	private ArrayList<Route> routes;

	public Cross(int crossID, int[] roadID) {
		super();
		this.id = crossID;
		this.roadID = roadID;
		passingCars = new ArrayList<>();
//		routes = new ArrayList<>();
	}

	public boolean isConflict(Road road, Road next) {
		if (next != null) {
			if (getDirection(road) - getDirection(next) == 2 || getDirection(road) - getDirection(next) == -2) {// 直行
				return false;
			}
			if (getDirection(road) - getDirection(next) == -1 || getDirection(road) - getDirection(next) == 3) {// 左转
				for (Car car : passingCars) {
					if (car.getRoad().equals(road)) {// 当前道路排除
						continue;
					}
					if (car.getChannel() != 1) {// 非第一优先级排除
						continue;
					}
					if (car.getNext().equals(next)) {// 直行时冲突
						if (getDirection(car.getRoad()) - getDirection(next) == 2
								|| getDirection(car.getRoad()) - getDirection(next) == -2) {
							return true;
						}
					}
				}
			}
			if (getDirection(road) - getDirection(next) == 1 || getDirection(road) - getDirection(next) == -3) {// 右转
				for (Car car : passingCars) {
					if (car.getRoad().equals(road)) {// 当前道路排除
						continue;
					}
					if (car.getChannel() != 1) {// 非第一优先级排除
						continue;
					}
					if (car.getNext().equals(next)) {// 只有右转时不冲突
						if (getDirection(car.getRoad()) - getDirection(next) == 1
								|| getDirection(car.getRoad()) - getDirection(next) == -3) {
							return false;
						}
					}
				}
				System.out.println(road.getId()+"到"+next.getId()+"---"+getDirection(road)+"右转"+getDirection(next));
				return true;
			}
		}
		return false;
	}

	private int getDirection(Road road) {// 方向分别为1 2 3 4
		for (int i = 1; i <= 4; i++) {
			if (roadID[i - 1] == road.getId()) {
				System.out.println(roadID[i-1]+"-"+i);
				return i;
			}
		}
		return -1;
	}

	public int getCrossID() {
		return id;
	}

	public int[] getRoadID() {
		return roadID;
	}

	public ArrayList<Car> getPassingCars() {
		return passingCars;
	}

	public void addPassingCar(Car car) {
		passingCars.add(car);
	}

	public void removePassingCar(Car car) {
		passingCars.remove(car);
	}

//	public ArrayList<Route> getRoutes() {
//		return routes;
//	}
//
//	public void addRoutes(Road road) {
//		if (road.isDuplex()) {
//			if (road.getFrom().equals(this)) {
//				routes.add(new Route(road, road.getTo()));
//			} else if (road.getTo().equals(this)) {
//				routes.add(new Route(road, road.getFrom()));
//			}
//		} else if (!road.isDuplex() && road.getFrom().equals(this)) {
//			routes.add(new Route(road, road.getTo()));
//		}
//	}

}
