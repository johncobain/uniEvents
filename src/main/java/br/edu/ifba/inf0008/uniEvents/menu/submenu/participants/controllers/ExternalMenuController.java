package br.edu.ifba.inf0008.uniEvents.menu.submenu.participants.controllers;

import br.edu.ifba.inf0008.uniEvents.model.participants.External;
import br.edu.ifba.inf0008.uniEvents.services.ParticipantManager;
import br.edu.ifba.inf0008.uniEvents.utils.Utils;

public class ExternalMenuController {

  public Boolean create(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    participantManager.createExternal(name, cpf, email, phone, Utils.stringToDate(birthDateString));
    return true;
  }

  public External update(ParticipantManager participantManager, String name, String cpf, String email, String phone, String birthDateString) {
    return new External(name, cpf, email, phone, Utils.stringToDate(birthDateString));
  }
  
}
