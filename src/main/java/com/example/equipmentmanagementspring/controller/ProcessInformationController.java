package com.example.equipmentmanagementspring.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.equipmentmanagementspring.entity.AIInformationEntity;
import com.example.equipmentmanagementspring.entity.ProcessInformationEntity;
import com.example.equipmentmanagementspring.form.ProcessInformationForm;
import com.example.equipmentmanagementspring.form.UserForm;
import com.example.equipmentmanagementspring.service.AIInformationService;
import com.example.equipmentmanagementspring.service.ProcessInformationService;
import com.example.equipmentmanagementspring.utils.R;
import com.example.equipmentmanagementspring.utils.DateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProcessInformation")
public class ProcessInformationController {
  private final ProcessInformationService processInformationService;
  private final AIInformationService aiInformationService;

  public ProcessInformationController(ProcessInformationService processInformationService,
                                      AIInformationService aiInformationService) {
    this.processInformationService = processInformationService;
    this.aiInformationService = aiInformationService;
  }

  @GetMapping("/getUserByProId")

  public R getUserByProId(@RequestParam(value = "message_id") Long messageId) {
    R r = R.ok();
    List<UserForm> list = processInformationService.queryUserInformationsByUProId(messageId);
    r.addData("UserInformation", list);
    return r;
  }

  @GetMapping("/getInformation")
  public R getInformation() {
    R r = R.ok();
    List<ProcessInformationForm> list = processInformationService.queryProcessInformations();
    r.addData("Processinformation", list);
    return r;
  }


  @GetMapping("/getInformationByMessageId")
  public R getInformationByMessageId(@RequestParam(value = "message_id") Long messageId,
                                     @RequestParam(value = "current_page", required = false) Integer currentPage,
                                     @RequestParam(value = "page_size", required = false) Integer pageSize) {
    R r = R.ok();
    Integer n;
    Integer m;
    if (currentPage == null || pageSize == null) {
      n = m = null;
    } else {
      n = (currentPage - 1) * pageSize;
      m = pageSize;
    }
    List<ProcessInformationForm> list = processInformationService.queryProcessInformationsByMessageId(messageId, n, m);
    r.addData("Processinformation", list);
    return r;
  }

  @GetMapping("/getInformationByUserId")
  public R queryProcessInformationsByUserId(@RequestParam(value = "user_id") String userId,
                                            @RequestParam(value = "current_page", required = false) Integer currentPage,
                                            @RequestParam(value = "page_size", required = false) Integer pageSize
  ) {
    R r = R.ok();

    Integer n;
    Integer m;
    if (currentPage == null && pageSize == null) {
      n = m = null;
    } else {
      n = (currentPage - 1) * pageSize;
      m = pageSize;
    }
    List<ProcessInformationForm> list = processInformationService.queryProcessInformationsByUserId(userId, n, m);

    r.addData("Processinformation", list);
    return r;
  }

  @PostMapping("/dealEvent")
  public R eventDeal(@RequestBody ProcessInformationForm form) {
    ProcessInformationEntity info = new ProcessInformationEntity();
    info.setMessageId(form.getMessageId());
    info.setProcessDeadline(DateUtils.stringToDate(form.getProcessDeadline(), DateUtils.DATE_TIME_PATTERN));
    info.setProcessorId(form.getProcessorId());
    info.setProcessRemark(form.getProcessRemark());
    info.setProcessRequest(form.getProcessRequest());
    info.setSenderId(form.getSenderId());
    info.setSendTime(DateUtils.stringToDate(form.getSendTime(), DateUtils.DATE_TIME_PATTERN));
    processInformationService.save(info);
    aiInformationService.update(new UpdateWrapper<AIInformationEntity>().set("state", 3).eq("message_id", form.getMessageId()));

    R r = R.ok();
    return r;
  }

  @PostMapping("/finishEvent")
  public R eventFinish(@RequestBody ProcessInformationForm form) {
    ProcessInformationEntity info = new ProcessInformationEntity();
    info.setMessageId(form.getMessageId());
    info.setProcessDeadline(DateUtils.stringToDate(form.getProcessDeadline(), DateUtils.DATE_TIME_PATTERN));
    info.setProcessorId(form.getProcessorId());
    info.setProcessRemark(form.getProcessRemark());
    info.setProcessRequest(form.getProcessRequest());
    info.setSenderId(form.getSenderId());
    info.setSendTime(DateUtils.stringToDate(form.getSendTime(), DateUtils.DATE_TIME_PATTERN));
    processInformationService.save(info);
    aiInformationService.update(new UpdateWrapper<AIInformationEntity>().set("state", 4).eq("message_id", form.getMessageId()));

    R r = R.ok();
    return r;
  }

  @GetMapping("/lastProcessInformation")
  public R lastProcessInformationByMessageId(@RequestParam(value = "message_id") Long messageId) {
    ProcessInformationForm form = processInformationService.lastProcessInformationByMessageId(messageId);
    R r = R.ok();
    r.addData("information", form);
    return r;
  }
}

