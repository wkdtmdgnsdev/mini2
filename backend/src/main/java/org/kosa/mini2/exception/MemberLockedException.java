package org.kosa.mini2.exception;

public class MemberLockedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public MemberLockedException(String message) {
        super(message);
    }
}
