import java.io.PrintStream;

public class Stack {

	public Stack(int maxSize, StatisticObject s, PrintStream loggingTarget) {
		elementData = new Object[maxSize];
		logTarget = loggingTarget;
	}

	private int size = 0;
	private Object[] elementData;
	private PrintStream logTarget;
 
	public boolean push(Object o) {
		Lock lock = lock();
		elementData[size++] = o;
		log("pushed " + o + ", new size: " + size);
		unlock(lock);
		return true;
	} 
	
	private void unlock(Lock lock) {
		// .....
		//.................
		// .....
		//.................
	}


	public Object pop() {
		Lock lock = lock();
		if (lock == null) {
			log("lock failed for pop");
			return null;
		}
		Object r = elementData[--size];
		unlock(lock);
		return r;
	}

	private void log(String msg) {
		// .....
		//.................
		logTarget.println(msg);
	}


	private Lock lock() {
		// ...
		// .....
		//.................
		// .....
		//.................
		// .....
		//.................
		return new Lock();
	}


}
