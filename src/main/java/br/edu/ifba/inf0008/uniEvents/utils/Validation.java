package br.edu.ifba.inf0008.uniEvents.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Validation {
  public static void validateCpf(String cpf){
    if(cpf == null || cpf.isEmpty() || cpf.isBlank()) {
      throw new IllegalArgumentException("CPF cannot be empty!");
    }
    if(cpf.matches(".*[a-zA-Z].*")) {
      throw new IllegalArgumentException("CPF cannot have letters!");
    }
    if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") && !cpf.matches("\\d{11}")) {
      throw new IllegalArgumentException("CPF must be in the format XXX.XXX.XXX-XX or XXXXXXXXXXX!");
    }

    String digits = cpf.replaceAll("[^\\d]", "");
    String checkDigits = digits.substring(digits.length() - 2);
    
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

  public static void validateEmail(String email){
    if(email == null || email.isEmpty() || email.isBlank()) {
      throw new IllegalArgumentException("Email cannot be empty!");
    }

    if(!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
      throw new IllegalArgumentException("Not a valid email!");
    }

  }

  public static void validatePhone(String phone){
    if(phone == null || phone.isEmpty() || phone.isBlank()) {
      throw new IllegalArgumentException("Phone cannot be empty!");
    }

    if(phone.matches(".*[a-zA-Z].*")) {
      throw new IllegalArgumentException("Phone cannot have letters!");
    }
    if(!phone.matches("\\d{2} \\d{4,5}-\\d{4}") &&
      !phone.matches("\\d{2} \\d{8,9}") &&
       !phone.matches("\\d{10,11}")) {
      throw new IllegalArgumentException("Not a valid phone number!");
    }
  }

  public static void validateDate(String date){
    if(date == null || date.isEmpty() || date.isBlank()) {
      throw new IllegalArgumentException("Date cannot be empty!");
    }

    if(date.matches(".*[a-zA-Z].*")) {
      throw new IllegalArgumentException("Date cannot have letters!");
    }
    if(
      !date.matches("\\d{2}/\\d{2}/\\d{4}") && 
      !date.matches("\\d{8}")) {
      throw new IllegalArgumentException("Not a valid date!");
    }

    if(date.matches("\\d{2}/\\d{2}/\\d{4}")) {
      try {
        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
      } catch (Exception e) {
        throw new IllegalArgumentException("Not a valid date!");
      }
    }else{
      try {
        LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(ResolverStyle.STRICT));
      } catch (Exception e) {
        throw new IllegalArgumentException("Not a valid date!");
      }
    }

  }

  public static void isInteger(String value){
    if (value == null || value.isEmpty() || value.isBlank()) {
      throw new IllegalArgumentException("Input cannot be empty!");
    }
    try {
      Integer.valueOf(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input, not an integer!");
    }
  }

  public static void isDouble(String value){
    if (value == null || value.isEmpty() || value.isBlank()) {
      throw new IllegalArgumentException("Input cannot be empty!");
    }
    try {
      Double.valueOf(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input, not a double!");
    }
  }

  public static void isLong(String value){
    if (value == null || value.isEmpty() || value.isBlank()) {
      throw new IllegalArgumentException("Input cannot be empty!");
    }
    try {
      Long.valueOf(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input, not a long!");
    }
  }

  public static void validateId(String id) {
    if (id == null || id.isEmpty() || id.isBlank()) {
      throw new IllegalArgumentException("ID cannot be empty!");
    }
    if (id.matches(".*[a-zA-Z].*")) {
      throw new IllegalArgumentException("ID cannot have letters!");
    }
    if (!id.matches("\\d{11}")) {
      throw new IllegalArgumentException("ID must be 11 digits long!");
    }
  }
}
