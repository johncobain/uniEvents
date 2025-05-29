package br.edu.ifba.inf0008.uniEvents.exceptions;

public class NotANumberException extends UniEventsException {
  public NotANumberException(String message) {
    super(message);
  }
  public NotANumberException() {
    super("The input is not a valid number.");
  }
}
