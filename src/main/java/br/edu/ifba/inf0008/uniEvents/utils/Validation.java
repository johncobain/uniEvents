package br.edu.ifba.inf0008.uniEvents.utils;

public class Validation {
  public static void validateCpf(String cpf){
    if(cpf == null || cpf.isEmpty() || cpf.isBlank()) {
      throw new IllegalArgumentException("CPF cannot be empty!");
    }
    if(cpf.matches(".*[a-zA-Z].*")) {
      throw new IllegalArgumentException("CPF cannot have letters!");
    }
    if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
      throw new IllegalArgumentException("CPF must be in the format XXX.XXX.XXX-XX!");
    }

    String[] parts = cpf.split("\\.");
    String digits = parts[0] + parts[1] + parts[2].substring(0, 3) + parts[2].substring(4);
    String checkDigits = parts[2].substring(4);
    
    if(digits.equals("0000000000000") || digits.equals("11111111111") || 
    digits.equals("22222222222") || digits.equals("33333333333") || 
    digits.equals("44444444444") || digits.equals("55555555555") || 
    digits.equals("66666666666") || digits.equals("77777777777") || 
    digits.equals("88888888888") || digits.equals("99999999999")) {
      throw new IllegalArgumentException("Not a valid CPF!");
    }
    int sum = 0 ,weight = 10;

    for (int i = 0; i < 9; i++) {
        sum += Integer.parseInt(digits.charAt(i) + "") * weight;
        weight--;
    }
    int remainder = 11 - (sum % 11);
    if (remainder >= 10) remainder = 0;
    if (remainder != Integer.parseInt(checkDigits.charAt(0) + "")) {
        throw new IllegalArgumentException("Not a valid CPF!");
    }

    sum = 0;
    weight = 11;
    for (int i = 0; i < 10; i++) {
        sum += Integer.parseInt(digits.charAt(i) + "") * weight;
        weight--;
    }
    remainder = 11 - (sum % 11);
    if (remainder == 10 || remainder == 11) {
        remainder = 0;
    }
    if (remainder != Integer.parseInt(checkDigits.charAt(1) + "")) {
        throw new IllegalArgumentException("Not a valid CPF!");
    }
  }


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
