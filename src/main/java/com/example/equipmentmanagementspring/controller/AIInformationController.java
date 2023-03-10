package com.example.equipmentmanagementspring.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.equipmentmanagementspring.entity.AIInformationEntity;
import com.example.equipmentmanagementspring.form.ProcessInformationForm;
import com.example.equipmentmanagementspring.service.AIInformationService;
import com.example.equipmentmanagementspring.service.ProcessInformationService;
import com.example.equipmentmanagementspring.utils.R;
import com.example.equipmentmanagementspring.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * AI消息
 *
 * @author Zhao Zian
 */

@RestController
@RequestMapping("/AIInformation")
public class AIInformationController {
  private final ProcessInformationService processInformationService;
  private final AIInformationService AIInformationService;

  public AIInformationController(ProcessInformationService processInformationService,
                                 com.example.equipmentmanagementspring.service.AIInformationService AIInformationService) {
    this.processInformationService = processInformationService;
    this.AIInformationService = AIInformationService;
  }


  @GetMapping("/getAIInformation")

  public R queryAIInformationList() {
    R r = R.ok();
    List<AIInformationEntity> list = AIInformationService.queryAIInformationList();
    r.addData("allAssociationIdList", list);
    return r;
  }


  @GetMapping("/getAIInformationByproId")

  public R queryAIInformationListByproId(@RequestParam(value = "pro_id") String proId) {
    R r = R.ok();
    List<AIInformationEntity> list = AIInformationService.queryAIInformationListByProId(proId);
    r.addData("allAssociationIdList", list);
    return r;
  }


  @GetMapping("/getUnaccomplishedAIInformationByusrId")

  public R getUnaccomplishedAIInformationByusrId(@RequestParam(value = "usr_id") String usrId,
                                                 @RequestParam(value = "current_page", required = false) Integer currentPage,
                                                 @RequestParam(value = "page_size", required = false) Integer pageSize) {
    R r = R.ok();
    Integer n;
    Integer m;
    if (currentPage == null && pageSize == null) {
      n = m = null;
    } else {
      n = (currentPage - 1) * pageSize;
      m = pageSize;
    }
    List<AIInformationEntity> ll = new ArrayList<>();
    List<AIInformationEntity> list = AIInformationService.AIInformationByIdState(usrId, 3, n, m);
    for (Integer i = 0; i < list.size(); i++) {

      Long proId = new Long(list.get(i).getMessageId());
      ProcessInformationForm temp = processInformationService.lastProcessInformationByMessageId(proId);
      if (temp.getProcessorId().equals(usrId)) {
        AIInformationEntity New = list.get(i);
        ll.add(list.get(i));
      }

    }
    try {
      Integer Num = ll.size();
      r.addData("totalNum",Num);
      r.addData("UnaccomplishedAIInformation", ll);
    }
      catch(Exception e){
        r.addData("totalNum",0);
        r.addData("UnaccomplishedAIInformation", ll);
    }


    return r;
  }


  @GetMapping("/getAIInformationNumByusrIdAndstate")

  public R getAIInformationNumByusrIdAndstate(@RequestParam(value = "usr_id") String usrId,
                                              @RequestParam(value = "state") Integer state) {
    R r = R.ok();
    Long num = AIInformationService.AIInformationNumByIdState(usrId, state);
    r.addData("informationNumber", num);
    return r;
  }


  @GetMapping("/getAIInformationByusrIdAndstate")

  public R getAIInformationByusrIdAndstate(@RequestParam(value = "usr_id") String usrId,
                                           @RequestParam(value = "state") Integer state,
                                           @RequestParam(value = "current_page", required = false) Integer currentPage,
                                           @RequestParam(value = "page_size", required = false) Integer pageSize) {
    R r = R.ok();
    Integer n;
    Integer m;
    if (currentPage == null && pageSize == null) {
      n = m = null;
    } else {
      n = (currentPage - 1) * pageSize;
      m = pageSize;
    }
    List<AIInformationEntity> list = AIInformationService.AIInformationByIdState(usrId, state, n, m);
    Long num = AIInformationService.AIInformationNumByIdState(usrId,state);
    r.addData("totalNum",num);
    r.addData("information", list);
    return r;
  }


  @GetMapping("/getAIInformationByproIdState")

  public R queryAIInformationListByproIdState(
                                              @RequestParam(value = "usr_id",required = false) String usrId,
                                              @RequestParam(value = "pro_id", required = false) LinkedList<String> proId,
                                              @RequestParam(value = "state", required = false) Integer state,
                                              @RequestParam(value = "begin_time", required = false) String beginTime,
                                              @RequestParam(value = "end_time", required = false) String endTime,
                                              @RequestParam(value = "event_type", required = false) LinkedList<String> type,
                                              @RequestParam(value = "current_page", required = false) Integer currentPage,
                                              @RequestParam(value = "page_size", required = false) Integer pageSize) {
    R r = R.ok();
    if(proId == null){
      return R.error();
    }
    while (proId != null && !proId.isEmpty()) {
      if (StringUtils.isBlank(proId.getLast())) {
        proId.removeLast();
      } else {
        break;
      }
    }
    while (type != null && !type.isEmpty()) {
      if (StringUtils.isBlank(type.getLast())) {
        type.removeLast();
      } else {
        break;
      }
    }
    if (proId != null && proId.isEmpty()) {
      proId = null;
    }
    if (type != null && type.isEmpty()) {
      type = null;
    }

    Integer n;
    Integer m;
    if (currentPage == null && pageSize == null) {
      n = m = null;
    } else {
      n = (currentPage - 1) * pageSize;
      m = pageSize;
    }
    List<AIInformationEntity> list = AIInformationService.queryUnconfirmedAIInformationList(usrId,proId, state, beginTime, endTime, type, n, m);
    Integer Num = AIInformationService.UnconfirmedAIInformationNum(usrId,proId, state, beginTime, endTime, type);
    r.addData("allAssociationIdList", list);
    r.addData("Num",Num);
    return r;
  }


  @PostMapping("/mistakeConfirm")

  public R mistakeconfirm(@RequestParam(value = "message_id") Long messageId) {
    R r = R.ok();
    AIInformationService.update(new UpdateWrapper<AIInformationEntity>().set("state", 5).eq("message_id", messageId));
    return r;
  }


  @PostMapping("/eventConfirm")

  public R eventConfirm(@RequestParam(value = "message_id") Long messageId) {
    R r = R.ok();
    AIInformationService.update(new UpdateWrapper<AIInformationEntity>().set("state", 2).eq("message_id", messageId));
    return r;
  }
}
