package com.example.equipmentmanagementspring.exception;

public class AuthorizationNotFoundException extends RuntimeException{

  private static final String msg = "authorization not found";

  public AuthorizationNotFoundException() {
    super(msg);
  }
}
