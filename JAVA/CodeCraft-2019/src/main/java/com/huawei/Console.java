package com.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Console {
	private static Map<Integer, Car> cars;// 保存所有的车辆
	private static Map<Integer, Road> roads;// 保存所有的路
	private static Map<Integer, Cross> crosses;// 保存所有的路口
	private static List<Car> checkedCars = new ArrayList<Car>();// check之后的车辆
	private static List<Car> endCars = new ArrayList<Car>();// 跑了之后的车辆
	private static List<Car> newCar = new ArrayList<Car>();// 未上路的车
	private static List<Car> onLoadCar = new ArrayList<Car>();// 在路上的车
	private static List<Car> finshedCars = new ArrayList<Car>();// 到达终点车辆
	private static int time = 1;
	private static int count;
	private static int max;

	public static void launch() {
		newCar.addAll(cars.values());
		while (finshedCars.size() != cars.size()) {// 所有车辆均到达终点则跳出循环
			while (endCars.size() != onLoadCar.size() || onLoadCar.size() == 0) {// 所有路上的车都check后并且进入终止状态后进入下一个时间片
				for (Cross cross : crosses.values()) {// 遍历路口
					count = 0;
					for (int i : cross.getRoadID()) {
						if(i==-1) {
							continue;
						}
						max+=roads.get(i).getNumber()*roads.get(i).getLength();
					}
					/* check并且处理路上的车 */
					runAllOnLoadCar(cross);

					while (!cross.getPassingCars().isEmpty()) {
						/* 处理路口的车以及它身后未check的车 */
						runAllOnCrossCar(cross);
//						if(count>50) {
//							for(Car car:cross.getPassingCars()) {
//								car.setEnd(true);
//								checkedCars.remove(car);
//								endCars.add(car);
//							}
//							cross.getPassingCars().clear();
//						}
					}
				}

				for (int i = 0; i < newCar.size(); i++) {// 到达出发时间后发车
					Car car = newCar.get(i);
					if (car.getTime() <= time) {
						addPath(car);
						if (car.start()) {
							onLoadCar.add(car);
							newCar.remove(car);
							i--;
						}
					}
				}
			}

			checkedCars.clear();
			endCars.clear();
			System.out.println("----------时间---------------" + time);
			time++;
		}
	}

	/**
	 * 运行指向某路口的所有的车
	 */
	public static void runAllOnLoadCar(Cross cross) {
		int index[] = cross.getRoadID();
//		Arrays.sort(index);// 升序排序
		for (int n = 0; n < index.length; n++) { // 遍历路口连接的路
			if (index[n] == -1) {
				continue;
			}
			Road road = roads.get(index[n]);
			if (road.isDuplex()) {
				if (road.getFrom().equals(cross)) {
					for (int i = 1; i <= road.getNumber(); i++) {
						for (Car car : road.getChannels().get(i)) {
							if (!checkedCars.contains(car)) {// 跑完了的不运行
								car.checkCar();
								if (!car.isEnd()) {// 当前车道最前方是等待状态 所以后面的车不进行check
									break;
								}
								car.runEndCar();
							}
						}
					}
				} else if (road.getTo().equals(cross)) {
					for (int i = -1; i >= -road.getNumber(); i--) {
						for (Car car : road.getChannels().get(i)) {
							if (!checkedCars.contains(car)) {// 跑完了的不运行
								car.checkCar();
								if (!car.isEnd()) {// 当前车道最前方是等待状态 所以后面的车不进行check
									break;
								}
								car.runEndCar();
							}
						}
					}
				}
			} else if (!road.isDuplex() && road.getTo().equals(cross)) {// 朝向该路口的路
				for (int i = 1; i <= road.getNumber(); i++) {
					for (Car car : road.getChannels().get(i)) {
						if (!checkedCars.contains(car)) {// 跑完了的不运行
							car.checkCar();
							if (!car.isEnd()) {// 当前车道最前方是等待状态 所以后面的车不进行check
								break;
							}
							car.runEndCar();
						}
					}
				}
			}
		}
	}

	/**
	 * 运行所有通过某路口的车
	 */
	public static void runAllOnCrossCar(Cross cross) {
		ArrayList<Car> passingCars = cross.getPassingCars();
//		int index[] = cross.getRoadID();
//		Arrays.sort(index);// 升序排序
//		for (int n = 0; n < index.length; n++) { // 遍历路口连接的路
//			if (index[n] == -1) {
//				continue;
//			}
//			Road road = roads.get(index[n]);
//			// 遍历车道
//			int i = 0;
//			int j = 0;
//			if (road.isDuplex()) {
//				if (road.getTo().equals(cross)) {
//					i = 1;
//					j = road.getChannels().size() / 2;
//				} else {
//					i = -1;
//					j = -road.getChannels().size() / 2;
//				}
//			} else {
//				if (road.getTo().equals(cross)) {
//					i = 1;
//					j = road.getChannels().size();
//				}
//			}
//			int k = i;
//			for (; k * i != k * j; i += k) {// 遍历车道
//				Car car = road.getFirst(i);
//				if (!car.isEnd() && car.isHandle()) {// 获取过路口的车辆
//					passingCars.add(car);
//				}
//			}
//		}

		passingCars.sort(new Comparator<Car>() {// 进行排序

			@Override
			public int compare(Car o1, Car o2) {
				if (o1.getRoad().getId() < o2.getRoad().getId()) {
					return -1;
				} else if (o1.getRoad().getId() > o2.getRoad().getId()) {
					return 1;
				}
				if (o1.getRoad().getId() == o2.getRoad().getId()) {
					if (o1.getRoad().getLength() - o1.getX() < o2.getRoad().getLength() - o2.getX()) {// 同路不同车道
						return -1;
					} else if (o1.getRoad().getLength() - o1.getX() > o2.getRoad().getLength() - o2.getX()) {
						return 1;
					}
					return 0;
				}
				return 0;
			}
		});
		// 按顺序处理等待车辆 有冲突的不能走
//		System.out.println("处理循环路口"+cross.getCrossID());
		for (int i = 0; i < passingCars.size(); i++) {
			if (passingCars.size() == 0) {
				break;
			}
			Car car = passingCars.get(i);
			System.out.println("处理车"+car);
			if (cross.isConflict(car.getRoad(), car.getNext())) {// 是否冲突
				count++;
				continue;
			}
			car.runWaitCar();
			cross.removePassingCar(car);
			i--;
		}

	}

	public static void addPath(Car car) {// 上路之前给车添加路径
		int start = car.getStart().getCrossID();
		int destination = car.getDestination().getCrossID();
		ArrayList<Integer> crossIdPath = calcRoute(start, destination, car);
		car.setPath(roadPath(crossIdPath), crossPath(crossIdPath));
	}

	private static LinkedList<Cross> crossPath(ArrayList<Integer> crossIdPath) {
		LinkedList<Cross> path = new LinkedList<Cross>();
		for (Integer i : crossIdPath) {// 根据id加入路口
			path.add(crosses.get(i));
		}
		return path;
	}

	private static LinkedList<Road> roadPath(ArrayList<Integer> crossIdPath) {
		LinkedList<Road> path = new LinkedList<Road>();
		for (int i = 1; i < crossIdPath.size(); i++) {
			Cross cross = crosses.get(crossIdPath.get(i - 1));
			Cross cross1 = crosses.get(crossIdPath.get(i));
			for (Integer j : cross.getRoadID()) {
				if (j == -1) {
					continue;
				}
				Road road = roads.get(j);
				if (road.isDuplex() && (road.getFrom().equals(cross1) || road.getTo().equals(cross1))) {
					path.add(road);
				} else if (!road.isDuplex() && road.getFrom().equals(cross) && road.getTo().equals(cross1)) {
					path.add(road);
				}
			}
		}
		return path;
	}

	private static ArrayList<Integer> calcRoute(int start, int destination, Car car) {
		Graph g = new Graph(roads, crosses, car);
		return g.shortestPath(start, destination);
	}

//	private static ArrayList<Integer> calcRoute(int start, int end) {
//		System.out.println("计算路径");
//		Map<Integer, Dest> dests = new TreeMap<Integer, Dest>();
//		Map<Integer, Dest> circle = new TreeMap<Integer, Dest>();
//		Dest root = new Dest(0, start);
//		circle.put(start, root);
//		dests.put(start, root);
//		while (true) {
//			if (dests.get(end) != null) {
//				break;
//			}
//			for (Map.Entry<Integer, Dest> entry : circle.entrySet()) {
//				int newdistance = entry.getValue().getDistance() + dests.get(entry.getValue().getPre()).getDistance();
//				if (dests.get(entry.getKey()) == null) {
//					Dest d = new Dest(newdistance, entry.getValue().getPre());
//					dests.put(entry.getKey(), d);
//				} else {
//					if (newdistance < dests.get(entry.getKey()).getDistance()) {
//						dests.get(entry.getKey()).setDistance(newdistance);
//						dests.get(entry.getKey()).setPre(entry.getValue().getPre());
//					}
//				}
//			}
//			Map<Integer, Dest> exchange = new TreeMap<Integer, Dest>();
//			for (Map.Entry<Integer, Dest> entry : circle.entrySet()) {
//				Cross cross = crosses.get(entry.getKey());
//				for (Route route : cross.getRoutes()) {
//					int id = route.getCross().getCrossID();
//					if (id != -1) {
////						if(!circle.containsKey(id)) {
//						if (exchange.get(id) == null) {
//							exchange.put(id, new Dest(route.getRoad().getLength(), cross.getCrossID()));
//
//						} else {
//							int dis1 = route.getRoad().getLength() + dests.get(cross.getCrossID()).getDistance();
//							int dis2 = exchange.get(id).getDistance()
//									+ dests.get(exchange.get(id).getPre()).getDistance();
//							if (dis1 < dis2) {
//								exchange.get(id).setDistance(route.getRoad().getLength());
//								exchange.get(id).setPre(cross.getCrossID());
//							}
//						}
//					}
//
//				}
//			}
//			circle = exchange;
//		}
//		ArrayList<Integer> routes = new ArrayList<Integer>();
//		for (int i = end; i != start; i = dests.get(i).getPre()) {
//			routes.add(0, i);
//		}
//		routes.add(start);
//		return routes;
//	}

	public static void read(String carPath, String roadPath, String crossPath) {
		crosses = Util.readCrossInfo(crossPath);
		cars = Util.readCarInfo(carPath);
		roads = Util.readRoadInfo(roadPath);
//		addRoute();
	}

//	private static void addRoute() {
//		for (Cross cross : crosses.values()) {
//			for (Integer i : cross.getRoadID()) {
//				if (i == -1) {
//					continue;
//				}
//				Road road = roads.get(i);
//				if (road.isDuplex() || (!road.isDuplex() && road.getFrom().equals(cross))) {// 这条路来源这个路口
//					cross.addRoutes(road);
//				}
//			}
//		}
//	}

	public static void write(String answerPath) {
		Util.writeAnswerInfo(cars.values(), answerPath);
	}

	public static void addCheckedCar(Car car) {
		if (car != null) {
			checkedCars.add(car);
		}
	}

	public static void addEndCar(Car car) {
		if (car != null) {
			endCars.add(car);
		}
	}

	public static boolean isChecked(Car car) {
		if (checkedCars.contains(car)) {
			return true;
		}
		return false;
	}

	public static int getTime() {
		return time;
	}

	public static void finished(Car car) {
		if (!finshedCars.contains(car)) {
			finshedCars.add(car);
			System.out.println(car.getId() + "已经完成----------------------------------------------------------");
		}
	}

	public static Cross getCross(int id) {
		if (crosses.containsKey(id)) {
			return crosses.get(id);
		}
		return null;
	}

	public static Map<Integer, Car> getCars() {
		return cars;
	}

	public static Map<Integer, Road> getRoads() {
		return roads;
	}

	public static Map<Integer, Cross> getCrosses() {
		return crosses;
	}

}
