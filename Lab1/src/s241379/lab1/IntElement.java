package s241379.lab1;

/**
 * Klasa wykorzystująca interfejs {@link IElement} przechowująca nazwe i wartość (int)
 * @author Michał Juszczyk
 *	
 */
public class IntElement implements IElement{
	
	/**
	 * Zmienna przechowująca nazwe
	 */
	String name;
	
	/**
	 * Zmienna przechowująca wartość (int)
	 */
	int value;
	
	/**
	 * Pusty domyślny konstruktor
	 */
	public IntElement() {
		
	}
	
	/**
	 * Konstruktor ustawiający nazwę i wartość
	 * @param s - nazwa
	 * @param v - wartość
	 */
	public IntElement(String s, int v) {
		this.name = s;
		this.value = v;
	}
	
	/**
	 * Nadpisana metoda interfejsu zwracająca nazwę
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Nadpisana metoda interfejsu zwracająca wartość
	 * @return value
	 */	
	@Override
	public float getValue() {
		return value;
	}
	
}
