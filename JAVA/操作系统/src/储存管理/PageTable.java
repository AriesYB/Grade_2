package 储存管理;

import java.util.ArrayList;

public class PageTable {//页表

	class item {
		int page_number = -1;// 页号
		int mark = -1;// 标志位
		int block_number = -1;// 主存块号
		int modify = -1;// 修改标志
		int position = -1;// 磁盘上的位置

		item(int page_number, int position) {
			this.page_number = page_number;
			this.mark = 0;
			this.modify = 0;
			this.position = position;
		}

		item(int page_number, int mark, int block_number, int position) {
			this.page_number = page_number;
			this.mark = mark;
			this.block_number = block_number;
			this.modify = 0;
			this.position = position;
		}

	}

	PageTable(ArrayList<item> table,ArrayList<item> a) {
		table.add(new item(0, 1, 5, 11));
		table.add(new item(1, 1, 8, 12));
		table.add(new item(2, 1, 9, 13));
		table.add(new item(3, 1, 1, 21));
		table.add(new item(4, 22));
		table.add(new item(5, 23));
		table.add(new item(6, 121));
		for (item i : table) {
			if(i.mark==1) {
				inMainmemory(i, a);
			}
		}
	}
	
	private void inMainmemory(item i,ArrayList<item> a) {
		a.add(i);
	}
}
