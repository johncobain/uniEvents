package br.edu.ifba.inf0008.uniEvents.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ValidationTest {
  @Test
  void testIsInteger() {
    IllegalArgumentException exception;
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

  }

  @Test
  void testValidateDate() {

  }

  @Test
  void testValidateEmail() {

  }

  @Test
  void testValidatePhone() {

  }
}
