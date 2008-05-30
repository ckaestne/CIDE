class Main {
	void foo() {
		a();
		try {
			a();
			b();
		} finally {
			b();
		}
		b();
	}

	void a() {
	}

	void b() {
	}

}
