class Main {
	void foo() {
		try {
			a(1);
			a(2);
			a(3);
		} finally {
		}
	}

	void bar() {
		try {
			a(4);
			a(5);
			a(6);
		} finally {
		}
	}

	void a(int x) {
	}

}
