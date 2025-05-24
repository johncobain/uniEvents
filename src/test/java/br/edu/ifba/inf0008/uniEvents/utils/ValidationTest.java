package br.edu.ifba.inf0008.uniEvents.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ValidationTest {
  @Test
  void testIsInteger() {
    Exception exception;
    // Test cases for isInteger
    assertDoesNotThrow(() -> Validation.isInteger("123"));
    assertDoesNotThrow(() -> Validation.isInteger("0"));
    assertDoesNotThrow(() -> Validation.isInteger("-123"));
    assertDoesNotThrow(() -> Validation.isInteger("2147483647"));
    assertDoesNotThrow(() -> Validation.isInteger("-2147483648"));
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.isInteger("notAnInteger");
    });
    assertEquals("Invalid input, not an integer!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.isInteger("123.45");
    });
    assertEquals("Invalid input, not an integer!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.isInteger("123abc");
    });
    assertEquals("Invalid input, not an integer!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.isInteger("");
    });
    assertEquals("Input cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.isInteger(" ");
    });
    assertEquals("Input cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.isInteger(null);
    });
    assertEquals("Input cannot be empty!", exception.getMessage());
  }

  @Test
  void testValidateCpf() {
    Exception exception;
    assertDoesNotThrow(() -> Validation.validateCpf("332.528.680-16"));
    assertDoesNotThrow(() -> Validation.validateCpf("397.441.930-69"));
    assertDoesNotThrow(() -> Validation.validateCpf("747.856.730-44"));
    assertDoesNotThrow(() -> Validation.validateCpf("33252868016"));

    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf("111.111.111-11");
    });
    assertEquals("Not a valid CPF!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf("222.222.222-22");
    });
    assertEquals("Not a valid CPF!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf("123.456.789-00");
    });
    assertEquals("Not a valid CPF!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf("332.528.680-1A");
    });
    assertEquals("CPF cannot have letters!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf("332.528.680-1");
    });
    assertEquals("CPF must be in the format XXX.XXX.XXX-XX or XXXXXXXXXXX!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf("332.528.680-161");
    });
    assertEquals("CPF must be in the format XXX.XXX.XXX-XX or XXXXXXXXXXX!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf("");
    });
    assertEquals("CPF cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf(" ");
    });
    assertEquals("CPF cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateCpf(null);
    });
    assertEquals("CPF cannot be empty!", exception.getMessage());
  }

  @Test
  void testValidateDate() {

  }

  @Test
  void testValidateEmail() {
    Exception exception;
    assertDoesNotThrow(() -> Validation.validateEmail("valid@example.com"));
    assertDoesNotThrow(() -> Validation.validateEmail("valid@example.com.br"));
    assertDoesNotThrow(() -> Validation.validateEmail("valid@example.edu.info.br"));

    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateEmail("notValid@example");
    });
    assertEquals("Not a valid email!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateEmail("notValid@example.");
    });
    assertEquals("Not a valid email!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateEmail("notValid@example.com.");
    });
    assertEquals("Not a valid email!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateEmail("notValid@example.com.toManyLetters");
    });
    assertEquals("Not a valid email!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateEmail("");
    });
    assertEquals("Email cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateEmail(" ");
    });
    assertEquals("Email cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateEmail(null);
    });
    assertEquals("Email cannot be empty!", exception.getMessage());
  }

  @Test
  void testValidatePhone() {

  }
}
