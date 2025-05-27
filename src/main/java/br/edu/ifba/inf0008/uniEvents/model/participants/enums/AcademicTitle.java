package br.edu.ifba.inf0008.uniEvents.model.participants.enums;

import java.util.ArrayList;

public enum AcademicTitle {
  BACHELOR("Bachelor's Degree"),
  MSC("Master of Science."),
  MA("Master of Arts"),
  MBA("MBA"),
  PHD ("Ph.D."),
  EDUDOC ("Doctor of Education"),
  MEDDOC ("Doctor of Medicine"),
  JURDOC ("Juris Doctor"),
  SPECIALIST ("Specialist"),
  PROFESSOR ("Professor"),
  ASSOCIATE_PROFESSOR ("Associate Professor"),
  ASSISTANT_PROFESSOR ("Assistant Professor"),
  LECTURER ("Lecturer"),
  POSTDOC ("Postdoctoral Fellow"),
  NONE ("N/A");
  

  private final String description;

  AcademicTitle(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static AcademicTitle fromDescription(String desc) {
    for (AcademicTitle title : AcademicTitle.values()) {
      if (title.getDescription().equalsIgnoreCase(desc)) {
        return title;
      }
    }
    throw new IllegalArgumentException("No enum constant with description: " + desc);
  }
  
  public static ArrayList<AcademicTitle> getAll() {
      return new ArrayList<>(java.util.Arrays.asList(AcademicTitle.values()));
  }
}
