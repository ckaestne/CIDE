class Main {
	void foo(int i) {
		int j = 1;
		a(j);
		try {
			a(j);
			b(i);
		} finally {
			b(j);
		}
		b(i);
	}

	void a(int x) {
	}

	void b(int x) {
	}

}
