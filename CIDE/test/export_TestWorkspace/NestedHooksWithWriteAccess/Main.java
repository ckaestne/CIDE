class Main {
	void foo() {
		a(0);
		int i = 1;

		try {
			a(1);
			i=a(i);
			a(2);
		} finally {
		}

		a(i+3);
	}

	int a(int i) {
		return i;
	}

}
