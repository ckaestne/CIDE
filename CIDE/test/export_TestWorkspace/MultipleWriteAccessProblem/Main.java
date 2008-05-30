class Main {
	void foo() {
		int i=a();
		int j=a();
		i+=j;
		b(i);
	}

	int a() {
		return 1;
	}
	void b(int x){}

}
