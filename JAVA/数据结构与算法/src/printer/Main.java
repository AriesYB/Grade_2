package printer;

public class Main {

	public static void main(String[] args) {
		
		Fifo f1 = new Fifo(2,"D://data/arbitrary.run");
		Fifo f2 = new Fifo(2,"D://data/bigfirst.run");
		f1.simulate();
		f2.simulate();

	}

}
