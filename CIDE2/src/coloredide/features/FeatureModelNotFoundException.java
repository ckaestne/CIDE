package coloredide.features;

public class FeatureModelNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public FeatureModelNotFoundException(String string) {
		super(string);
	}

	public FeatureModelNotFoundException(String string, Exception e) {
		super(string, e);
	}

	public FeatureModelNotFoundException() {
		super();
	}

}
