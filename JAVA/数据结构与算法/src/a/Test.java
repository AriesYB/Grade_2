package a;

import java.util.*;
// import java.io.*;
public class Test{
		public static void main(String[] args){
			// rl.load_services();
			RailSystem rl = new RailSystem();
			rl.load_services();
			Scanner scan = new Scanner(System.in);
			System.out.println("enter a start city and destination city: ('quit/q' to exit)");
			String value = scan.nextLine();
			// String value = "q";

			while(!value.equals("quit") && !value.equals("q")){
				String[] st_de = value.split(" ");
				rl.calc_route(st_de[0]);
				System.out.println(rl.getRoute(st_de[1]));
				// rl.reSet();
				System.out.println("enter a start city and destination city: ('quit/q' to exit)");
				value = scan.nextLine();
			}





		}

}
	

	
