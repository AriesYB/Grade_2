package a;

import java.util.*;
import java.io.*;
public class RailSystem{
	private int numOfVertex;
	private ArrayList<Vertex> verticesList;
	// private String start ;
	private int[] dist;
	private boolean[] set;
	private String[] path;
	// private 
	public RailSystem(){
		this.numOfVertex = 0;
		this.verticesList = new ArrayList<Vertex>();
	}
	// publi
	public void setNum(int num){
		this.numOfVertex = num;
	}
	public int getNum(){
		return numOfVertex;
	}
	public void setArray(ArrayList<Vertex> verticesList){
		this.verticesList = verticesList;
	}
	public ArrayList<Vertex> getArray(){
		return this.verticesList;
	}
	public void add(Vertex v){
		this.verticesList.add(v);
		numOfVertex++;
	}
	public Vertex getByName(String n){
		Vertex v = null;
			for(int i=0;i<numOfVertex;i++){
				if (n.equals(verticesList.get(i).getCity().getName())){
					v = verticesList.get(i);
			}
		}
		
		return v;
		
	}
	public String[] toList(){
		String[] demo = new String[numOfVertex] ;
		for(int i=0;i<numOfVertex;i++){
			demo[i] = verticesList.get(i).getCity().getName();
		}
		return demo;
	}

	public void load_services(){
		try{
			FileReader reader = new FileReader("demo2.txt");
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			String city = null;
			while((str = br.readLine()) != null){
				String[] a = str.split(" ");
				Service newService = new Service(a[1],Integer.parseInt(a[2]),Integer.parseInt(a[3]));
				if(!a[0].equals(city)){
					add(new Vertex(new City(a[0])));

					city = a[0];
				}
				verticesList.get(numOfVertex-1).addToEnd(newService);
			}
			br.close();
			reader.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}




	// public void check(){
	// 	for(int i=0;i<numOfVertex;i++){
	// 		Vertex v = verticesList.get(i);
	// 		System.out.print(v.getCity().getName()+" ");
	// 		// System.out.println(v.getLink()==null);
	// 		for(Service s=v.getLink();s!=null;s=s.getLink()){
	// 			System.out.print(s.getDest()+" ");
	// 		}
	// 		System.out.println("\n");
	// 	}
	// }




	public void calc_route(String v){
		try {
			int MAX = Integer.MAX_VALUE;
			dist = new int[numOfVertex];
			set = new boolean[numOfVertex];
			path = new String[numOfVertex];
			Arrays.fill(dist,MAX);//默认为最大
			Arrays.fill(set,false);//默认为非

			String[] demo = toList();
			int flag = Arrays.binarySearch(demo,v);//起点在demo中的位置
			Vertex vertex = verticesList.get(flag);
			

			//初始化dist数组和path数组
			for(Service i=vertex.getLink();i!=null;i=i.getLink()){
				int index = Arrays.binarySearch(demo,i.getDest());
				dist[index] = i.getFee();
				path[index] = v;//path数组的初始化
			}
			path[Arrays.binarySearch(demo,v)] = v;
			set[flag] = true;
			dist[flag] = 0;

			//计算最短路径
			for(int i=0;i<numOfVertex-1;i++){
				int min = MAX;
				int u = flag;
				//取得dist当中的最小数
				for(int j=0;j<numOfVertex;j++){
					if(!set[j] && dist[j]<min){
						u = j;
						min = dist[j];
					}
				}
				//设定 u的路径是最短路径
				set[u] = true;

				for(int w=0;w<numOfVertex;w++){



					int dis =MAX;
					vertex = verticesList.get(u);
					//获得目的地w对应的费用
					for (Service s=vertex.getLink();s!=null;s=s.getLink()) {
						if(s.getDest().equals(demo[w])){
							dis = s.getFee();
						}
					}
					if (!set[w] && dis<MAX && (dist[u]+dis)<dist[w]) {
						dist[w] = dist[u] + dis;
						path[w] = demo[u];
					}
				}
			}
			// for(int i=0;i<numOfVertex;i++){
			// 	System.out.println(path[i]+" to "+demo[i]);
			// }
			//将得到的数组赋值到verticesList数组当中实现业务
			for(int i=0;i<numOfVertex;i++){
				City city = verticesList.get(i).getCity();
				// System.out.print(path[i]+" ");
				if(path[i]!=null){
					if(!path[i].equals(demo[i])){
						city.setCity(path[i]);				
						Vertex m = getByName(path[i]);
						for(Service s=m.getLink();s!=null;s=s.getLink()){
							if(city.getName().equals(s.getDest())){
								city.setDistance(s.getDistance());
							}
						}
					}
				}
			// 求最短路径
			}

		}catch(IndexOutOfBoundsException e){
			System.out.println("There is NO SUCH SOURCE CITY or please input in the right form of 'Xxxx Xxxxx'.");
			System.exit(1);
		}
			
	}
	public String getRoute(String d){
		try{
			if (getByName(d).getCity().getCity()==null) {
			System.out.println("There are no route to choose,for the destination is now not reachable currently.you may choose other transportations.");
			System.exit(0);
			}
			ArrayList<String> route = new ArrayList<String>();
			String[] demo = toList();
			int total=dist[verticesList.indexOf(getByName(d))];
			int distance=0;

			for(City city = getByName(d).getCity();city!=null;city=getByName(city.getCity()).getCity()){
				route.add(city.getName());
				distance+=city.getDistance();	
				if(city.getCity()==null){
					break;
				}
			}
			StringBuffer a1 = new StringBuffer();
			for(int i=route.size()-1;i>=0;i--){
				a1.append(route.get(i));
				if(i!=0){
					a1.append(" to ");
				}
			}
			return "The cheapest route from "+route.get(route.size()-1)+" to "+route.get(0)
						+"\ncosts "+total+" euros and spans "+distance+" kilometers\n"
						+a1;
		}catch(NullPointerException e){
			System.out.println("There is NO SUCH DESTINATION or please input in the right form of 'Xxxx Xxxxx'.");
		}
		return null;


		
	}
	public void reSet(){
		this.dist = null;
		this.set = null;
		this.path = null;
		for (int i=0;i<numOfVertex ;i++ ) {
			verticesList.get(i).getCity().reSet();
		}
	}
	// public recover_route(){}

}


