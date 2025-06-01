package org.kosa.mini2.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginFailedException(String message) {
        super(message);
    }
}
