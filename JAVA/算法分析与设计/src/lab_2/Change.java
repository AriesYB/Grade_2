package lab_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Change {
	private int kinds;// 硬币种类数目
	private int cion_kind[];// 硬币面值ֵ
	private int money;// 要找的钱
	private String num;// 最小找钱数目

	public int getMinCount (int m,int ck[],int cl[]) {
	     int a[]=new int[m+1];
	     a[0]=0;
	     for(int x=1;x<m+1;x++){ 
	         if(x>=ck[0]){  
	             a[x]=a[x-ck[0]]+1;
	             cl[x]=ck[0];
	         }else{
	             a[x]=Integer.MAX_VALUE- m;
	         }
	         for(int i=1;i<ck.length;i++){
	             if(x>=ck[i]&&(a[x]>a[x-ck[i]]+1)){
	                  a[x]=a[x-ck[i]]+1;
	                  cl[x]=ck[i];
	             }
	         }
	     }
	     return a[m];
	}

	public void print() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入要找的钱");
		money = Integer.parseInt(s.next());
		int cionList[] = new int[money + 1];
		int min = getMinCount(money, cion_kind, cionList);
		if (min > Integer.MAX_VALUE - money) {
			num = "∞";
			System.out.println("总额为" + money + "，需要最少的硬币为" + num);
		} else {
			num = Integer.toString(min);
			System.out.println("总额为" + money + "，需要最少硬币为" + num);
		}
	}

	public void readFile() {
		String line = null;
		String[] str = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/lab_1/input.txt"));
			try {
				line = br.readLine();
				kinds = Integer.parseInt(line);
				cion_kind = new int[kinds];
				line = br.readLine();
				str = line.split(" ");
				for (int i = 0; i < str.length; i++) {
					cion_kind[i] = Integer.parseInt(str[i]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void writeFile() {
		File file = new File("src/lab_2/output.txt");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(num);
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
		Change tc = new Change();
		tc.readFile();
		tc.print();
		tc.writeFile();
	}
}
