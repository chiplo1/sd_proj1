package utils;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CFifo {
	
	private Integer size;
	private Integer[] fifo;
	final Lock rl = new ReentrantLock();
	private final Condition isEmpty;
	private final Condition notFull;
	
	private Integer count = 0;
	private Integer idxWrite = 0;
	private Integer idxRead = 0;
	
	public CFifo(Integer size) {
		this.size = size;
		this.fifo = new Integer[size];
		this.isEmpty = rl.newCondition();
		this.notFull = rl.newCondition();
	}
	public Integer read() {
		Integer value = null;
		try {
			rl.lock();
			while(count==0)
				isEmpty.await();
			value = fifo[idxRead];
			idxRead = (idxRead+1) % size;
			count--;
			
			notFull.signal();
			notFull.signalAll();
		} catch(Exception ex) {}
		finally {
			rl.unlock();
		}
		return value;
	}
	public void write(Integer value) {
		try {
			rl.lock();
			while(count==size)
				notFull.await();
			fifo[idxWrite] = value;
			idxWrite = (idxWrite+1) % size;
			count++;
			
			isEmpty.signal();
			isEmpty.signalAll();
		} catch(Exception ex) {}
		finally {
			rl.unlock();
		}
	}
	public String toString() {
		return Arrays.toString(fifo);
	}
}
