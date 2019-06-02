package math;

import java.util.Scanner;

public class Main {

	public static void print(double[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + "  ");
		}
		System.out.println("\n" + "-----------------");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入维数__");
		int n = scan.nextInt();
		Equation e = new Equation(n);
		System.out.println("----------高斯消去法----------");
		print(e.Gauss());
		System.out.println("----------雅克比迭代法----------");
		for (int j = 1; j <= 1000; j += 20) {
			print(e.Jacobi(j));
		}
		System.out.println("----------高斯赛德尔迭代法----------");
		for (int k = 1; k <= 1000; k += 20) {
			print(e.GS(k));
		}
		System.out.println("----------逐次超松弛迭代法----------");
		for (int k = 1; k <= 1000; k += 20) {
			print(e.SOR(k, 0.99));
		}

		System.out.println("输入任意值将增加维数到20维演示迭代1000次的值...");
		scan.nextInt();
		scan.close();
		for (int i = 6; i < 20; i += 2) {
			Equation e2 = new Equation(i);
			System.out.println("----------高斯消去法----------");
			print(e2.Gauss());
			System.out.println("----------雅克比迭代法----------");
			print(e2.Jacobi(1000));
			System.out.println("----------高斯赛德尔迭代法----------");
			print(e2.GS(1000));
			System.out.println("----------逐次超松弛迭代法----------");
			print(e2.SOR(1000, 0.99));
		}
	}
}
