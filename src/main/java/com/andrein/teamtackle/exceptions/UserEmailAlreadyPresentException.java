package com.andrein.teamtackle.exceptions;

public class UserEmailAlreadyPresentException extends RuntimeException {
    public UserEmailAlreadyPresentException(String message) {
        super(message);
    }
}
