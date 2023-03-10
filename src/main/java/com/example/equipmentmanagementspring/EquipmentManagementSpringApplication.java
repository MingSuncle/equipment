package com.example.equipmentmanagementspring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.equipmentmanagementspring.mapper")
@MapperScan("com.example.equipmentmanagementspring.box.dao")
public class EquipmentManagementSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(EquipmentManagementSpringApplication.class, args);
  }

}
