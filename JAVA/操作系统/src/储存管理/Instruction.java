package 储存管理;

import java.util.ArrayList;

public class Instruction {// 指令表
	class ins {
		int page_number = -1;// 页号
		int unit_number = -1;// 单元号
		int operate = -1;// 存的操作

		ins(int page_number, int unit_number) {
			super();
			this.page_number = page_number;
			this.unit_number = unit_number;
			this.operate = 0;
		}

		ins(int page_number, int unit_number, int operate) {
			super();
			this.page_number = page_number;
			this.unit_number = unit_number;
			this.operate = operate;
		}

	}

	public Instruction(ArrayList<ins> t) {
		t.add(new ins(0, 70));
		t.add(new ins(1, 50));
		t.add(new ins(2, 15));
		t.add(new ins(3, 21, 1));
		t.add(new ins(0, 56));
		t.add(new ins(6, 40));
		t.add(new ins(4, 53));
		t.add(new ins(5, 23));
		t.add(new ins(1, 37, 1));
		t.add(new ins(2, 78));
		t.add(new ins(4, 1));
		t.add(new ins(6, 84, 1));
	}
}
