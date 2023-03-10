package com.example.equipmentmanagementspring.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.example.equipmentmanagementspring.entity.IpcConfigEntity;
import com.example.equipmentmanagementspring.service.CenterConfigService;
import com.example.equipmentmanagementspring.service.IpcConfigService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMPP
@RequestMapping("/ipcConfig")
public class IpcConfigController {
  private final IpcConfigService IpcConfigService;

  public IpcConfigController(IpcConfigService IpcConfigService) {
    this.IpcConfigService = IpcConfigService;
  }


  @PostMapping("/deleteIpcConfig")

  public R deleteIpcConfig(@RequestParam(value = "Ipc_id") String IpcId,
                           @RequestParam(value = "state") Integer state) {
    R r = R.ok();

    QueryWrapper<IpcConfigEntity> wrapper =  new QueryWrapper<IpcConfigEntity>();
    wrapper.eq("state",state).eq("ipc_id",IpcId);
    IpcConfigService.remove(wrapper);
    return r;
  }


  @PostMapping("/addIpcConfig")

  public R addIpcConfig(@RequestBody IpcConfigEntity ice) {
    R r = R.ok();
    ice.setState(0);
    IpcConfigService.saveOrUpdateByMultiId(ice);
    return r;
  }


  @PostMapping("/updateIpcConfig")

  public R updateIpcConfig(@RequestBody IpcConfigEntity ice) {
    R r = R.ok();
    ice.setState(0);
    try{
      IpcConfigEntity temp = IpcConfigService.selectByMultiId(ice);
      if(temp.getState()==0) {
        IpcConfigService.saveOrUpdateByMultiId(ice);
      }
    }catch (NullPointerException e){
      ice.setState(1);
      IpcConfigService.saveOrUpdateByMultiId(ice);
    }
    return r;
  }


  @GetMapping("/getIpcConfigNum")

  public R getIpcConfigNum(@RequestParam(value = "ipc_boxNo") String ipcBoxNo) {
    R r = R.ok();
    Integer Num = IpcConfigService.getIpcConfigListNum(ipcBoxNo);
    r.addData("total", Num);
    return r;
  }


  @GetMapping("/getIpcConfigList")

  public R getIpcConfigList(@RequestParam(value = "ipc_boxNo") String ipcBoxNo,
                            @RequestParam(value = "current_page") Integer currentPage,
                            @RequestParam(value = "pageSize") Integer pageSize

  ) {
    R r = R.ok();
    List<IpcConfigEntity> list = IpcConfigService.getIpcConfigList(ipcBoxNo, (currentPage - 1) * pageSize, pageSize);
    r.addData("result", list);
    return r;
  }


  @GetMapping("/getIpcConfigListByName")

  public R getIpcConfigListByName(
                            @RequestParam(value = "ipc_name") String ipcName,
                            @RequestParam(value = "ipc_boxNo") String ipcBoxNo,
                            @RequestParam(value = "current_page") Integer currentPage,
                            @RequestParam(value = "pageSize") Integer pageSize

  ) {
    R r = R.ok();
    List<IpcConfigEntity> list = IpcConfigService.getIpcConfigListByName(ipcName,ipcBoxNo, (currentPage - 1) * pageSize, pageSize);
    Integer Num = IpcConfigService.getIpcConfigListNumByName(ipcName,ipcBoxNo);
    r.addData("result", list);
    r.addData("Num",Num);
    return r;
  }


  @GetMapping("/getIpcConfig")

  public R getIpcConfig(@RequestParam(value = "Ipc_id") String IpcId) {
    R r = R.ok();

    IpcConfigEntity result = IpcConfigService.getIpcConfig(IpcId, 1);
    try {
      if (result.getIpcId().equals(null)) {
      }
    } catch (NullPointerException e) {
      try {
        result = IpcConfigService.getIpcConfig(IpcId, 2);
        if (result.getIpcId().equals(null)) {
        }
      } catch (NullPointerException E) {
        result = IpcConfigService.getIpcConfig(IpcId, 0);
      }

    }
    r.addData("result", result);
    return r;
  }
}
