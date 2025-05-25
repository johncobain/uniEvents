package br.edu.ifba.inf0008.uniEvents.utils;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class UtilsTest {
  @Test
  void testFormatCpf() {
    assert(Utils.formatCpf("12345678910").equals("123.456.789-10"));
    assert(Utils.formatCpf("123.456.789-10").equals("123.456.789-10"));
  }

  @Test
  void testFormatPhone() {
    assert(Utils.formatPhone("1234567891").equals("12 3456-7891"));
    assert(Utils.formatPhone("12345678910").equals("12 34567-8910"));
    assert(Utils.formatPhone("12 34567891").equals("12 3456-7891"));
    assert(Utils.formatPhone("12 345678910").equals("12 34567-8910"));
    assert(Utils.formatPhone("12 3456-7891").equals("12 3456-7891"));
    assert(Utils.formatPhone("12 34567-8910").equals("12 34567-8910"));

  }

  @Test
  void testStringToDate() {
    LocalDate date;
    date = Utils.stringToDate("01022003");
    assert(date.toString().equals("2003-02-01"));
    date = Utils.stringToDate("01/02/2003");
    assert(date.toString().equals("2003-02-01"));
  }
}
