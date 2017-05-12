package ca.uqam.inf5153.enums;

public enum Direction {
	HORIZONTALE("Horizontal"), VERTICAL("Vertical");

	private String value;

	Direction(String value) {
		this.value = value;
	}

	public static Direction fromString(String text) {
		if (text != null) {
			for (Direction role : Direction.values()) {
				if (text.equalsIgnoreCase(role.toString())) {
					return role;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return value;
	}
}
