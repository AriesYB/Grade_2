package 老鼠喝水;

public class DrinkWater {

	public int drinking(int day,int water) {
		for (int i = 0; i < water; i++) {
			if (Math.pow(day+1,i)>=water) {
				return i;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrinkWater dw = new DrinkWater();
		System.out.println(dw.drinking(1, 1000));
	}

}
