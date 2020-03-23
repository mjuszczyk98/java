package s241379.lab1;

import java.util.List;
/**
 * Abstrakcyjna klasa będąca bazą do klas sortujących listę elementów typu {@link IntElement}
 * @author Michał Juszczyk
 */
public abstract class AbstractIntSorter{
	
	/**
	 * Metoda służąca do posortowania elementów typu {@link IntElement}
	 * @param list - lista do posortowania
	 * @return Posortowana lista
	 */
	public List<IntElement> solve(List<IntElement> list){
		return null;
	}
	
	/**
	 * Metoda zwracająca opis algorytmy sortującego
	 * @return Opis algorytmu
	 */
	public String description() {
		return null;
	}
	
	/**
	 * Metoda zwraca wartość <i>true</i> jeżeli algorytm jest stabilny
	 * @return Algorytm stabilny
	 */
	public boolean isStable() {
		return true;
	}
	
	/**
	 * Metoda zwraca wartość <i>true</i> jeżeli algorytm działa w miejscu
	 * @return Algorytm działa w miejscu
	 */
	public boolean isInSitu() {
		return true;
	}
}