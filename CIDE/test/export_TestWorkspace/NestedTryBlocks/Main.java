class Main {
	void foo() {
		try {
			a();
			try {
				b();
			} finally {
				b();
			}
			b();
		} finally {
			a();
		}
	}

	void a() {
	}

	void b() {
	}

}
