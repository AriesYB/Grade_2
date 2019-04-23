package routePlaning;

public class Service {

	public String destination;// 目的城市
	public int fee;// 价格
	public int distance;// 距离
	public Service next;// 城市的下一个到达服务
	
	public Service(String destination, int fee, int distance) {
		this.destination = destination;
		this.fee = fee;
		this.distance = distance;
	}

	public Service(String destination, int fee, int distance, Service next) {
		super();
		this.destination = destination;
		this.fee = fee;
		this.distance = distance;
		this.next = next;
	}

}
