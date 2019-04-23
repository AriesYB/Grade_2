package a;

public class Service{
	private String dest_city;
	private int fee;
	private int distance;
	private Service link;

	public Service(){
	}
	public Service(String a,int b,int c){
		this.dest_city = a;
		this.fee = b;
		this.distance = c;
		this.link = null;
	}
	public void setDest(String dest){
		this.dest_city = dest;
	}
	public String getDest(){
		return this.dest_city;
	}
	public void setFee(int fee){
		this.fee = fee;
	}
	public int getFee(){
		return this.fee;
	}
	public void setDistance(int distance){
		this.distance = distance;
	}
	public int getDistance(){
		return this.distance;
	}

	public void setLink(Service link){
		this.link = link;
	}
	public Service getLink(){
		return this.link;
	}

}