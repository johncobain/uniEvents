package br.edu.ifba.inf0008.uniEvents.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class Utils {
    public static final Scanner scanner = new Scanner(System.in);

    public static LocalDate stringToDate(String dateString) {
        // (dd/MM/yyyy)
        String[] parts = dateString.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return LocalDate.of(year, month, day);
    }
}
