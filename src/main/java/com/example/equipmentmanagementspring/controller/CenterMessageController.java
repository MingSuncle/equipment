package com.example.equipmentmanagementspring.controller;


import com.example.equipmentmanagementspring.entity.AIInformationEntity;
import com.example.equipmentmanagementspring.service.AIInformationService;
import com.example.equipmentmanagementspring.utils.DateUtils;
import com.example.equipmentmanagementspring.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/center")
public class CenterMessageController {
  @Value("${user.filepath}")
  private String filePath;
  private final AIInformationService aiInformationService;

  public CenterMessageController(AIInformationService aiInformationService) {
    this.aiInformationService = aiInformationService;
  }


  @RequestMapping("/AImessage")
  public R savePost(@RequestParam(value = "file_type") String fileType,
                    @RequestParam(value = "file_name") String fileName,
                    @RequestParam(value = "center_project") String centerProject,
                    @RequestParam(value = "center_id") String centerId,
                    @RequestParam(value = "box_id") String boxId,
                    @RequestParam(value = "video_id") String videoID,
                    @RequestParam(value = "time") String time,
                    @RequestParam(value = "event_type") String eventType,
                    @RequestParam(value = "description") String description,
                    @RequestPart("file") MultipartFile multipartFile) {

    // 生成一个随机的名称，避免文件名重复
    UUID uuid = UUID.randomUUID();
    // 获取原文件名称
    String originalFileName = multipartFile.getOriginalFilename();
    // 获取原文件的后缀
    String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
    // 保存文件
    Object filePath;
    File file = new File(this.filePath + uuid + fileSuffix);
    try {
      multipartFile.transferTo(file);
    } catch (IOException e) {
      e.printStackTrace();
      return R.error();
    }

    String url = "http://47.100.71.212:8080/pictures/" + uuid + fileSuffix;
    AIInformationEntity info = new AIInformationEntity();
    info.setProjectId(centerProject);
    info.setCenterIp(centerId);
    info.setBoxIp(boxId);
    info.setIpcIp(videoID);
    Integer fileTypeI = Integer.parseInt(fileType);
    info.setFileType(fileTypeI);
    info.setAIeventName(eventType);
    info.setDescription(description);
    info.setTime(DateUtils.stringToDate(time, DateUtils.DATE_TIME_PATTERN));
    info.setFile(url);
    info.setState("0");
    aiInformationService.save(info);

    R r = R.ok();
    return r;
  }
}
