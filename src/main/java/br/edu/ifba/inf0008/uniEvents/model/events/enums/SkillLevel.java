package br.edu.ifba.inf0008.uniEvents.model.events.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum SkillLevel {
  BEGINNER("Beginner"){
    @Override
    public double minimumGpa() {
      return 0;
    }
  },
  INTERMEDIATE("Intermediate"){
    @Override
    public double minimumGpa() {
      return 5;
    }
  },
  ADVANCED("Advanced"){
    @Override
    public double minimumGpa() {
      return 8;
    }
  };

  private final String description;

  SkillLevel(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static SkillLevel fromDescription(String desc) {
    for (SkillLevel level : SkillLevel.values()) {
      if (level.getDescription().equalsIgnoreCase(desc)) {
        return level;
      }
    }
    throw new IllegalArgumentException("No enum constant with description: " + desc);
  }

  public abstract double minimumGpa();

  public static ArrayList<SkillLevel> getAll() {
    return new ArrayList<>(Arrays.asList(SkillLevel.values()));
  }
}
