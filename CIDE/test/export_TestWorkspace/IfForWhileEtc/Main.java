class Main {
	void foo() {
		int i;
		for (i = 1; i < 54; i++) {
			a();
		}
		
		while (i == 3) {
			a();
		}
		
		if (true) {
			a();
		}
		
		if (true)
			a();
		
		if (false) {
			b();
		} else {
			a();
		}
		
		synchronized (this) {
			a();
		}
		
		if (false) {
			a();
		} else {
			b();
		}

		do {
			a();
		}while(true);
	}

	void a() {
	}

	void b() {
	}

}
