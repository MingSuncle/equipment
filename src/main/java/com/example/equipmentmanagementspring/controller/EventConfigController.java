package com.example.equipmentmanagementspring.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.example.equipmentmanagementspring.entity.EventConfigEntity;
import com.example.equipmentmanagementspring.service.CenterConfigService;
import com.example.equipmentmanagementspring.service.EventConfigService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMPP
@RequestMapping("/aiEventConfig")
public class EventConfigController {
  private final EventConfigService EventConfigService;

  public EventConfigController(com.example.equipmentmanagementspring.service.EventConfigService EventConfigService) {
    this.EventConfigService = EventConfigService;
  }


  @PostMapping("/deleteEventConfig")

  public R deleteEventConfig(@RequestParam(value = "Event_id") String EventId,
                             @RequestParam(value = "state") Integer state,
                             @RequestParam(value = "ipc_id") String ipcId) {
    R r = R.ok();
    EventConfigEntity ece = new EventConfigEntity();
    ece.setState(state);
    ece.setAiEventId(EventId);
    ece.setIpcId(ipcId);
    EventConfigService.deleteByMultiId(ece);
    return r;
  }


  @PostMapping("/addEventConfig")

  public R addEventConfig(@RequestBody EventConfigEntity ece) {
    R r = R.ok();
    ece.setState(0);
    EventConfigService.saveOrUpdateByMultiId(ece);
    return r;
  }


  @PostMapping("/updateEventConfig")
  public R updateIpcConfig(@RequestBody EventConfigEntity ece) {
    R r = R.ok();
    ece.setState(0);
    try{
      EventConfigEntity temp = EventConfigService.selectByMultiId(ece);
      if(temp.getState()==0) {
        EventConfigService.saveOrUpdateByMultiId(ece);
      }
    }catch (NullPointerException e){
      ece.setState(1);
      EventConfigService.saveOrUpdateByMultiId(ece);
    }
    return r;
  }


  @GetMapping("/getEventConfigNum")

  public R getEventConfigNum(@RequestParam(value = "ipc_id") String ipcId) {
    R r = R.ok();
    Integer Num = EventConfigService.getEventConfigListNum(ipcId);
    r.addData("total", Num);
    return r;
  }


  @GetMapping("/getEventConfigList")

  public R getEventConfigList(@RequestParam(value = "ipc_id") String ipcId,
                              @RequestParam(value = "current_page") Integer currentPage,
                              @RequestParam(value = "pageSize") Integer pageSize

  ) {
    R r = R.ok();
    List<EventConfigEntity> list = EventConfigService.getEventConfigList(ipcId, (currentPage - 1) * pageSize, pageSize);
    Integer Num = EventConfigService.getEventConfigListNum(ipcId);
    r.addData("result", list);
    r.addData("Num",Num);
    return r;
  }


  @GetMapping("/getEventConfig")

  public R getEventConfig(@RequestParam(value = "Event_id") String EventId,
                          @RequestParam(value = "Ipc_id") String ipcId) {
    R r = R.ok();

    EventConfigEntity result = EventConfigService.getEventConfig(EventId, ipcId, 1);
    try {
      if (result.getAiEventId().equals(null)) {
      }
    } catch (NullPointerException e) {
      try {
        result = EventConfigService.getEventConfig(EventId, ipcId, 2);
        if (result.getAiEventId().equals(null)) {
        }
      } catch (NullPointerException E) {
        result = EventConfigService.getEventConfig(EventId, ipcId, 0);
      }

    }
    r.addData("result", result);
    return r;
  }
}
