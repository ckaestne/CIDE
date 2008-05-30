class Main {
	void foo(int j) {
		int i=2;
		a();
		b(j);
		i++;
		j++;
		c();
		d(i, j);
	}

	void a() {	}
	void b(int p) {	}
	void c() {	}
	void d(int p, int x) {	}
}
