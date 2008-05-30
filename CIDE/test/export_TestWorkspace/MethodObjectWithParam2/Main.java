class Main {
	int x;
	Dummy d=new Dummy();
	int foo(int j, int k) throws Exception {
		a();
		x++;
		d.i++;
		int i = k - j;
		a();
		k++;
		i++;
		a();
		return k + j;
	}

	void a() {

	}
}

