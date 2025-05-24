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
    return name;
  }
  
  protected static String getCpf(ParticipantManager participantManager){
    String cpf;
    while (true) { 
      System.out.print("Enter participant CPF (XXX.XXX.XXX-XX or XXXXXXXXXXX) (\"cancel\" to exit)>> ");
      cpf = Utils.scanner.nextLine();
      if (cpf.equalsIgnoreCase("cancel")) return "cancel";
      try {
        Validation.validateCpf(cpf);
        cpf = Utils.formatCpf(cpf);
        if(participantManager.isCpfAlreadyInUse(cpf)) throw new Exception("CPF already in use! Please try again.");
        break;
      } catch (Exception e) {
        System.out.println(Lines.clear());
        System.out.println(Lines.errorLine(e.getMessage()));
      }
    }
    return cpf;
  }

  protected static String getEmail(){
    System.out.print("Enter participant email (\"cancel\" to exit)>> ");
    String email = Utils.scanner.nextLine();

    return email;
  }
  protected static String getPhone(){
    System.out.print("Enter participant phone (\"cancel\" to exit)>> ");
    String phone = Utils.scanner.nextLine();

    return phone;
  }
  protected static String getBirthDate(){
    System.out.print("Enter participant birth date (dd/MM/yyyy) (\"cancel\" to exit)>> ");
    String birthDateString = Utils.scanner.nextLine();

    return birthDateString;
  }

}
