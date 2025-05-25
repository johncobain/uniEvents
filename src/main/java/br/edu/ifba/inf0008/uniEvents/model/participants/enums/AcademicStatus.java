package br.edu.ifba.inf0008.uniEvents.model.participants.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum AcademicStatus {
  ACTIVE("Active"), 
  ON_LEAVE("On Leave"), 
  GRADUATED("Graduated"),
  DROPED_OUT("Dropped Out");

  private final String description;

  AcademicStatus(String description) {
    this.description = description;
  }

  public ArrayList<AcademicStatus> getAll(){
    ArrayList<AcademicStatus> academicStatuses = new ArrayList<>();
    academicStatuses.addAll(Arrays.asList(AcademicStatus.values()));
    return academicStatuses;
  }

  @Override
  public String toString() {
    return description;
  }
}
