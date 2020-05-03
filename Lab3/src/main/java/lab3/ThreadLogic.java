package lab3;

import java.util.LinkedList;
import java.util.List;

import s241379.lab1.IElement;
import s241379.lab1.IntElement;

public class ThreadLogic implements Runnable {
	private static final long MIN = 1;
	private static final long MAX = 1000000;
	private static final long MULTIPLY_BY = 5717;
	private static final long INCREASE_BY = 137;
	private static final long MODULO = 7993;
	private static final long SIZE = 10;
	
	private static long g1 = 0;
	private static long g2 = 0;
	private static long m1 = 0;
	private static long m2 = 0;
	
	private static boolean run = true;
	
	private static SoftHashMap<Long, List<IElement>> map = new SoftHashMap<Long, List<IElement>>();
	
	
	
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
		int s = Main.methods.size();
		if(s == 0)
			return;
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
			list = makeList(seed);
			int method = (int)(Math.random()*s);
			try {
				synchronized(Main.methods) {
				Main.methods.get(method).invoke(Main.classes.get(method).newInstance(), list);
				}
			} catch (Exception e) {
				return;
			}
			
			synchronized (map) {
				map.put(seed, list);				
			}
		}
	}
	
	public static void stopRunning() {
		run = false;
	}
	
	public static boolean running() {
		return run;
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

	public void run() {
		while(run) {
			long seed = MIN + (long)(Math.random()*(MAX-MIN));
			sort(seed);
		}
	}
}
