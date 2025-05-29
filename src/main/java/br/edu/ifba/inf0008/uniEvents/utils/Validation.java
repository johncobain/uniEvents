package br.edu.ifba.inf0008.uniEvents.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import br.edu.ifba.inf0008.uniEvents.exceptions.CanNotBeEmptyException;
import br.edu.ifba.inf0008.uniEvents.exceptions.InvalidInputException;
import br.edu.ifba.inf0008.uniEvents.exceptions.NotANumberException;
import br.edu.ifba.inf0008.uniEvents.exceptions.UniEventsException;

public class Validation {
  public static void validateCpf(String cpf) throws UniEventsException {
    if(cpf == null || cpf.isBlank()) {
      throw new CanNotBeEmptyException("CPF");
    }
    if(cpf.matches(".*[a-zA-Z].*")) {
      throw new InvalidInputException("CPF cannot have letters!");
    }
    if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") && !cpf.matches("\\d{11}")) {
      throw new InvalidInputException("CPF must be in the format XXX.XXX.XXX-XX or XXXXXXXXXXX!");
    }

    String digits = cpf.replaceAll("\\D", "");
    String checkDigits = digits.substring(digits.length() - 2);
    
    if(digits.equals("0000000000000") || digits.equals("11111111111") || 
    digits.equals("22222222222") || digits.equals("33333333333") || 
    digits.equals("44444444444") || digits.equals("55555555555") || 
    digits.equals("66666666666") || digits.equals("77777777777") || 
    digits.equals("88888888888") || digits.equals("99999999999")) {
      throw new InvalidInputException("Not a valid CPF!");
    }
    int sum = 0 ,weight = 10;

    for (int i = 0; i < 9; i++) {
        sum += Integer.parseInt(digits.charAt(i) + "") * weight;
        weight--;
    }
    int remainder = 11 - (sum % 11);
    if (remainder >= 10) remainder = 0;
    if (remainder != Integer.parseInt(checkDigits.charAt(0) + "")) {
        throw new InvalidInputException("Not a valid CPF!");
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
        throw new InvalidInputException("Not a valid CPF!");
    }
  }

  public static void validateEmail(String email) throws UniEventsException {
    if(email == null || email.isBlank()) {
      throw new CanNotBeEmptyException("Email");
    }

    if(!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
      throw new InvalidInputException("Not a valid email!");
    }

  }

  public static void validatePhone(String phone) throws UniEventsException {
    if(phone == null || phone.isBlank()) {
      throw new CanNotBeEmptyException("Phone");
    }

    if(phone.matches(".*[a-zA-Z].*")) {
      throw new InvalidInputException("Phone cannot have letters!");
    }
    if(!phone.matches("\\d{2} \\d{4,5}-\\d{4}") &&
      !phone.matches("\\d{2} \\d{8,9}") &&
       !phone.matches("\\d{10,11}")) {
      throw new InvalidInputException("Not a valid phone number!");
    }
  }

  public static void validateDate(String date) throws UniEventsException {
    if(date == null || date.isBlank()) {
      throw new CanNotBeEmptyException("Date");
    }

    if(date.matches(".*[a-zA-Z].*")) {
      throw new InvalidInputException("Date cannot have letters!");
    }
    if(
      !date.matches("\\d{2}/\\d{2}/\\d{4}") && 
      !date.matches("\\d{8}")) {
      throw new InvalidInputException("Not a valid date!");
    }

    if(date.matches("\\d{2}/\\d{2}/\\d{4}")) {
      try {
        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
      } catch (Exception e) {
        throw new InvalidInputException("Not a valid date!");
      }
    }else{
      try {
        LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(ResolverStyle.STRICT));
      } catch (Exception e) {
        throw new InvalidInputException("Not a valid date!");
      }
    }

  }

  public static void isInteger(String value) throws UniEventsException {
    if (value == null || value.isBlank()) {
      throw new CanNotBeEmptyException("Input");
    }
    try {
      Integer.valueOf(value);
    } catch (NumberFormatException e) {
      throw new NotANumberException("Invalid input, not an integer!");
    }
  }

  public static void isDouble(String value) throws UniEventsException {
    if (value == null || value.isBlank()) {
      throw new CanNotBeEmptyException("Input");
    }
    try {
      Double.valueOf(value);
    } catch (NumberFormatException e) {
      throw new NotANumberException("Invalid input, not a double!");
    }
  }

  public static void validateId(String id) throws UniEventsException {
    if (id == null || id.isBlank()) {
      throw new CanNotBeEmptyException("ID");
    }
    if (id.matches(".*[a-zA-Z].*")) {
      throw new InvalidInputException("ID cannot have letters!");
    }
    if (!id.matches("\\d{11}")) {
      throw new InvalidInputException("ID must be 11 digits long!");
    }
  }
}
