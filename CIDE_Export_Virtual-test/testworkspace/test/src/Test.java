public class Test {
	private void foo() {
		foo();
		int i = 3;
		int j = 5;
		j = j + i;
		foo();
	}
}
