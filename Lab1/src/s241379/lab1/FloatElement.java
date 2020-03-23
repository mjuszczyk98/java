package s241379.lab1;

/**
 * Klasa wykorzystująca interfejs {@link IElement} przechowująca nazwe i wartość (float)
 * @author Michał Juszczyk
 *	
 */
public class FloatElement implements IElement{
	
	/**
	 * Zmienna przechowująca nazwe
	 */
	String name;
	
	/**
	 * Zmienna przechowująca wartość (float)
	 */
	float value;
	
	/**
	 * Konstruktor ustawiający nazwę i wartość
	 * @param name - nazwa
	 * @param value - wartość
	 */
	public FloatElement(String name, float value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Nadpisana metoda interfejsu zwracająca nazwę
	 * @return Nazwa
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Nadpisana metoda interfejsu zwracająca wartość
	 * @return Wartość
	 */	
	@Override
	public float getValue() {
		return value;
	}
	
}