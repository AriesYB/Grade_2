package 分数;

public class Fraction {
	private long a;// 分子
	private long b;// 分母

	public Fraction(long a, long b) {
		super();
		this.a = a;
		this.b = b;
		deal();
	}

	public Fraction add(Fraction f) {
		return new Fraction(a * f.b + f.a * b, b * f.b);
	}

	public Fraction subtract(Fraction f) {
		return new Fraction(a * f.b - f.a * b, b * f.b);
	}

	public Fraction multiple(Fraction f) {
		return new Fraction(a * f.a, b * f.b);
	}

	public Fraction divide(Fraction f) {
		return new Fraction(a * f.b, b * f.a);
	}

	public boolean isZero() {
		return a == 0;
	}

	private void deal() {// 简单约分
		for (int i : new int[] { 2, 3, 5, 7 }) {
			while (a % i == 0 && b % i == 0) {
				a /= 2;
				b /= 2;
			}
		}
	}

	public double get() {
		if(b==0) {
			return -99999999.99999;
		}
		return  Math.round(a / b * 10000) / 10000;
	}

	@Override
	public String toString() {
		return a + "/" + b;
	}

}
