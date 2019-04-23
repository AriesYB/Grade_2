package 储存管理;

import java.util.ArrayList;

import 储存管理.Instruction.ins;
import 储存管理.PageTable.item;

public class Simulate {
	static ArrayList<item> table = new ArrayList<item>();// 分页表
	static ArrayList<ins> instruction = new ArrayList<ins>();// 指令表
	static ArrayList<item> mainmemory = new ArrayList<item>();// 存入主存的页面

	static void translate() {// 模拟分页式存储管理中硬件的地址转换和产生缺页中断
		System.out.println("模拟分页式存储管理中硬件的地址转换和产生缺页中断：");
		for (int n = 0; n < instruction.size(); n++) {
			ins i = instruction.get(n);
			System.out.print("第" + n + "条指令 ");
			if (i.page_number <= table.size()) {// 存在该页号
				if (table.get(i.page_number).mark == 1) {// 标志为1
					System.out.println("绝对地址：" + table.get(i.page_number).block_number * 128 + i.unit_number);
				} else {
					System.out.println("*" + table.get(i.page_number).page_number);
				}
			} else {
				System.out.println("不存在该页号");
			}
		}
		System.out.println("------------------------------");
	}

	static void fifo() {// 用先进先出（FIFO）页面调度算法处理缺页中断
		int k=0;
		System.out.println("用先进先出（FIFO）页面调度算法处理缺页中断：");
		for (int n = 0; n < instruction.size(); n++) {
			ins i = instruction.get(n);
			System.out.print("第" + n + "条指令 ");
			if (i.page_number <= table.size()) {// 存在该页号
				if (table.get(i.page_number).mark == 1) {// 标志为1
					if (i.operate==1) {
						table.get(i.page_number).modify=1;
					}
					System.out.println("绝对地址：" + table.get(i.page_number).block_number * 128 + i.unit_number);
					continue;
				} else {//产生缺页中断
					item current = mainmemory.get(k);
					if(current.modify==1) {
						System.out.println("OUT "+current.page_number);
					}else {
						System.out.println("IN "+table.get(i.page_number).page_number);
					}
					mainmemory.remove(k);
					mainmemory.add(k, table.get(i.page_number));
					mainmemory.get(k).mark=1;
					mainmemory.get(k).block_number=current.block_number;
					k=(k+1)%mainmemory.size();
					n--;
					continue;
				}
			} else {
				System.out.println("不存在该页号");
			}
		}
		System.out.println("------------------------------");
	}

	public static void main(String[] args) {
		new PageTable(table, mainmemory);// 载入页表，并将一定页面装入主存
		new Instruction(instruction);// 载入指令表
		translate();// 问题一
		fifo();// 问题二
	}
}
