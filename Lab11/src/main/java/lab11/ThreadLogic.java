package lab11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import s241379.lab1.IElement;
import s241379.lab1.InsertFloatSorter;
import s241379.lab1.IntElement;

public class ThreadLogic implements Runnable {
	
	private static final long MIN = 1;
	private static final long MULTIPLY_BY = 5717;
	private static final long INCREASE_BY = 137;
	private static final long MODULO = 7993;
	private static final long SIZE = 10;
	
	private static long g1 = 0;
	private static long g2 = 0;
	private static long m1 = 0;
	private static long m2 = 0;
	
	private static int buffer = 1000;
	private static long max = 2000;
	
	private boolean run = true;
	
	public static HashMap<Long, List<IElement>> map = new HashMap<Long, List<IElement>>();
	public static InsertFloatSorter sorter = new InsertFloatSorter();
	
	public List<IElement> makeList(long seed){
		List<IElement> list = new LinkedList<IElement>();
		int value;
		for(int i = 0; i < SIZE; i++) {
			seed = (MULTIPLY_BY * seed + INCREASE_BY) % MODULO;
			value = (int) (seed % Integer.MAX_VALUE);
			list.add(new IntElement("", value));
		}
		return list;
	}
	
	public void sort(long seed) {
		
		synchronized((Long)g2) {
			g1++;
			g2++;
		}
		
		List<IElement> list;
		
		synchronized(map) {
			list = map.get(seed);
		}
		
		if(list == null) {
			synchronized((Long)m2) {
				m1++;
				m2++;
			}
			
			synchronized(map) {
				while(map.size() > buffer) {
					map.remove(map.keySet().toArray()[0]);
				}
			}
			
			list = makeList(seed);
			
			list = sorter.solve2(list);
			
			synchronized (map) {
				map.put(seed, list);				
			}
		}
	}
	
	public void stopRunning() {
		run = false;
	}
	
	public static double tmpRatio() {
		return (double)m2/(double)g2;
	}
	
	public static double ratio() {
		return (double)m1/(double)g1;
	}
	
	public static void cleanTmpRatio() {
		synchronized((Long)g2) {
			synchronized((Long)m2) {
				m2 = 0;
				g2 = 0;
			}
		}
	}
	
	public static void setBuffer(int buffer) {
		ThreadLogic.buffer = buffer;
	}
	
	public static void setMax(int max) {
		ThreadLogic.max = max;
	}

	public void run() {
		while(run) {
			long seed = MIN + (long)(Math.random()*(max-MIN));
			sort(seed);
		}
	}
}
