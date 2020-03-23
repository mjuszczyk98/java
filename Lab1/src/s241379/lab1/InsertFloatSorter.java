package s241379.lab1;

import java.util.List;

/**
 * Klasa dziedzicząca po {@link AbstractFloatSorter} implementująca algorytm <b>InsertSort</b>
 * @author Michał Juszczyk
 *
 */
public class InsertFloatSorter extends AbstractFloatSorter {
	
	/**
	 * Metoda sortująca listę elementów typu {@link IElement}
	 * @param list - lista do posortowania
	 * @return Posortowana lista
	 */
	@Override
	public List<IElement> solve2(List<IElement> list){
		for(int i = 1; i < list.size(); i++) {
			insert(list, i, find(list, list.get(i).getValue()));
		}
		return list;
	}
	
	/**
	 * Metoda pomocnicza, pomagająca znaleźć odpowiednie miejsce dla elementu
	 * @param list - sortowana lista
	 * @param val - wartośc, dla której szykamy miejsca
	 * @return index w którym ma zostać umieszczona wartość
	 */
	public int find(List<IElement> list, float val) {
		for(int index = 0; index < list.size(); index++) {
			if(list.get(index).getValue() > val)
				return index;
		}
		return -1;
	}
	
	/**
	 * Metoda pomocnicza, pomagająca przenieść elemetn w odpowiedznie miejsce
	 * @param list - sortowana lista
	 * @param from - index z którego przenosimy element
	 * @param where - index do którego ma trafić element
	 */
	public void insert (List<IElement> list, int from, int where) {
		if(from <= where || where == -1)
			return;
		IElement i = list.get(from);
		list.remove(from);
		list.add(where, i);
	}

	/**
	 * Metoda zwracająca opis algorytmy sortującego: <b>Insert sort for float</b>
	 * @return Opis algorytmu
	 */
	@Override
	public String description() {
		return "Insert sort for float";
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
