class Main {

	void foo() throws ClassNotFoundException {
		a();

		try {
			a();
			b();
		} catch (CloneNotSupportedException e) {
		} catch (ClassNotFoundException e) {
		}
		a();

	}

	void a() throws ClassNotFoundException {
	}

	void b() throws CloneNotSupportedException {
	}
}
