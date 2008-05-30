class Main {
	void foo() {
		try {
			a();
		} finally {
			b();
		}
		try {
			a();
		} finally {
			b();
		}
	}

	void a() {
	}

	void b() {
	}

	
}
