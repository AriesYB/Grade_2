package math;


public class Equation {
	public final double[][] H;// 系数矩阵
	public final double[] b;// 方程右端向量
	public double[][] h;// 临时的变换的矩阵
	public double[] x;// 方程的解
	public int n;// 维数

	/**
	 * 初始化矩阵及右端
	 */
	public Equation(int n) {
		this.n = n;
		H = new double[n][n];
		b = new double[n];
		h = new double[n][n];
		x = new double[n];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				H[i - 1][j - 1] = (double) 1 / (i + j - 1);
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				b[i - 1] += H[i - 1][j - 1];
			}
		}

		System.out.println("系数矩阵(保留两位小数查看)以及右端为");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print((double) Math.round(H[i][j] * 100) / 100 + "  ");
			}
			System.out.println();
		}
		for (int i = 0; i < n; i++) {
			System.out.println((double) Math.round(b[i] * 100) / 100 + "  ");
		}
		System.out.println("-----------------------------------");

	}

	/**
	 * 高斯消去法
	 */
	public double[] Gauss() {
		init();
		double[] c = new double[n];
		for (int i = 0; i < c.length; i++) {
			c[i] = b[i];
		}
		for (int i = 0; i < n - 1; i++) {// 从第0行到第n-2行都会减
			for (int j = i + 1; j < n; j++) {// 消去第i列及后面的
				if (h[j][i] == 0) {
					continue;
				}
				double a = -h[j][i] / h[i][i];
				for (int k = 0; k < n; k++) {
					h[j][k] += a * h[i][k];
				}
				c[j] += a * c[i];
			}
		}
		for (int i = n - 1; i >= 0; i--) {
			x[i] = c[i];
			for (int j = n - 1; j > i; j--) {
				x[i] -= x[j] * h[i][j];
			}
			x[i] /= h[i][i];
		}
		return x;
	}

	/**
	 * 雅克比迭代法
	 */
	public double[] Jacobi(int k) {// 迭代k次
		init();
		double[] y = new double[n];
		while (k != 0) {
			for (int i = 0; i < n; i++) {
				y[i] = x[i];
			}
			for (int i = 0; i < n; i++) {
				double num = 0;
				for (int j = 0; j < n; j++) {
					if (i == j) {
						num += b[i] / h[i][i];
						continue;
					}
					num -= y[j] * h[i][j] / h[i][i];
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
	public double[] GS(int k) {
		init();
		while (k != 0) {
			for (int i = 0; i < n; i++) {
				double num = 0;
				for (int j = 0; j < n; j++) {
					if (i == j) {
						num += b[i];
						continue;
					} 
					num -= x[j] * h[i][j];
				}
				x[i] = num/h[i][i];
			}
			k--;
		}
		return x;
	}

	/**
	 * 逐次超松弛迭代法
	 */
	public double[] SOR(int k, double w) {
		init();
		while (k != 0) {
			for (int i = 0; i < n; i++) {
				double num = 0;
				for (int j = 0; j < n; j++) {
					if (i == j) {
						num += b[i]*w;
						continue;
					} 
					num -= x[j] * h[i][j];
				}
				x[i] = num/h[i][i]*w;
			}
			k--;
		}
		return x;
	}

	/**
	 * 每次计算后恢复h以及x
	 */
	private void init() {
		for (int i = 0; i < h.length; i++) {
			for (int j = 0; j < h.length; j++) {
				h[i][j] = H[i][j];
			}
		}
		for (int i = 0; i < x.length; i++) {
			x[i] = 0;
		}
	}

}
