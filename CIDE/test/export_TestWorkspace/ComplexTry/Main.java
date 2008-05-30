class Main {
	void foo() {
		int i=a();
		try {
			if (i==1) {
				a();
			}
			a();
		} finally {
			a();
		}
	}

	int a() {
		return 1;
	}
}
