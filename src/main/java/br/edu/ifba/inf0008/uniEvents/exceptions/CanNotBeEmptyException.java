package br.edu.ifba.inf0008.uniEvents.exceptions;

public class CanNotBeEmptyException extends UniEventsException {
    public CanNotBeEmptyException(String field) {
        super(field + " cannot be empty!");
    }

    public CanNotBeEmptyException() {
        super("The field cannot be empty!");
    }
}
