package routePlaning;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		while (true) {
			RailSystem rail = new RailSystem();
			System.out.println("---Enter a start and destination city: <'quit' to exit>---");
			String str = scan.nextLine();
			if (str.equals("quit")) {
				break;
			}
			String a[] = str.split(" ");
			if(!rail.find(a[0])) {
				System.out.println("---"+a[0]+" is not found!---");
				continue;
			}else if(!rail.find(a[1])) {
				System.out.println("---"+a[1]+" is not found!---");
				continue;
			}
			int []b =rail.calc_route(a[0], a[1]);
			System.out.println("---Cost:"+b[0] + " euros---  ---Spans:"+b[1] + " kilometers---");
			rail.recover_route(a[0],a[1]);
		}
		scan.close();
	}

}
