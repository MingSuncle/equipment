package com.example.equipmentmanagementspring.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.HeartDetectEntity;
import com.example.equipmentmanagementspring.service.BoxConfigService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@EnableMPP
@RequestMapping("/boxConfig")
public class BoxConfigController {
  private final BoxConfigService boxConfigService;

  public BoxConfigController(BoxConfigService boxConfigService) {
    this.boxConfigService = boxConfigService;
  }


  @PostMapping("/deleteBoxConfig")
  public R deleteBoxConfig(@RequestParam(value = "box_no") String boxNo,
                           @RequestParam(value = "state") Integer state) {

    QueryWrapper<BoxConfigEntity> wrapper =  new QueryWrapper<BoxConfigEntity>();
    wrapper.eq("state",state).eq("box_no",boxNo);
    R r = R.ok();
    boxConfigService.remove(wrapper);
    return r;
  }

  @PostMapping("/addBoxConfig")
  public R addBoxConfig(@RequestBody BoxConfigEntity bce) {
    R r = R.ok();
    bce.setState(0);
    boxConfigService.saveOrUpdateByMultiId(bce);
    return r;
  }

  @PostMapping("/updateBoxConfig")
  public R updateIpcConfig(@RequestBody BoxConfigEntity bce) {
    R r = R.ok();
    bce.setState(0);
    try{
      BoxConfigEntity temp = boxConfigService.selectByMultiId(bce);
      if(temp.getState()==0){
      boxConfigService.saveOrUpdateByMultiId(bce);}
    }catch (NullPointerException e){
      bce.setState(1);
      boxConfigService.saveOrUpdateByMultiId(bce);
    }
    return r;
  }

  @GetMapping("/getBoxConfigNum")
  public R getBoxConfigNum(@RequestParam(value = "center_id") String centerId) {
    R r = R.ok();
    Integer Num = boxConfigService.getBoxConfigListNum(centerId);
    r.addData("total", Num);
    return r;
  }

  @GetMapping("/getBoxConfigList")
  public R getBoxConfigList(@RequestParam(value = "center_id") String centerId,
                            @RequestParam(value = "current_page") Integer currentPage,
                            @RequestParam(value = "pageSize") Integer pageSize

  ) {
    R r = R.ok();
    List<BoxConfigEntity> list = boxConfigService.getBoxConfigList(centerId, (currentPage - 1) * pageSize, pageSize);
    Integer Num = boxConfigService.getBoxConfigListNum(centerId);
    r.addData("result", list);
    r.addData("Num",Num);
    return r;

  }

  @GetMapping("/getBoxConfigListByName")
  public R getBoxConfigListByName(
                            @RequestParam(value = "box_name") String boxName,
                            @RequestParam(value = "center_id") String centerId,
                            @RequestParam(value = "current_page") Integer currentPage,
                            @RequestParam(value = "pageSize") Integer pageSize

  ) {
    R r = R.ok();
    List<BoxConfigEntity> list = boxConfigService.getBoxConfigListByName(boxName,centerId, (currentPage - 1) * pageSize, pageSize);
    Integer Num = boxConfigService.getBoxConfigListNumByName(boxName,centerId);
    r.addData("result", list);
    r.addData("Num", Num);
    return r;
  }

  @GetMapping("/getBoxConfig")
  public R getBoxConfig(@RequestParam(value = "box_id") String boxId) {
    R r = R.ok();

    BoxConfigEntity result = boxConfigService.getBoxConfig(boxId, 1);
    try {
      if (result.getBoxNo().equals(null)) {
      }
    } catch (NullPointerException e) {
      try {
        result = boxConfigService.getBoxConfig(boxId, 2);
        if (result.getBoxNo().equals(null)) {
        }
      } catch (NullPointerException E) {
        result = boxConfigService.getBoxConfig(boxId, 0);
      }

    }
    r.addData("result", result);
    return r;
  }

  @GetMapping("/getAll")
  public R getAll(){
    R r = R.ok();
    List<BoxConfigEntity> result = boxConfigService.getAll();
    Integer total = boxConfigService.allNum();
    r.addData("result",result);
    r.addData("total",total);
    return r;
  }
}
