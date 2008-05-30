class Main {
	int foo() {
		a();
		try {
			a();
			return 1;
		} finally {
			a();
		}
	}

	void a() {
	}

}
