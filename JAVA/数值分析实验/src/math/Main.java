package math;

public class Main {

	public static void main(String[] args) {
		int n = 6;// 维数
		int[][] H = new int[n][n];// 系数矩阵
		int[][] b = new int[n][1];// 方程右端向量
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				H[i-1][j-1] = 1/(i+j-1);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(H[i][j]+" ");
			}
			System.out.println();
		}
	}
}
