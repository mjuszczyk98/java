package s241379.lab1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Klasa dziedzicząca po {@link AbstractFloatSorter} implementująca algorytm <b>MergeSort</b>
 * @author Michał Juszczyk
 *
 */
public class MergeFloatSorter extends AbstractFloatSorter {
	
	/**
	 * Metoda sortująca listę elementów typu {@link IElement}
	 * @param list - lista do posortowania
	 * @return Posortowana lista
	 */
	@Override
	public List<IElement> solve2(List<IElement> list){
		if(list.size() == 2) {
			if(list.get(0).getValue() > list.get(1).getValue()) {
				IElement v = list.get(0);
				list.remove(0);
				list.add(v);
			}
			return list;
			
		}
		else if(list.size() < 2)
			return list;
		
		Random r = new Random();
		
		float t = list.get(r.nextInt(list.size())).getValue();
		
		List<IElement> l1 = new LinkedList<IElement>();
		List<IElement> l2 = new LinkedList<IElement>();
		
		Iterator<IElement> it = list.iterator();
		IElement ie;
		
		while(it.hasNext()) {
			ie = it.next();
			if(ie.getValue() < t)
				l1.add(ie);
			else
				l2.add(ie);
		}
		
		List<IElement> retL = this.solve2(l1);
		retL.addAll(this.solve2(l2));
		
		list.clear();
		for(it = retL.iterator(); it.hasNext();)
			list.add(it.next());
		
		return list;
	}
	
	/**
	 * Metoda zwracająca opis algorytmy sortującego: <b>Merge sort for float</b>
	 * @return Opis algorytmu
	 */
	@Override
	public String description() {
		return "Merge sort for float";
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
	 * Metoda zwracająca czy algorytm działa w miejscu, w tej implementacji: <b>true</b>
	 * @return Algorytm działa w miejscu
	 */
	@Override
	public boolean isInSitu() {
		return true;
	}

}
