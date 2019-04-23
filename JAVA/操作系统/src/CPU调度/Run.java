package CPU调度;

public class Run {

	public static void main(String[] args) {
		CPU cpu = new CPU();
		System.out.println("Input Name,needTime and Priority");
		for (PCB p : cpu.list) {
			if (p == null) {
				continue;
			}
			System.out.println(
					"    " + p.getName() + "         " + p.getNeedTime() + "           " + p.getPriority() + "    ");
		}
		System.out.println("Output of priority:");
		System.out.println("Name  CPUTime  NeedTime  Priority  State");
		cpu.print();
		while (!cpu.q.isEmpty()) {
			cpu.execute(cpu.q.peek());
		}

	}

}
