package a;
public class City {
	private String name;
	private int fee;
	private int distance;
	private String from_city;
	public City(String a){
		this.name = a;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setFee(int f){
		this.fee = f;
	}
	public int getFee(){
		return this.fee;
	}
	public void setDistance(int dis){
		this.distance = dis;
	}
	public int getDistance(){
		return this.distance;
	}
	public void setCity(String c){
		this.from_city = c;
	}
	public String getCity(){
		return this.from_city;
	}
	public void reSet(){
		this.fee = 0;
		this.distance = 0;
		this.from_city = null;
	}
}