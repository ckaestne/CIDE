class Main {
	static int x;
	static int foo(int j, int k) throws Exception {
		a();
		x++;
		int i = k - j;
		a();
		k++;
		i++;
		a();
		return k + j;
	}

	static void a() {

	}
}

