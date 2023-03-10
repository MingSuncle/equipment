package com.example.equipmentmanagementspring.exception;

public class ProjectNotFoundException extends RuntimeException{

  private static final String msg = "project not found";

  public ProjectNotFoundException() {
    super(msg);
  }
}
