package com.example.equipmentmanagementspring.controller;


import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ProjectInformation")
public class ProjectInformationController {
  @Autowired
  private com.example.equipmentmanagementspring.service.ProjectInformationService ProjectInformationService;


  @RequestMapping("/getAIInformationByusrId")
  public R queryAIInformationList(@RequestParam(value = "usr_id") String usrId) {
    R r = R.ok();
    List<Project> list = ProjectInformationService.queryProjectInformationByUserId(usrId);
    r.addData("projectInformationList", list);
    return r;
  }

}

