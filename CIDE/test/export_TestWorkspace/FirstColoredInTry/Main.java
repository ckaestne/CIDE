class Main {
	void foo() {
		a();
		try {
			b();
			a();
		} finally {
			b();
		}
		a();
	}

	void a() {
	}

	void b() {
	}

}
