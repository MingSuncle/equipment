package com.example.equipmentmanagementspring.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.example.equipmentmanagementspring.entity.EventConfigEntity;
import com.example.equipmentmanagementspring.entity.IpcConfigEntity;
import com.example.equipmentmanagementspring.exception.BoxNotFoundException;
import com.example.equipmentmanagementspring.form.ConfigRequestResponseBody;
import com.example.equipmentmanagementspring.form.ConfigResponse;
import com.example.equipmentmanagementspring.mapper.BoxConfigDao;
import com.example.equipmentmanagementspring.mapper.CenterConfigDao;
import com.example.equipmentmanagementspring.mapper.EventConfigDao;
import com.example.equipmentmanagementspring.mapper.IpcConfigDao;
import com.example.equipmentmanagementspring.service.ResponseConfigService;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@EnableMPP
public class ResponseConfigServiceImpl implements ResponseConfigService {

  @Resource
  private BoxConfigDao boxConfigMapper;

  @Resource
  private IpcConfigDao ipcConfigMapper;

  @Resource
  private EventConfigDao aiEventConfigMapper;

  @Resource
  private CenterConfigDao CenterConfigMapper;


  public ConfigResponse configResponse(String centerId, String boxId)   throws BoxNotFoundException {
    boolean flag = false;

    List<CenterConfigEntity> clist = CenterConfigMapper.SelectCenterConfig(centerId);
    CenterConfigEntity centerConfig = clist.get(0);
    if(centerConfig.getState().equals(1))
    {
      flag = true;
      CenterConfigMapper.deleteCenterConfig(centerId,1);
      centerConfig.setState(2);
      CenterConfigMapper.updateByMultiId(centerConfig);
    }
    String[] center_urlString = centerConfig.getCenterThirdPartyUrls().split(",");
    List<String> center_urlList = new ArrayList<>(Arrays.asList(center_urlString));
    List<ConfigRequestResponseBody> crrb =  new ArrayList<>();
    List<BoxConfigEntity> blist = boxConfigMapper.SelectBoxConfig(boxId);
    BoxConfigEntity boxConfig = blist.get(0);
    if(boxConfig.getState().equals(1))
    {
      flag = true;
      boxConfigMapper.deleteBoxConfig(boxId,1);
      boxConfig.setState(2);
      boxConfigMapper.updateByMultiId(boxConfig);
    }
      String boxNo = boxConfig.getBoxNo();
    List<String> ipcConfigList = ipcConfigMapper.SelectIpcId(boxNo);

      String[] urlString = boxConfig.getCenterThirdPartyUrls().split(",");
      List<String> urlList = new ArrayList<>(Arrays.asList(urlString));
      String[] aiString = boxConfig.getBoxAiEventId().split(",");
      List<Map<String, Object>> boxVideoInformationList = new ArrayList<>();
      for (String ipcId : ipcConfigList) {
        List<IpcConfigEntity> ilist = ipcConfigMapper.SelectIpcConfig(ipcId);
        IpcConfigEntity ipcConfig = ilist.get(0);
        if(ipcConfig.getState().equals(1))
        {
          flag = true;
          ipcConfigMapper.deleteIpcConfig(ipcId,1);
          ipcConfig.setState(2);
          ipcConfigMapper.updateByMultiId(ipcConfig);
        }
        List<String> aiEventConfigList = aiEventConfigMapper.SelectEventId(ipcConfig.getIpcId());


        Map<String, ConfigRequestResponseBody.video_ai_event> aiList = new HashMap<>();
        for (String eventId : aiEventConfigList) {
          List<EventConfigEntity> elist = aiEventConfigMapper.SelectEventConfig(eventId,ipcConfig.getIpcId());
          EventConfigEntity aiEventConfig = elist.get(0);
          if(aiEventConfig.getState().equals(1))
          {
            flag = true;
            aiEventConfigMapper.deleteEventConfig(eventId,1);
            aiEventConfig.setState(2);
            aiEventConfigMapper.updateByMultiId(aiEventConfig);
          }
          ConfigRequestResponseBody.video_ai_event video_ai_event = ConfigRequestResponseBody.video_ai_event.builder()
                  .AIevent_confidence(aiEventConfig.getConfidence())
                  .AIevent_iou(aiEventConfig.getIou())
                  .AIevent_level(aiEventConfig.getLevel())
                  .AIevent_processmode(aiEventConfig.getMethod())
                  .AIevent_processparam(aiEventConfig.getParameter())
                  .build();
          aiList.put(aiEventConfig.getAiEventId(), video_ai_event);
        }
        Double[][] parse = JSON.parseObject(ipcConfig.getIpcDetectArea(), Double[][].class);
        Map<String, Object> videoInformation = new HashMap<>();
        List<String> video_detection_time = new ArrayList<>();
        videoInformation.put("video_ai_event_list", aiList);
        video_detection_time.add(ipcConfig.getIpcDetectTimeBgn().substring(0,5));
        video_detection_time.add(ipcConfig.getIpcDetectTimeEnd().substring(0,5));
        videoInformation.put("video_detection_area", parse);
        videoInformation.put("video_detection_time", video_detection_time);
        videoInformation.put("video_fps", ipcConfig.getIpcFps());
        videoInformation.put("video_ip", ipcConfig.getIpcIp());
        videoInformation.put("video_id", ipcConfig.getIpcId());
        videoInformation.put("video_name", ipcConfig.getIpcName());
        videoInformation.put("video_position", ipcConfig.getIpcPosition());
        videoInformation.put("video_url", ipcConfig.getIpcUrl());
        boxVideoInformationList.add(videoInformation);
      }

      ConfigRequestResponseBody crr = ConfigRequestResponseBody.builder()
              .center_project(boxConfig.getProjectId())
              .center_third_party_url_list(urlList)
              .box_center_ip(centerConfig.getCenterIp())
              .box_center_id(boxConfig.getProjectId())
              .box_ip(boxConfig.getBoxIp())
              .box_id(boxNo)
              .box_name(boxConfig.getBoxName())
              .box_ai_event_list(Arrays.asList(aiString))
              .box_video_information_list(boxVideoInformationList)
              .build();
      crrb.add(crr);
      if(flag==true){
            return ConfigResponse.builder()
            .center_id(centerConfig.getCenterId())
            .center_ip(centerConfig.getCenterIp())
            .center_third_party_url_list(center_urlList)
            .center_project(centerConfig.getProjectId())
            .center_manager_box_list(crrb)
            .build();
      }
      else {
        return null;
      }
  }

  public ConfigResponse ConfigRequestResponseBody2(String centerId)   throws BoxNotFoundException {
    CenterConfigEntity cce = new CenterConfigEntity();
    cce.setCenterId(centerId);
    cce.setState(1);
    CenterConfigEntity centerConfig = CenterConfigMapper.selectByMultiId(cce);
    String[] center_urlString = centerConfig.getCenterThirdPartyUrls().split(",");
    List<String> center_urlList = new ArrayList<>(Arrays.asList(center_urlString));
    List<ConfigRequestResponseBody> crrb =  new ArrayList<>();
    List<BoxConfigEntity> boxConfigList = boxConfigMapper.selectList(new LambdaQueryWrapper<BoxConfigEntity>()
            .eq(BoxConfigEntity::getBoxCenterId, centerId).eq(BoxConfigEntity::getState,1));
    for(BoxConfigEntity boxConfig : boxConfigList) {
      String boxNo = boxConfig.getBoxNo();
      List<IpcConfigEntity> ipcConfigList = ipcConfigMapper.selectList(new LambdaQueryWrapper<IpcConfigEntity>()
              .eq(IpcConfigEntity::getIpcBoxNo, boxNo).eq(IpcConfigEntity::getState, 1));

      String[] urlString = boxConfig.getCenterThirdPartyUrls().split(",");
      List<String> urlList = new ArrayList<>(Arrays.asList(urlString));
      String[] aiString = boxConfig.getBoxAiEventId().split(",");
      List<Map<String, Object>> boxVideoInformationList = new ArrayList<>();
      for (IpcConfigEntity ipcConfig : ipcConfigList) {
        List<EventConfigEntity> aiEventConfigList = aiEventConfigMapper.selectList(
                new LambdaQueryWrapper<EventConfigEntity>()
                        .eq(EventConfigEntity::getIpcId, ipcConfig.getIpcId())
        );
        Map<String, ConfigRequestResponseBody.video_ai_event> aiList = new HashMap<>();
        for (EventConfigEntity aiEventConfig : aiEventConfigList) {
          ConfigRequestResponseBody.video_ai_event video_ai_event = ConfigRequestResponseBody.video_ai_event.builder()
                  .AIevent_confidence(aiEventConfig.getConfidence())
                  .AIevent_iou(aiEventConfig.getIou())
                  .AIevent_level(aiEventConfig.getLevel())
                  .AIevent_processmode(aiEventConfig.getMethod())
                  .AIevent_processparam(aiEventConfig.getParameter())
                  .build();
          aiList.put(aiEventConfig.getAiEventId(), video_ai_event);
        }
        Map<String, Object> videoInformation = new HashMap<>();
        List<String> video_detection_time = new ArrayList<>();
        videoInformation.put("video_ai_event_list", aiList);
        video_detection_time.add(ipcConfig.getIpcDetectTimeBgn());
        video_detection_time.add(ipcConfig.getIpcDetectTimeEnd());
        videoInformation.put("video_detection_area", ipcConfig.getIpcDetectArea());
        videoInformation.put("video_detection_time", video_detection_time);
        videoInformation.put("video_fps", ipcConfig.getIpcFps());
        videoInformation.put("video_ip", ipcConfig.getIpcIp());
        videoInformation.put("video_id", ipcConfig.getIpcId());
        videoInformation.put("video_name", ipcConfig.getIpcName());
        videoInformation.put("video_position", ipcConfig.getIpcPosition());
        videoInformation.put("video_url", ipcConfig.getIpcUrl());
        boxVideoInformationList.add(videoInformation);
      }

      ConfigRequestResponseBody crr = ConfigRequestResponseBody.builder()
              .center_project(boxConfig.getProjectId())
              .center_third_party_url_list(urlList)
              .box_center_ip(centerConfig.getCenterIp())
              .box_center_id(boxConfig.getProjectId())
              .box_ip(boxConfig.getBoxIp())
              .box_id(boxNo)
              .box_name(boxConfig.getBoxName())
              .box_ai_event_list(Arrays.asList(aiString))
              .box_video_information_list(boxVideoInformationList)
              .build();
      crrb.add(crr);
    }

    return ConfigResponse.builder()
            .center_id(centerConfig.getCenterId())
            .center_ip(centerConfig.getCenterIp())
            .center_third_party_url_list(center_urlList)
            .center_project(centerConfig.getProjectId())
            .center_manager_box_list(crrb)
            .build();
  }

  @Override
  public void initialEdgeConfig(String json) {

  }
}
