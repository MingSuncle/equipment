package com.example.equipmentmanagementspring.service;


import com.example.equipmentmanagementspring.form.ConfigResponse;

public interface ResponseConfigService {

  ConfigResponse configResponse(String centerId, String boxNo);

  ConfigResponse ConfigRequestResponseBody2(String centerId);

  void initialEdgeConfig(String json);


}
