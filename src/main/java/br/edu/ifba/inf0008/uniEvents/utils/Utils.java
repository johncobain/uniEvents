package br.edu.ifba.inf0008.uniEvents.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class Utils {
    public static final Scanner scanner = new Scanner(System.in);

    public static LocalDate stringToDate(String dateString) {
        if(dateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
            // (dd/MM/yyyy)
            String[] parts = dateString.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return LocalDate.of(year, month, day);
        }else{
            // (ddMMyyyy)
            int day = Integer.parseInt(dateString.substring(0, 2));
            int month = Integer.parseInt(dateString.substring(2, 4));
            int year = Integer.parseInt(dateString.substring(4));
            return LocalDate.of(year, month, day);
        }
    }

    public static String formatCpf(String cpf) {
        if(cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }

    public static String formatPhone(String phone) {
        if(phone.matches("\\d{2}\\ \\d{4,5}-\\d{4}")) {
            return phone;
        }
        if(phone.matches("\\d{2} \\d{8,9}")){
            if(phone.length() == 11) {
                return phone.substring(0, 2) + " " + phone.substring(3, 7) + "-" + phone.substring(7);
            }
            return phone.substring(0, 2) + " " + phone.substring(3, 8) + "-" + phone.substring(8);
        }

        if(phone.length() == 10) {
            return phone.substring(0, 2) + " " + phone.substring(2, 6) + "-" + phone.substring(6);
        }
        return phone.substring(0, 2) + " " + phone.substring(2, 7) + "-" + phone.substring(7);
    }
}
