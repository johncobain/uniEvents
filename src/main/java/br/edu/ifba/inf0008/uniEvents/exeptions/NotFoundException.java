package br.edu.ifba.inf0008.uniEvents.exeptions;

public class NotFoundException extends UniEventsException{
  public NotFoundException(String entity, String key){
    super(entity + " with key '" + key + "' not found.");
  }
}
