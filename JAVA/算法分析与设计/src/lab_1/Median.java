package lab_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Median {
	private int n;
	private int[] a;
	private int[] b;
	private int median;

	public int getMid() {
		int x_mid = a.length - 1;
		int y_mid = b.length - 1;
		int i = a.length / 2;
		int j = b.length / 2;
		int m = 0;
		int p = 0;
		while ((m <= x_mid) && (p <= y_mid)) {
			if (a[x_mid] <= b[p])
				return a[x_mid];
			if (a[m] >= b[y_mid])
				return b[y_mid];
			if (a[i] == b[j]) {
				median = a[i];
				System.out.println("中位数为" + median);
				return a[i];
			}
			if (a[i] < b[j]) {
				m = i;
				i = (m + x_mid) / 2;
				y_mid = j;
				j = (y_mid + p) / 2;
			} else {
				x_mid = i;
				i = (x_mid + m) / 2;
				p = j;
				j = (p + y_mid) / 2;
			}
			if (m == x_mid) {
				median = a[m];
				System.out.println("中位数为" + median);
				return a[m];
			}
			if (p == y_mid) {
				median = b[p];
				System.out.println("中位数为" + median);
				return b[p];
			}
		}
		return -1;
	}

	public void readFile() {
		String line = null;
		String[] str = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/lab_1/input.txt"));//文件输入地址
			try {
				line = br.readLine();
				n = Integer.parseInt(line);
				a = new int[n];
				b = new int[n];
				line = br.readLine();
				str = line.split(" ");
				for (int i = 0; i < n; i++) {
					a[i] = Integer.parseInt(str[i]);
				}
				line = br.readLine();
				str = line.split(" ");
				for (int j = 0; j < n; j++) {
					b[j] = Integer.parseInt(str[j]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeFile() {
		File file = new File("src/lab_1/output.txt");//文件输出地址
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(Integer.toString(median));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Median m = new Median();
		m.readFile();
		m.getMid();
		m.writeFile();
	}
}
