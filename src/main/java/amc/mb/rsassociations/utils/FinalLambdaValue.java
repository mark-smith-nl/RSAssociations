package amc.mb.rsassociations.utils;

/** Class to be used in lambda expressions. */
public class FinalLambdaValue<T> {

	private T value;

	public FinalLambdaValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
