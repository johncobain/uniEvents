package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants;

import java.util.ArrayList;

import br.edu.ifba.inf0008.uniEvents.menu.submenu.BaseMenu;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Lines;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;
import br.edu.ifba.inf0008.uniEvents.utils.Validation;

public class ParticipantForms {
  
  protected static String getType(){
    BaseMenu baseMenu;
    ArrayList<String> participantTypes = new ArrayList<>();
    {
      participantTypes.add("Cancel");
      participantTypes.add("Student");
      participantTypes.add("Teacher");
      participantTypes.add("External");
    }
    baseMenu = new BaseMenu("Select participant type",  participantTypes);
    int response = baseMenu.getResponse();
    if (response == 0) return "cancel";
    return participantTypes.get(response);
  }

  protected static String getName(){
    String name;
    while(true) {
      System.out.print("Enter participant name (\"cancel\" to exit)>> ");
      name = Utils.scanner.nextLine();
      if (!name.isEmpty() || !name.isBlank()) break;
      System.out.println(Lines.clear());
      System.out.println(Lines.errorLine("Name cannot be empty!"));
    }
    System.out.println(Lines.clear());
    return name;
  }
  
  protected static String getCpf(ParticipantManager participantManager){
    String cpf;
    while (true) { 
      System.out.print("Enter participant CPF (XXX.XXX.XXX-XX or XXXXXXXXXXX) (\"cancel\" to exit)>> ");
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
      System.out.print("Enter participant email (\"cancel\" to exit)>> ");
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
    System.out.print("Enter participant phone (\"cancel\" to exit)>> ");
    String phone = Utils.scanner.nextLine();

    System.out.println(Lines.clear());
    return phone;
  }
  protected static String getBirthDate(){
    String birthDateString;
    while(true){
      System.out.print("Enter participant birth date (dd/MM/yyyy or ddMMyyyy) (\"cancel\" to exit)>> ");
      birthDateString = Utils.scanner.nextLine();
      if(birthDateString.equalsIgnoreCase("cancel")){
        System.out.println(Lines.clear());
        return "cancel";
      }
      try {
        Validation.validateDate(birthDateString);
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }

    System.out.println(Lines.clear());
    return birthDateString;
  }

}
