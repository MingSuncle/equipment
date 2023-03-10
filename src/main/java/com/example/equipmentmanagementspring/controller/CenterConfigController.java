package com.example.equipmentmanagementspring.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.example.equipmentmanagementspring.service.CenterConfigService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMPP
@RequestMapping("/centerConfig")
public class CenterConfigController {
  private final CenterConfigService CenterConfigService;

  public CenterConfigController(com.example.equipmentmanagementspring.service.CenterConfigService CenterConfigService) {
    this.CenterConfigService = CenterConfigService;
  }


  @PostMapping("/deleteCenterConfig")

  public R deleteCenterConfig(@RequestParam(value = "Center_id") String CenterId,
                              @RequestParam(value = "state") Integer state) {

    QueryWrapper<CenterConfigEntity> wrapper =  new QueryWrapper<CenterConfigEntity>();
    wrapper.eq("state",state).eq("center_id",CenterId);
    R r = R.ok();
    CenterConfigService.remove(wrapper);
    return r;
  }


  @PostMapping("/addCenterConfig")

  public R addCenterConfig(@RequestBody CenterConfigEntity cce) {
    R r = R.ok();
    cce.setState(0);
    CenterConfigService.saveOrUpdateByMultiId(cce);
    return r;
  }


  @PostMapping("/updateCenterConfig")

  public R updateIpcConfig(@RequestBody CenterConfigEntity cce) {
    R r = R.ok();
    cce.setState(0);
    try{
      CenterConfigEntity temp = CenterConfigService.selectByMultiId(cce);
      if(temp.getState()==0) {
      CenterConfigService.saveOrUpdateByMultiId(cce);
      }
      }catch (NullPointerException e){
      cce.setState(1);
      CenterConfigService.saveOrUpdateByMultiId(cce);
    }
    return r;
  }


  @GetMapping("/getCenterConfigNum")

  public R getCenterConfigNum(@RequestParam(value = "usr_id") String usrId) {
    R r = R.ok();
    Integer Num = CenterConfigService.getCenterConfigListNum(usrId);
    r.addData("total", Num);
    return r;
  }


  @GetMapping("/getCenterConfigList")

  public R getCenterConfigList(@RequestParam(value = "usr_id") String usrId,
                               @RequestParam(value = "current_page") Integer currentPage,
                               @RequestParam(value = "pageSize") Integer pageSize

  ) {
    R r = R.ok();
    List<CenterConfigEntity> list = CenterConfigService.getCenterConfigList(usrId, (currentPage - 1) * pageSize, pageSize);
    Integer Num = CenterConfigService.getCenterConfigListNum(usrId);
    r.addData("result", list);
    r.addData("Num",Num);
    return r;
  }

  @GetMapping("/getCenterConfig")

  public R getCenterConfig(@RequestParam(value = "Center_id") String CenterId) {
    R r = R.ok();

    CenterConfigEntity result = CenterConfigService.getCenterConfig(CenterId, 1);
    try {
      if (result.getCenterId().equals(null)) {
      }
    } catch (NullPointerException e) {
      try {
        result = CenterConfigService.getCenterConfig(CenterId, 2);
        if (result.getCenterId().equals(null)) {
        }
      } catch (NullPointerException E) {
        result = CenterConfigService.getCenterConfig(CenterId, 0);
      }

    }
    r.addData("result", result);
    return r;
  }
}
