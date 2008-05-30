class Main {
	void foo() {
		if (true) {
			a();
		} else if (true){
			a();
		} else if (true){
			a();
		}
	}

	int a() {
		return 1;
	}
	
	void bar(){
		a();
		a();
		a();
	}
	
	void bar2(){
		a();
		a();
		a();
	}
}
