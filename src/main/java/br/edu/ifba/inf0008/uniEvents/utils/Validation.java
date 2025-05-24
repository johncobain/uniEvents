package br.edu.ifba.inf0008.uniEvents.utils;

public class Validation {
  public static void validateCpf(String cpf){}

  public static void validateEmail(String email){}

  public static void validatePhone(String phone){}

  public static void validateDate(String date){}

  public static int isInteger(String value){
    if (value == null || value.isEmpty() || value.isBlank()) {
      throw new IllegalArgumentException("Input cannot be empty!");
    }
    try {
      int parsedValue = Integer.parseInt(value);
      return parsedValue;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input, not an integer!");
    }
  }
}
