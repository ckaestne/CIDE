class Main {
	void foo() {
		int i=a();
		{b(i);}
		b(1);
		b(2);
	}

	int a() {
		return 1;
	}
	void b(int x){}

}
