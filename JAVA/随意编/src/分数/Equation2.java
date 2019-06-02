package 分数;

import java.util.Scanner;

public class Equation2 {
	public final Fraction[][] H;// 系数矩阵
	public final Fraction[] b;// 方程右端向量
	public Fraction[][] h;// 临时的变换的矩阵
	public Fraction[] x;// 方程的解
	public final int n;// 维数

	/**
	 * 初始化矩阵及右端
	 */
	public Equation2() {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入维数__");
		n = scan.nextInt();
		scan.close();
		H = new Fraction[n][n];
		b = new Fraction[n];
		h = new Fraction[n][n];
		x = new Fraction[n];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				H[i - 1][j - 1] = new Fraction(1, i + j - 1);
			}
		}

		for (int i = 1; i <= n; i++) {
			b[i-1] = new Fraction(0, 1);
			for (int j = 1; j <= n; j++) {
				b[i - 1] = H[i - 1][j - 1].add(b[i - 1]);
			}
		}
		System.out.println("系数矩阵以及右端为");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(H[i][j] + "  ");
			}
			System.out.println("----"+b[i]);
		}
		System.out.println("-----------------------------------");

	}

	/**
	 * 高斯消去法
	 */
	public Fraction[] Gauss() {
		init();
		for (int i = 1; i < n; i++) {// 从第2行到第n行都需要被减
			for (int j = i; j < n; j++) {// 消去第i列
				if (h[j][i - 1].isZero()) {
					continue;
				}
				Fraction a = h[j][i - 1].multiple(new Fraction(-1, 1)).divide(h[i - 1][i - 1]);
				for (int k = i - 1; k < n; k++) {
					h[j][k] = h[j][k].add(a.multiple(h[i - 1][k]));
					b[j] = b[j].subtract(a.multiple(h[i - 1][k]));
				}
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			x[i] = b[i];
			for (int j = n - 1; j > i; j--) {
				x[i] = x[i].subtract(x[j].multiple(h[i][j]));
			}
			x[i] = x[i].divide(h[i][i]);
		}
		return x;
	}

	/**
	 * 雅克比迭代法
	 */
	public Fraction[] Jacobi(int k) {// 迭代k次
		init();
		while (k != 0) {
			Fraction[] y = new Fraction[n];
			for (int i = 0; i < n; i++) {
				y[i] = x[i];
			}
			for (int i = 0; i < n; i++) {
				Fraction num = new Fraction(0, 1);
				for (int j = 0; j < n; j++) {
					if (i == j) {
						num = num.add(b[i].divide(h[i][i]));
						continue;
					}
					num = num.subtract(y[j].multiple(h[i][j]).divide(h[i][i]));
				}
				x[i] = num;
			}
			k--;
		}
		return x;
	}

	/**
	 * 高斯-赛德尔迭代法
	 */
	public Fraction[] GS(int k) {
		init();
		while (k != 0) {
			for (int i = 0; i < n; i++) {
				Fraction num = new Fraction(0, 1);
				for (int j = 0; j < n; j++) {
					if (i == j) {
						num = num.add(b[i].divide(h[i][i]));
						continue;
					}
					num = num.subtract(x[j].multiple(h[i][j]).divide(h[i][i]));
				}
				x[i] = num;
			}
			k--;
		}
		return x;
	}

	/**
	 * 逐次超松弛迭代法
	 */
	public Fraction[] SOR(int k, Fraction w) {
		init();
		while (k != 0) {
			for (int i = 0; i < n; i++) {
				Fraction num = new Fraction(0, 1);
				for (int j = 0; j < n; j++) {
					if (i == j) {
						num = num.add(b[i].multiple(w).divide(h[i][i]));
						continue;
					}
					num = num.subtract(x[j].multiple(h[i][j]).multiple(w).divide(h[i][i]));
				}
				x[i] = num;
			}
			k--;
		}
		return x;
	}

	private void init() {
		for (int i = 0; i < h.length; i++) {
			for (int j = 0; j < h.length; j++) {
				h[i][j] = H[i][j];
			}
		}
		for (int i = 0; i < x.length; i++) {
			x[i] = new Fraction(0, 1);
		}
	}

}
