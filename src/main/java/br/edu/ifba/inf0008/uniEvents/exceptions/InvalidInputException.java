package br.edu.ifba.inf0008.uniEvents.exceptions;

public class InvalidInputException extends UniEventsException {
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException() {
        super("Not a valid input.");
    }
}
