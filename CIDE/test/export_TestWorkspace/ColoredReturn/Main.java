import java.util.ArrayList;

class Main {
	boolean foo() {
		int i = a();
		if (i == 1) {
			return true;
		}
		i++;
		return false;
	}
	
	ArrayList bar() {
		int i = a();
		if (i == 1) {
			return new ArrayList();
		}
		i++;
		return new ArrayList();
	}
	
	void foobar() {
		int i = a();
		if (i == 1) {
			return;
		}
		i++;
	}

	int a() {
		return 1;
	}
}
