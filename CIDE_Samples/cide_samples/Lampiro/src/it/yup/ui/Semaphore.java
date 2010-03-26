package it.yup.ui;

public class Semaphore {
	/** current number of available permits **/
	protected long permits_;

	/** 
	 * Create a Semaphore with the given initial number of permits.
	 * Using a seed of one makes the semaphore act as a mutual exclusion lock.
	 * Negative seeds are also allowed, in which case no acquires will proceed
	 * until the number of releases has pushed the number of permits past 0.
	**/
	public Semaphore(long initialPermits) {
		permits_ = initialPermits;
	}

	/** Wait until a permit is available, and take one **/
	public void acquire() throws InterruptedException {
		synchronized (this) {
			try {
				while (permits_ <= 0)
					wait();
				--permits_;
			} catch (InterruptedException ex) {
				notify();
				throw ex;
			}
		}
	}

	/** Wait at most msecs millisconds for a permit. **/
	public boolean attempt(long msecs) throws InterruptedException {
		synchronized (this) {
			if (permits_ > 0) {
				--permits_;
				return true;
			} else if (msecs <= 0) return false;
			else {
				try {
					long startTime = System.currentTimeMillis();
					long waitTime = msecs;

					for (;;) {
						wait(waitTime);
						if (permits_ > 0) {
							--permits_;
							return true;
						} else {
							waitTime = msecs
									- (System.currentTimeMillis() - startTime);
							if (waitTime <= 0) return false;
						}
					}
				} catch (InterruptedException ex) {
					notify();
					throw ex;
				}
			}
		}
	}

	/** Release a permit **/
	public synchronized void release() {
		++permits_;
		notify();
	}

	/** 
	 * Release N permits. <code>release(n)</code> is
	 * equivalent in effect to:
	 * <pre>
	 *   for (int i = 0; i < n; ++i) release();
	 * </pre>
	 * <p>
	 * But may be more efficient in some semaphore implementations.
	 * @exception IllegalArgumentException if n is negative.
	 **/
	public synchronized void release(long n) {
		if (n < 0) throw new IllegalArgumentException("Negative argument");

		permits_ += n;
		for (long i = 0; i < n; ++i)
			notify();
	}

	/**
	 * Return the current number of available permits.
	 * Returns an accurate, but possibly unstable value,
	 * that may change immediately after returning.
	 **/
	public synchronized long permits() {
		return permits_;
	}

}
