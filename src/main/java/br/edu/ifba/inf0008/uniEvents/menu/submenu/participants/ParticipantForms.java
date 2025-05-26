package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.BaseMenu;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public class ParticipantForms {
  protected static String getName(String type){
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
  
  protected static String getCpf(ParticipantManager participantManager) {
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

  protected static String getEmail(){
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
  protected static String getPhone(){
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
  protected static String getDate(String type) {
    String dateString;
    while(true){
      System.out.print("Enter " + type.toLowerCase() + " (dd/MM/yyyy or ddMMyyyy) (\"cancel\" to exit)>> ");
      dateString = Utils.scanner.nextLine();
      if(dateString.equalsIgnoreCase("cancel")){
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validateDate(dateString);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }

    System.out.println(Lines.clear());
    return dateString;
  }

  protected static String getOption(ArrayList<String> options, String title) {
    BaseMenu baseMenu;
    baseMenu = new BaseMenu("Select " + title, options);
    int response = baseMenu.getResponse();
    if (response == 0) return "cancel";
    return options.get(response);
  }

  protected static int getSemester(){
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

  protected static double getGpa(){
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

  protected static String getId(String text) {
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
