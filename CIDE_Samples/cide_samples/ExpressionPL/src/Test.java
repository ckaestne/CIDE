class Test {
	static Exp e;

	public static void main(String args[]) {
		Test.printtest();
		Test.evaltest();
	}

	static void evaltest() {
		e = new Num(1);
		System.out.println("eval(1) = " + e.eval());
		e = new Neg(new Num(1));
		System.out.println("eval(Neg(1)) = " + e.eval());
		e = new Plus(new Num(1), new Num(2));
		System.out.println("eval(1+2)=" + e.eval());
		e = new Neg(new Plus(new Num(1), new Num(2)));
		System.out.println("eval(-(1+2))=" + e.eval());
	}

	static void printtest() {
		e = new Num(3);
		System.out.println("print(3) = " + e);
		e = new Neg(new Num(5));
		System.out.println("print(Neg(5)) = " + e);
		e = new Plus(new Num(5), new Num(7));
		System.out.println("print(5+7) = " + e);
	}
}

interface Exp {
	int eval();

	String toString();
}

class Num implements Exp {
	int value;

	Num(int val) {
		value = val;
	}

	public int eval() {
		return value;
	}

	public String toString() {
		return "" + value;
	}
}

class Neg implements Exp {
	Exp x;

	Neg(Exp x) {
		this.x = x;
	}

	public int eval() {
		return -x.eval();
	}

	public String toString() {
		return " -(" + x + ") ";
	}
}

class Plus implements Exp {
	Exp x;
	Exp y;

	Plus(Exp x, Exp y) {
		this.x = x;
		this.y = y;
	}

	public int eval() {
		return x.eval() + y.eval();
	}

	public String toString() {
		return x + " + " + y;
	}
}