package br.edu.ifba.inf0008.uniEvents.forms;

import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public class ParticipantForms {
  public static String getName(String type){
    String name;
    while(true) {
      System.out.print("Enter " + type.toLowerCase() + " name (\"cancel\" to exit)>> ");
      name = Utils.scanner.nextLine();
      if (!name.isEmpty() || !name.isBlank()) break;
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Name cannot be empty!"));
    }
    System.out.println(Lines.clear());
    return name;
  }
  
  public static String getCpf() {
    String cpf;
    while (true) { 
      System.out.print("Enter CPF (XXX.XXX.XXX-XX or XXXXXXXXXXX) (\"cancel\" to exit)>> ");
      cpf = Utils.scanner.nextLine();
      if (cpf.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validateCpf(cpf);
        cpf = Utils.formatCpf(cpf);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return cpf;
  }

  public static String getEmail(){
    String email;
    while (true) { 
      System.out.print("Enter email (\"cancel\" to exit)>> ");
      email = Utils.scanner.nextLine();
      if(email.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validateEmail(email);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return email;
  }
  public static String getPhone(){
    String phone;
    while (true) { 
      System.out.print("Enter phone (\"cancel\" to exit)>> ");
      phone = Utils.scanner.nextLine();
      if(phone.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validatePhone(phone);
        phone = Utils.formatPhone(phone);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }

    System.out.println(Lines.clear());
    return phone;
  }

  public static int getSemester(){
    String semester;
    while (true) { 
      System.out.print("Enter semester (\"cancel\" to exit)>> ");
      semester = Utils.scanner.nextLine();
      if(semester.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return 0;
      }
      try {
        Validation.isInteger(semester);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return Integer.parseInt(semester);
  }

  public static double getGpa(){
    String gpa;
    while (true) { 
      System.out.print("Enter GPA (\"cancel\" to exit)>> ");
      gpa = Utils.scanner.nextLine();
      if(gpa.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return 0;
      }
      try {
        Validation.isDouble(gpa);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return Double.parseDouble(gpa);
  }

  public static String getId(String text) {
    String id;
    while (true) { 
      System.out.print("Enter " + text.toLowerCase() + "(11 digits) (\"cancel\" to exit)>> ");
      id = Utils.scanner.nextLine();
      if(id.equalsIgnoreCase("cancel")) {
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validateId(id);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    System.out.println(Lines.clear());
    return id;
  }
}