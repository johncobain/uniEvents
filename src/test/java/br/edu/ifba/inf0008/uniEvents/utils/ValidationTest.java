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
    Exception exception;
    assertDoesNotThrow(() -> Validation.validateDate("20/08/2005"));
    assertDoesNotThrow(() -> Validation.validateDate("28/02/2005"));
    assertDoesNotThrow(() -> Validation.validateDate("03052008"));

    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("2/3/2005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("20/3/2005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("2/12/2005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("32/08/2005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("2/13/2005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("29/02/2005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("2/13/2005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("2182005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("32082005");
    });
    assertEquals("Not a valid date!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("30/ab/2005");
    });
    assertEquals("Date cannot have letters!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("30ab2005");
    });
    assertEquals("Date cannot have letters!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate("");
    });
    assertEquals("Date cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate(" ");
    });
    assertEquals("Date cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validateDate(null);
    });
    assertEquals("Date cannot be empty!", exception.getMessage());
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
    Exception exception;
    assertDoesNotThrow(() -> Validation.validatePhone("75988888888"));
    assertDoesNotThrow(() -> Validation.validatePhone("7588888888"));
    assertDoesNotThrow(() -> Validation.validatePhone("75 988888888"));
    assertDoesNotThrow(() -> Validation.validatePhone("75 88888888"));
    assertDoesNotThrow(() -> Validation.validatePhone("75 98888-8888"));
    assertDoesNotThrow(() -> Validation.validatePhone("75 8888-8888"));

    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone("75 9888888888");
    });
    assertEquals("Not a valid phone number!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone("75 98888-888");
    });
    assertEquals("Not a valid phone number!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone("75 98888-88888");
    });
    assertEquals("Not a valid phone number!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone("aa 98888-88888");
    });
    assertEquals("Phone cannot have letters!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone("aabbbbbcccc");
    });
    assertEquals("Phone cannot have letters!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone("");
    });
    assertEquals("Phone cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone(" ");
    });
    assertEquals("Phone cannot be empty!", exception.getMessage());
    exception = assertThrows(IllegalArgumentException.class, () -> {
      Validation.validatePhone(null);
    });
    assertEquals("Phone cannot be empty!", exception.getMessage());
    
  }
}
