package br.edu.ifba.inf0008.uniEvents.model.participants.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum Course {
  ADMINISTRATION("Administration"),
  ARCHITECTURE("Architecture"),
  ARTS("Arts"),
  BIOLOGY("Biology"),
  CHEMISTRY("Chemistry"),
  COMPUTER_SCIENCE("Computer Science"),
  COMPUTER_ENGINEERING("Computer Engineering"),
  EDUCATION("Education"),
  ENGINEERING("Engineering"),
  INFORMATION_SYSTEMS("Information Systems"),
  INFORMATION_TECHNOLOGY("Information Technology"),
  LAW("Law"),
  MATH("Math"),
  MEDICINE("Medicine"),
  MUSIC("Music"),
  PHYSICS("Physics"),
  PSYCHOLOGY("Psychology");

  private final String description;

  Course(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static Course fromDescription(String desc) {
    for (Course course : Course.values()) {
      if (course.getDescription().equalsIgnoreCase(desc)) {
        return course;
      }
    }
    throw new IllegalArgumentException("No enum constant with description: " + desc);
  }

  public static ArrayList<Course> getAll(){
      return new ArrayList<>(Arrays.asList(Course.values()));
  }
}
