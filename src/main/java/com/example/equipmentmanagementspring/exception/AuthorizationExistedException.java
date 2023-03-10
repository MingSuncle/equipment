package com.example.equipmentmanagementspring.exception;

public class AuthorizationExistedException extends RuntimeException{

  private static final String msg = "authorization already existed";

  public AuthorizationExistedException() {
    super(msg);
  }
}
