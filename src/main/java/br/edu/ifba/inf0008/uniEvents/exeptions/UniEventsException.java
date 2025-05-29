package br.edu.ifba.inf0008.uniEvents.exeptions;

public class UniEventsException extends RuntimeException{
  public UniEventsException(String message) {
    super(message);
  }
  public UniEventsException(String message, Throwable cause) {
    super(message, cause);
  }
}
