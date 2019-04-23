package math;

public class Fraction {
	private int a;// 分子
	private int b;// 分母

	public Fraction(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return a+"/"+b;
	}

}
