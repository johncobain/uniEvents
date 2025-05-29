package br.edu.ifba.inf0008.uniEvents.exceptions;

public class NotFoundException extends UniEventsException{
  public NotFoundException(String entity, String key){
    super(entity + " with key '" + key + "' not found.");
  }
}
