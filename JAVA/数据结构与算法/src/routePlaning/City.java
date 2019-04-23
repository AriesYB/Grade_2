package routePlaning;

public class City {

	public String name;// 城市名称
	public Service link;// 铁路服务
	public boolean visited;//是否被访问
	public int total_distance;//总距离
	public int total_fee;//总花费
	public City from_city;//从哪个城市到来
	
	public City(String name, Service service) {
		this.name = name;
		this.link = service;
	}
}
