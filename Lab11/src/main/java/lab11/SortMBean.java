package lab11;

public interface SortMBean {
	
	public void sort();
	
	public void setThreads(int threads);
	public int getThreads();
	
	public void setBuffer(int buffer);
	public int getBuffer();
	
	public void setMax(int max);
	public int getMax();
}
