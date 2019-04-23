package com.huawei;

public class T {

	public static void main(String[] args) {
		System.out.println("读取文件···");
		Console.read("C://Users/管理员/Desktop/car.txt", "C://Users/管理员/Desktop/road.txt",
				"C://Users/管理员/Desktop/cross.txt");
		System.out.println("调度运行中···");
		Console.launch();
		System.out.println("打印结果···");
		Console.write("C://Users/管理员/Desktop/answer.txt");
	}

}
