package s241379.lab1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa dziedzicząca po {@link AbstractIntSorter} implementująca algorytm <b>CountingSort</b>
 * @author Michał Juszczyk
 *
 */
public class CountingIntSorter extends AbstractIntSorter {
	
	/**
	 * Metoda sortująca listę elementów typu {@link IntElement}
	 * @param list - lista do posortowania
	 * @return Posortowana lista
	 */
	@Override
	public List<IntElement> solve(List<IntElement> list){
		
		LinkedList<IntElement> al = new LinkedList<IntElement>();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getValue() < min)
				min = (int)list.get(i).getValue();
			if(list.get(i).getValue() > max)
				max = (int)list.get(i).getValue();
		}
		
		byte[] tab = new byte[max - min + 1];
		IntElement ie;
		Iterator<IntElement> it = list.iterator();
		
		List<ArrayList<String>> s = new ArrayList<ArrayList<String>>(max-min+1);
		for(int i = 0; i < max-min+1; i++)
			s.add(new ArrayList<String>());
		
		while(it.hasNext()) {
			ie = it.next();
			tab[(int)ie.getValue() - min]++;
			s.get((int)(ie.getValue() - min)).add(ie.getName());
		}
		
		
		
		int index = 0;
		int index2 = 0;
		for(int i = 0; i < max - min + 1; i++) {
			while(tab[index] == 0) {
				index++;
				if(index > max - min)
					break;
				index2 = 0;
			}
			if(index > max - min)
				break;
			tab[index]--;
			al.add(new IntElement(s.get(index).get(index2), index + min));
			index2++;
		}
		
		return al;
	}
	
	/**
	 * Metoda zwracająca opis algorytmy sortującego: <b>Counting sort for int</b>
	 * @return Opis algorytmu
	 */
	@Override
	public String description() {
		return "Counting sort for int";
	}
	
	/**
	 * Metoda zwracająca czy algorytm jest stabilny, w tej implementacji: <b>true</b>
	 * @return Algorytm stabilny
	 */
	@Override
	public boolean isStable() {
		return true;
	}
	
	/**
	 * Metoda zwracająca czy algorytm działa w miejscu, w tej implementacji: <b>false</b>
	 * @return Algorytm działa w miejscu
	 */
	@Override
	public boolean isInSitu() {
		return false;
	}
}
