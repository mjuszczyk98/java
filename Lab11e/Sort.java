package lab11;

import java.util.ArrayList;
import java.util.List;

public class Sort implements SortMBean {
	
	private int threads = 5;
	private int buffer = 1000;
	private int max = 2000;
	
	List<ThreadLogic> list = new ArrayList<ThreadLogic>();

	public void sort() {
		while(true) {
			if(list.size() > threads) {
				ThreadLogic t = list.get(list.size() - 1);
				t.stopRunning();
				list.remove(t);
			} else if (list.size() < threads) {
				ThreadLogic t = new ThreadLogic();
				list.add(t);
				new Thread(t).start();
			}

			System.out.println(toString());
			ThreadLogic.cleanTmpRatio();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
		}
		
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public int getThreads() {
		return threads;
	}

	public void setBuffer(int buffer) {
		this.buffer = buffer;
		ThreadLogic.setBuffer(buffer);
		
	}

	public int getBuffer() {
		return buffer;
	}

	public void setMax(int max) {
		this.max = max;
		ThreadLogic.setMax(max);
		
	}

	public int getMax() {
		return max;
	}
	
	@Override
	public String toString() {
		return "------------------- \n" +
				"Threads: " + this.threads + "\n" +
				"Buffer: " + this.buffer + "\n" +
				"Max: " + this.max + "\n" +
				"Ratio: " + ThreadLogic.ratio() + "\n" + 	
				"TmpRatio: " + ThreadLogic.tmpRatio();	
	}

}
