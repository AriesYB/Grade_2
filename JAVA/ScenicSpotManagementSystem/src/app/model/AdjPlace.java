package app.model;

/**
 * 邻接景区类（边）
 */
public class AdjPlace {
	private String destination;// 目的景点
	private int distance;// 距离
	private AdjPlace next;// 下一个邻接景点
	private Place from;//来源于哪个节点
	
	public AdjPlace() {
		this.next=null;
	}

	public AdjPlace(String destination, int distance) {
		super();
		this.destination = destination;
		this.distance = distance;
		this.next = null;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public AdjPlace next() {
		return next;
	}

	public void setNext(AdjPlace next) {
		this.next = next;
	}

	public Place getFrom() {
		return from;
	}

	public void setFrom(Place from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "AdjPlace [destination=" + destination + ", distance=" + distance + ", next=" + next + ", from=" + from
				+ "]";
	}
	
}
