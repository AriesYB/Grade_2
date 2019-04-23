package CPU调度;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Queue extends LinkedList<PCB> implements Comparator<PCB> {
	@Override
	public boolean add(PCB e) {
		Iterator<PCB> iterator = iterator();
		int index = 0;
		if(size()==0) {
			addFirst(e);
			return true;
		}
		while (iterator.hasNext()) {
			PCB current = iterator.next();
			if (compare(e, current) == 1) {// 插入的大于当前的
				add(index,e);
				return true;
			}
			index++;
		}
		if(index==size()) {
			addLast(e);
			return true;
		}
		return false;
	}
	@Override
	public int compare(PCB e1, PCB e2) {
		if (e1.getPriority() > e2.getPriority()) {
			return 1;
		} else if (e1.getPriority() == e2.getPriority()) {

			return 0;
		}
		return -1;
	}
}
