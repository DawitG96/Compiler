package scanner;

public class LexicalException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public LexicalException() {
	}

	public LexicalException(String arg0) {
		super(arg0);
	}

	public LexicalException(Throwable arg0) {
		super(arg0);
	}

	public LexicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public LexicalException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}