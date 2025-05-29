package br.edu.ifba.inf0008.uniEvents.exeptions;

public class CapacityExceededException extends UniEventsException{
  public CapacityExceededException(String eventCode, int capacity) {
    super("Event " + eventCode + " has reached its maximum capacity of " + capacity + " participants.");
  }
}