class Main {

	private static class Result {
		public Integer i;
	}

	void foo() {
		Result r = new Result();
		a();
		r.i.byteValue();
		a();
		r = null;
	}

	void a() {
	}
}
