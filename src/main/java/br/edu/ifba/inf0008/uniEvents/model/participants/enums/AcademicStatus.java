package br.edu.ifba.inf0008.uniEvents.model.participants.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum AcademicStatus {
  ACTIVE("Active"), 
  ON_LEAVE("On Leave"), 
  GRADUATED("Graduated"),
  DROPPED_OUT("Dropped Out");

  private final String description;

  AcademicStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static AcademicStatus fromDescription(String desc) {
    for (AcademicStatus status : AcademicStatus.values()) {
      if (status.getDescription().equalsIgnoreCase(desc)) {
        return status;
      }
    }
    throw new IllegalArgumentException("No enum constant with description: " + desc);
  }

  public static ArrayList<AcademicStatus> getAll(){
      return new ArrayList<>(Arrays.asList(AcademicStatus.values()));
  }
}
