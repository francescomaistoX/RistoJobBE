package it.epicode.ristojob.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message){
      super(message);
  }
}
