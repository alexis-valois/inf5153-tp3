package ca.uqam.inf5153.model;

/***
 *
 * @author Alexis Example tiré de :
 *         http://stackoverflow.com/questions/15554715/how-do-i-populate-a-
 *         javafx-choicebox-with-data-from-the-database
 */
public class KeyValuePair {
	private final Object key;
	private final Object value;

	public KeyValuePair(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
