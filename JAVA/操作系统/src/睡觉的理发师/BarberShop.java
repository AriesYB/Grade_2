package 睡觉的理发师;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class BarberShop {

	private static int customer = 0;// 顾客数量
	private final int MAX = 5;// 椅子数量
	private static boolean sleep = true;// 理发师是否睡觉
	private static Semaphore mutex = new Semaphore(1);// 临界区互斥访问信号量，相当于互斥锁

	public synchronized boolean isFull() {
		if (customer == MAX) {
			return true;
		}
		return false;
	}

	public synchronized boolean isEmpty() {
		if (customer == 0) {
			return true;
		}
		return false;
	}

	public synchronized boolean isSleep() {
		return sleep;
	}

	public void haircut(int i) throws InterruptedException {
		System.out.println("第" + i + "位顾客到了");
		customer++;
		// 判断是否满
		if (isFull()) {
			System.out.println("座位已满，" + "顾客" + i + "离开");
			customer--;
		} else {
			if (!isSleep()) {
				System.out.println("第" + i + "位顾客正在等待理发师");
			}
			mutex.acquire();// 信号量减少，防止其他进程进入
			synchronized (this) {
				while (!isSleep()) {
					wait();
				}
			}
			if (customer == 1) {
				System.out.println("现在只有顾客" + i + "，理发师是清醒的");
			}
			sleep = false;
			System.out.println("顾客" + i + "正在理发");
			Thread.sleep(1000);
			System.out.println("顾客" + i + "离开");
			customer--;
			mutex.release();// 信号量增加
			synchronized (this) {
				sleep = true;
				notify();// 唤醒
			}
			if (customer == 0) {
				System.out.println("无顾客，理发师睡觉");
			}
		}

	}

	class Barber implements Runnable{
		BarberShop barbershop;
		int index;
		
		public Barber(BarberShop barbershop, int i) {
			super();
			this.barbershop = barbershop;
			this.index = i;
		}
		
		@Override
		public void run() {
			try {
				barbershop.haircut(index);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		BarberShop bars = new BarberShop();
		System.out.println("请输入来理发的人数：");
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		scan.close();
		for (int i = 1; i <= num; i++) {
			new Thread(bars.new Barber(bars,i)).start();
			Thread.sleep((int)(400 - Math.random()*300));
		}
	}
}
