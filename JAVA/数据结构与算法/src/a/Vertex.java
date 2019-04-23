package a;

import java.util.*;
public class Vertex{
	private  City city;
	private  Service dest;  

	public Vertex(){
		this.city = null;
		this.dest=null;
	}
	public Vertex(City city){
		this.city = city;
		this.dest = null;
	}

	public void setCity(City city){
		this.city = city ;
	}
	public City getCity(){
		return this.city;
	}
	public void setLink(Service dest){
		this.dest = dest;
	}
	public Service getLink(){
		return this.dest;
	}
	public void addToEnd(Service link){
		Service current;
		if(dest==null){
			setLink(link);
		}else{
			for(current=dest;current.getLink()!=null;current=current.getLink()){				
			}
			current.setLink(link);
		}
		
	}
}