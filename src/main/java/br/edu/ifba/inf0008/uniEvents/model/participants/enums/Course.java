package br.edu.ifba.inf0008.uniEvents.model.participants.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum Course {
  COMPUTER_SCIENCE("Computer Science"),
  COMPUTER_ENGINEERING("Computer Engineering"),
  INFORMATION_SYSTEMS("Information Systems"),
  INFORMATION_TECHNOLOGY("Information Technology"),
  MATH("Math"),
  PHYSICS("Physics"),
  CHEMISTRY("Chemistry"),
  BIOLOGY("Biology"),
  ENGINEERING("Engineering"),
  ARCHITECTURE("Architecture"),
  MEDICINE("Medicine"),
  PSYCHOLOGY("Psychology"),
  ARTS("Arts"),
  EDUCATION("Education"),
  LAW("Law"),
  MUSIC("Music"),
  ADMINISTRATION("Administration");

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
    ArrayList<Course> courses = new ArrayList<>();
    courses.addAll(Arrays.asList(Course.values()));
    return courses;
  }
}
