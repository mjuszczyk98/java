package lab3;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftHashMap <K, V>{
	
	private HashMap<K, SoftReference<V>> map = new HashMap<K, SoftReference<V>>();
	
	public void put(K key, V value) {
		SoftReference<V> v = new SoftReference<V>(value);
		map.put(key, v);
	}
	
	public V get(K key) {
		SoftReference<V> softV = map.get(key);
		if(softV == null) {
			return null;
		}
		V value = softV.get();
		if(value == null) {
			map.remove(key);
			return null;
		}
		return value;
	}
	
	public void remove(K key) {
		map.remove(key);		
	}
}
