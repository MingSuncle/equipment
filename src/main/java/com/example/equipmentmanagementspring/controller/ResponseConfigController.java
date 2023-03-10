package com.example.equipmentmanagementspring.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.equipmentmanagementspring.entity.*;
import com.example.equipmentmanagementspring.exception.BoxNotFoundException;
import com.example.equipmentmanagementspring.mapper.CenterConfigDao;
import com.example.equipmentmanagementspring.service.*;
import com.example.equipmentmanagementspring.utils.R;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController

@EnableMPP
@RequestMapping("/configRequest")
public class ResponseConfigController {
  @Resource
  private CenterConfigDao CenterConfigMapper;

  @Value("${user.filepath}")


  private String filePath;
  private final CenterConfigService centerConfigService;
  private final BoxConfigService boxConfigService;
  private final IpcConfigService ipcConfigService;
  private final EventConfigService eventConfigService;
  private final ResponseConfigService responseConfigService;
  private final HeartDetectService heartDetectService;

  public ResponseConfigController(CenterConfigService CenterConfigService,
                                  BoxConfigService BoxConfigService,
                                  IpcConfigService IpcConfigService,
                                  EventConfigService EventConfigService,
                                  ResponseConfigService ResponseConfigService,
                                  HeartDetectService HeartDetectService
                                  ) {
    this.centerConfigService = CenterConfigService;
    this.boxConfigService = BoxConfigService;
    this.ipcConfigService = IpcConfigService;
    this.eventConfigService = EventConfigService;
    this.responseConfigService = ResponseConfigService;
    this.heartDetectService = HeartDetectService;
  }


  @PostMapping("/statePicture")
  public R heartPicture(@RequestParam(value = "video_id") String vedioId){
    R r =R.ok();
    HeartDetectEntity hde = heartDetectService.getPic(vedioId);
    r.addData("picture",hde.getUrl());
    return r;
  }


  @PostMapping("/postState")
  public R heartDetect( @RequestParam(value = "center_id") String centerId,
                        @RequestParam(value = "box_id") String boxId,
                        @RequestParam(value = "video_id") String videoID,
                        @RequestPart("file") MultipartFile multipartFile){
    R r = R.ok();
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

    String url = "http://47.100.71.212:8080/pictures/"+uuid+fileSuffix;
    HeartDetectEntity hde = new HeartDetectEntity();
    hde.setUrl(url);
    hde.setBoxId(boxId);
    hde.setCenterId(centerId);
    hde.setVideoId(videoID);
    UpdateWrapper<HeartDetectEntity> wrapper =  new UpdateWrapper<HeartDetectEntity>();
    wrapper.eq("center_id",centerId).eq("box_id",boxId).eq("video_id",videoID);
    heartDetectService.saveOrUpdate(hde,wrapper);
    return r;
  }


  @GetMapping("/response")
  public R responseConfigRequest(@RequestParam(value = "centerId") String centerId,
                                  @RequestParam(value = "boxNo") String boxNo)
                                  {
    R r = R.ok();
    try {
      return  r.addData("result",responseConfigService.configResponse(centerId,boxNo));
    } catch (BoxNotFoundException boxNotFoundException) {
      return R.error();
    }

  }


  @GetMapping("/response2")
  public R responseConfigRequest2(@RequestParam(value = "center_id") String centerId
                                 )
  {
//    CenterConfigEntity cce = new CenterConfigEntity();
//    cce.setCenterId(centerId);
//    cce.setState(1);
//    CenterConfigEntity centerConfig = CenterConfigMapper.selectByMultiId(cce);

    R r = R.ok();
    try {
      return  r.addData("result",responseConfigService.ConfigRequestResponseBody2(centerId));
    } catch (BoxNotFoundException boxNotFoundException) {
      return R.error();
    }

  }


  @PostMapping("/initialEdgeConfig2")
  public R initialEdgeConfig(@RequestBody String json)
  {
    R r = R.ok();
    JSONObject joj = JSON.parseObject(json.toString());
    String centerIp = (String)joj.get("center_project");
    String centerProject = (String)joj.get("center_project");
    List<String> list = (List<String>)joj.get("center_third_party_url_list");
    String url = String.join(",", list);
    String centerId = (String)joj.get("center_project");
    CenterConfigEntity cce = new CenterConfigEntity();
    cce.setCenterIp(centerIp);
    cce.setCenterId(centerId);
    cce.setProjectId(centerProject);
    cce.setState(2);
    cce.setCenterThirdPartyUrls(url);
//    UpdateWrapper<CenterConfigEntity> wrapper =  new UpdateWrapper<CenterConfigEntity>();
//    wrapper.eq("state",2).eq("center_id",cce.getCenterId());
//    centerConfigService.saveOrUpdate(cce,wrapper);
    centerConfigService.saveOrUpdateByMultiId(cce);
    List<JSONObject> box_list = (List<JSONObject>)joj.get("center_manager_box_list");
    for(JSONObject  box: box_list){
      String boxCenterProject = (String)joj.get("center_project");
      String boxId = (String)joj.get("box_id");
      List<Integer> eventList = (List<Integer>)joj.get("box_ai_event_list");
      String event = StringUtils.join(eventList, ",");
      List<String> boxUrlList = (List<String>)joj.get("center_third_party_url_list");
      String boxurl = String.join(",", boxUrlList);
      String boxCenterId = (String)box.get("box_center_id");
      String boxIp = (String)box.get("box_ip");
      String boxName = (String)box.get("box_name");
      BoxConfigEntity bce = new BoxConfigEntity();
      bce.setBoxNo(boxId);
      bce.setState(2);
      bce.setBoxAiEventId(event);
      bce.setBoxCenterId(boxCenterId);
      bce.setCenterThirdPartyUrls(boxurl);
      bce.setProjectId(boxCenterProject);
      bce.setBoxIp(boxIp);
      bce.setBoxName(boxName);
//      UpdateWrapper<BoxConfigEntity> bwrapper =  new UpdateWrapper<BoxConfigEntity>();
//      bwrapper.eq("box_no",bce.getBoxNo()).eq("state",2);
//      boxConfigService.saveOrUpdate(bce,bwrapper);
      boxConfigService.saveOrUpdateByMultiId(bce);
      List<JSONObject> ipc_list = (List<JSONObject>)box.get("box_video_information_list");
      for(JSONObject  ipc: ipc_list){
        String videoName = (String)ipc.get("video_name");
        String videoUrl = (String)ipc.get("video_url");
        String videoPosition = (String)ipc.get("video_position");
        String videoIp = (String)ipc.get("video_ip");
        Object fpsObject = ipc.get("video_fps");
        Double videoFps = Double.parseDouble(String.valueOf(fpsObject));
        List<String> detection_time = (List<String>)ipc.get("video_detection_time");
        String beginTime = detection_time.get(0);
        String endTime = detection_time.get(1);
        String detectionArea = JSON.toJSONString(ipc.get("video_detection_area"));
        String videoId = (String)ipc.get("video_id");
        IpcConfigEntity ice = new IpcConfigEntity();
        ice.setIpcId(videoId);
        ice.setState(2);
        ice.setIpcBoxNo(boxId);
        ice.setIpcDetectArea(detectionArea);
        ice.setIpcDetectTimeBgn(beginTime);
        ice.setIpcDetectTimeEnd(endTime);
        ice.setIpcFps(videoFps);
        ice.setIpcIp(videoIp);
        ice.setIpcName(videoName);
        ice.setIpcUrl(videoUrl);
        ice.setIpcposition(videoPosition);
//        UpdateWrapper<IpcConfigEntity> iwrapper =  new UpdateWrapper<IpcConfigEntity>();
//        iwrapper.eq("ipc_id",ice.getIpcId()).eq("state",2);
//        ipcConfigService.saveOrUpdate(ice,iwrapper);
        ipcConfigService.saveOrUpdateByMultiId(ice);
        JSONObject event_list = (JSONObject)ipc.get("video_ai_event_list");
        for (Map.Entry entry : event_list.entrySet()) {
          JSONObject aiEvent = (JSONObject)entry.getValue();
          EventConfigEntity ece = new EventConfigEntity();
          ece.setIpcId(videoId);
          ece.setAiEventId((String)entry.getKey());
          Object object = aiEvent.get("AIevent_iou");
          Double iou = Double.parseDouble(String.valueOf(object));
          Object objectc = aiEvent.get("AIevent_confidence");
          Double confidence = Double.parseDouble(String.valueOf(objectc));
          Integer level = (Integer)aiEvent.get("AIevent_level");
          Integer method = (Integer)aiEvent.get("AIevent_processmode");
          String para = (String)aiEvent.get("AIevent_processparam");
          ece.setIou(iou);
          ece.setLevel(level);
          ece.setMethod(method);
          ece.setConfidence(confidence);
          ece.setState(2);
          ece.setParameter(para);
//          UpdateWrapper<EventConfigEntity> ewrapper =  new UpdateWrapper<EventConfigEntity>();
//          ewrapper.eq("state",2).eq("ai_event_id",ece.getAiEventId()).eq("ipc_id",ece.getIpcId());
//          eventConfigService.saveOrUpdate(ece,ewrapper);
          eventConfigService.saveOrUpdateByMultiId(ece);


        }

      }
    }

    return r;
  }

  @PostMapping("/initialEdgeConfig")
  public R initialEdgeConfig_v2(@RequestBody String json)
  {
    R r = R.ok();
    JSONObject joj = JSON.parseObject(json.toString());
    String centerIp = (String)joj.get("box_center_ip");
    String centerProject = (String)joj.get("center_project");
    JSONObject thirdUrl = (JSONObject)joj.get("center_third_party_url_list");
    List<String> temp = new ArrayList<>();
    for (Map.Entry entry : thirdUrl.entrySet()) {

      temp.add(String.valueOf(entry.getValue()));
    }
    String url = String.join(",", temp);

    String centerId = (String)joj.get("center_project");
    CenterConfigEntity cce = new CenterConfigEntity();
    cce.setCenterIp(centerIp);
    cce.setCenterId(centerId);
    cce.setProjectId(centerProject);
    cce.setState(2);
    cce.setCenterThirdPartyUrls(url);
//    UpdateWrapper<CenterConfigEntity> wrapper =  new UpdateWrapper<CenterConfigEntity>();
//    wrapper.eq("state",2).eq("center_id",cce.getCenterId());
//    centerConfigService.saveOrUpdate(cce,wrapper);
    centerConfigService.saveOrUpdateByMultiId(cce);


      String boxCenterProject = (String)joj.get("center_project");
      String boxId = (String)joj.get("box_id");
      List<Integer> eventList = (List<Integer>)joj.get("box_ai_event_list");
      String event = StringUtils.join(eventList, ",");

      String boxurl = url;
      String boxCenterId = (String)joj.get("box_center_id");
      String boxIp = (String)joj.get("box_ip");
      String boxName = (String)joj.get("box_name");
      BoxConfigEntity bce = new BoxConfigEntity();
      bce.setBoxNo(boxId);
      bce.setState(2);
      bce.setBoxAiEventId(event);
      bce.setBoxCenterId(boxCenterId);
      bce.setCenterThirdPartyUrls(boxurl);
      bce.setProjectId(boxCenterProject);
      bce.setBoxIp(boxIp);
      bce.setBoxName(boxName);
//      UpdateWrapper<BoxConfigEntity> bwrapper =  new UpdateWrapper<BoxConfigEntity>();
//      bwrapper.eq("box_no",bce.getBoxNo()).eq("state",2);
//      boxConfigService.saveOrUpdate(bce,bwrapper);
      boxConfigService.saveOrUpdateByMultiId(bce);
      List<JSONObject> ipc_list = (List<JSONObject>)joj.get("box_video_information_list");
      for(JSONObject  ipc: ipc_list){
        String videoName = (String)ipc.get("video_name");
        String videoUrl = (String)ipc.get("video_url");
        String videoPosition = (String)ipc.get("video_position");
        String videoIp = (String)ipc.get("video_ip");
        Object fpsObject = ipc.get("video_fps");
        Double videoFps = Double.parseDouble(String.valueOf(fpsObject));
//        List<String> detection_time = (List<String>)ipc.get("video_detection_time");

//        String beginTime = detection_time.get(0);
//        String endTime = detection_time.get(1);
        String Time = JSON.toJSONString(ipc.get("video_detection_time"));
//        String Time = "123";
        String detectionArea = JSON.toJSONString(ipc.get("video_detection_area"));
        String videoId = (String)ipc.get("video_id");
        IpcConfigEntity ice = new IpcConfigEntity();
        ice.setIpcId(videoId);
        ice.setState(2);
        ice.setIpcBoxNo(boxId);
        ice.setIpcDetectArea(detectionArea);
//        ice.setIpcDetectTimeBgn(beginTime);
//        ice.setIpcDetectTimeEnd(endTime);
        ice.setIpcTime(Time);
        ice.setIpcFps(videoFps);
        ice.setIpcIp(videoIp);
        ice.setIpcName(videoName);
        ice.setIpcUrl(videoUrl);
        ice.setIpcposition(videoPosition);
//        UpdateWrapper<IpcConfigEntity> iwrapper =  new UpdateWrapper<IpcConfigEntity>();
//        iwrapper.eq("ipc_id",ice.getIpcId()).eq("state",2);
//        ipcConfigService.saveOrUpdate(ice,iwrapper);
        ipcConfigService.saveOrUpdateByMultiId(ice);
        JSONObject event_list = (JSONObject)ipc.get("video_ai_event_list");
        for (Map.Entry entry : event_list.entrySet()) {
          JSONObject aiEvent = (JSONObject)entry.getValue();
          EventConfigEntity ece = new EventConfigEntity();
          ece.setIpcId(videoId);
          ece.setAiEventId((String)entry.getKey());
          Object object = aiEvent.get("AIevent_iou");
          Double iou = Double.parseDouble(String.valueOf(object));
          Object objectc = aiEvent.get("AIevent_confidence");
          Double confidence = Double.parseDouble(String.valueOf(objectc));
          Integer level = (Integer)aiEvent.get("AIevent_level");
          Integer method = (Integer)aiEvent.get("AIevent_processmode");
          String para = (String)aiEvent.get("AIevent_processparam");
          ece.setIou(iou);
          ece.setLevel(level);
          ece.setMethod(method);
          ece.setConfidence(confidence);
          ece.setState(2);
          ece.setParameter(para);
//          UpdateWrapper<EventConfigEntity> ewrapper =  new UpdateWrapper<EventConfigEntity>();
//          ewrapper.eq("state",2).eq("ai_event_id",ece.getAiEventId()).eq("ipc_id",ece.getIpcId());
//          eventConfigService.saveOrUpdate(ece,ewrapper);
          eventConfigService.saveOrUpdateByMultiId(ece);


        }

      }


    return r;
  }
}
