package com.example.equipmentmanagementspring.deviceConfig.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.equipmentmanagementspring.deviceConfig.entity.*;
import com.example.equipmentmanagementspring.deviceConfig.service.*;
import com.example.equipmentmanagementspring.deviceConfig.utils.FileDes;
import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.IpcConfigEntity;
import com.example.equipmentmanagementspring.service.BoxConfigService;
import com.example.equipmentmanagementspring.service.IpcConfigService;
import com.example.equipmentmanagementspring.utils.DateUtils;
import com.example.equipmentmanagementspring.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;


@Api(tags = "盒子")
@ApiSupport(order = 10)
@RestController
@RequestMapping("/box")
@EnableMPP
public class BoxInformationController {
    @Value("${userPath.codeFile}")
    private String codeFile;

    private final BoxInformationService boxInformationService;
    private final BoxConfigService boxConfigService;

    private final IpcConfigService ipcConfigService;
    private final ChannelService channelService;

    private final AreaService areaService;

    private final EventService eventService;

    private final BoxModelService boxModelService;

    private final ModelInformationService modelInformationService;

    private final ProcessParamService processParamService;




    public BoxInformationController(BoxInformationService boxInformationService, BoxConfigService boxConfigService, IpcConfigService ipcConfigService, ChannelService channelService, AreaService areaService, EventService eventService, BoxModelService boxModelService, ModelInformationService modelInformationService, ProcessParamService processParamService) {
        this.boxInformationService = boxInformationService;
        this.boxConfigService = boxConfigService;
        this.ipcConfigService = ipcConfigService;
        this.channelService = channelService;
        this.areaService = areaService;
        this.eventService = eventService;

        this.boxModelService = boxModelService;
        this.modelInformationService = modelInformationService;
        this.processParamService = processParamService;
    }


//    返回通道数和事件idList
    @ApiOperation("激活盒子")
    @PostMapping ("/registerBox")
    public R activateBox(@RequestParam(value = "box_id") String boxId) throws JsonProcessingException {
        R r = R.ok();
        DateUtils dateUtils = new DateUtils();
        Integer result = boxInformationService.activateBox(boxId);
        if (result == 404) {
            r = R.error(404, "Not Found");
        }
        else{
            BoxInformationEntity box = boxInformationService.getById(boxId);
            BoxConfigEntity temp = new BoxConfigEntity();
            temp.setBoxNo(boxId);
            temp.setState(2);
            BoxConfigEntity boxConfig = boxConfigService.selectByMultiId(temp);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(boxConfig.getCenterThirdPartyUrls());
            } catch (Exception e) {

            }


            r.addData("channelLimit",box.getChannelNumberLimit());
            r.addData("eventList",box.getAIeventLimit());
            r.addData("expireTime",dateUtils.format(box.getExpireTime()));
            r.addData("thirdPartyUrls",jsonNode);

            return r;
        }
        return r;
    }

    @ApiOperation("下发加密模型")
    @RequestMapping("/pushModel")
    public String pushModel(@RequestParam(value = "box_id")String boxId,
                       HttpServletResponse response) throws Exception {
        //算法加密
        BoxInformationEntity box = boxInformationService.getById(boxId);
        FileDes fileDes = new FileDes(boxId);
//        fileDes.encrypt("D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书.doc", "D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书dec.doc");
        fileDes.encrypt("/root/javaWorkspace/数据库实体设计说明书.doc", "/root/javaWorkspace/数据库实体设计说明书dec.doc");
//        fileDes.decrypt("D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书dec.doc", "D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书解密.doc");
        fileDes.decrypt("/root/javaWorkspace/数据库实体设计说明书dec.doc", "/root/javaWorkspace/数据库实体设计说明书解密.doc");
//            String path = "D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书dec.doc";
        try{
        String path = "/root/javaWorkspace/test.zip";
            // path是指想要下载的文件的路径
            File file = new File(path);
            System.out.println(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            System.out.println("文件后缀名：" + ext);

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            return "success";
        } catch (IOException ex) {
            ex.printStackTrace();
            return "fail";
        }
    }

    @ApiOperation("盒子上传json，服务器解析")
    @PostMapping("/uploadConfig")
    public R uploadConfig(@RequestBody String json)
    {
        R r = R.ok();
        JSONObject object = JSON.parseObject(json.toString());
        Object channelLimitObj =object.get("channelLimit");
        Integer channelLimit = Integer.parseInt(String.valueOf(channelLimitObj));
        String eventList = (String)object.get("eventList");
//        String expireTime = (String)object.get("expiration_date");       该字段目前无用
        String boxId = (String)object.get("box_id");
        String boxIp = (String)object.get("box_ip");
        String boxName = (String)object.get("box_name");
        String boxThirdPartyUrl =String.valueOf(object.get("third_party_url_list"));
        System.out.println(boxThirdPartyUrl);
        BoxConfigEntity bce = new BoxConfigEntity();
        bce.setCenterThirdPartyUrls(boxThirdPartyUrl);
        bce.setBoxNo(boxId);
        bce.setState(2);
        bce.setBoxIp(boxIp);
        bce.setBoxName(boxName);
        boxConfigService.saveOrUpdateByMultiId(bce);
        List<JSONObject> channel_list = (List<JSONObject>)object.get("channel_list");
        for(JSONObject channel:channel_list){
            Integer channelId = (Integer) channel.get("channel_id");
            String channelName = (String)channel.get("channel_name");
            Integer AIeventId = (Integer) channel.get("AIevent_id");
//            Object fpsObject = channel.get("video_fps");
//            Double videoFps = Double.parseDouble(String.valueOf(fpsObject));
            Integer videoFps = (Integer) channel.get("video_fps");
            String videoName = (String)channel.get("video_name");
            String videoId = (String)channel.get("video_id");
//            String videoPosition = (String)channel.get("video_position");
            String videoBrand = (String)channel.get("video_brand");
            String videoIp = (String)channel.get("video_ip");
            String videoPort = (String)channel.get("video_port");
            String videoStream = (String)channel.get("video_stream");
            IpcConfigEntity ipc = new IpcConfigEntity();
            ipc.setIpcId(videoId);
            ipc.setIpcIp(videoIp);
            ipc.setIpcName(videoName);
//            ipc.setIpcposition(videoPosition);
//            ipc.setIpcFps(videoFps);
            ipc.setIpcBrand(videoBrand);
            ipc.setState(2);
            ipcConfigService.saveOrUpdateByMultiId(ipc);
            EventEntity event = eventService.selectByEventId(AIeventId);
            ChannelEntity channelEntity = new ChannelEntity();
            channelEntity.setBoxId(boxId);
            channelEntity.setChannelId(channelId);
            channelEntity.setChannelName(channelName);
            channelEntity.setVideoFps(videoFps);
            channelEntity.setVideoId(videoId);
            channelEntity.setEventId(AIeventId);
            channelEntity.setEventName(event.getAIeventName());
            channelEntity.setVideoPort(videoPort);
            channelEntity.setVideoStream(videoStream);
            channelService.saveOrUpdateByMultiId(channelEntity);

            List<JSONObject> area_list = (List<JSONObject>)channel.get("detect_area_list");
            for(JSONObject area:area_list){
                Integer areaId = (Integer) area.get("area_id");
                String detectTimeList = JSON.toJSONString(area.get("detect_time_List"));
                String areaName = (String)area.get("area_name");
                Integer AIeventLevel = (Integer) area.get("AIevent_level");
                Integer AIeventProcessMode = (Integer) area.get("AIevent_processmode");
                String AIeventProcessParam = JSON.toJSONString(area.get("AIevent_processparam"));
                String detectAreaScope = JSON.toJSONString(area.get("detect_area_scope")) ;
                AreaEntity areaEntity = new AreaEntity();
                areaEntity.setAreaId(areaId);
                areaEntity.setChannelId(channelId);
                areaEntity.setBoxId(boxId);
                areaEntity.setAreaName(areaName);
                areaEntity.setIpcId(videoId);
                areaEntity.setAIeventLevel(AIeventLevel);
                areaEntity.setDetectAreaScope(detectAreaScope);
                areaEntity.setAIeventProcessmode(AIeventProcessMode);
                areaEntity.setAIeventProcessparam(AIeventProcessParam);
                areaEntity.setDetectTimeList(detectTimeList);
                areaService.saveOrUpdateByMultiId(areaEntity);
            }

    }
        return r;
    }

    @ApiOperation("更新模型")
    @RequestMapping("/UpdateModel")
    public ResponseEntity<Resource> getBoxModel(@RequestParam(value = "box_id")String boxId,
    @RequestParam(value = "current_version")String currentVersion,
    HttpServletResponse response) throws Exception {
        BoxModelEntity boxModel =boxModelService.getOne(boxId);

        if(!Objects.equals(currentVersion, boxModel.getCorrectVersion())){
            ModelInformationEntity model = modelInformationService.getOne(boxModel.getModelId(),boxModel.getCorrectVersion());
            try{
                String path = model.getModelFile();
                // path是指想要下载的文件的路径
                File file = new File(path);
                Resource resource = new FileSystemResource(file);

                // 设置响应头
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                // 告知浏览器文件的大小
                headers.add("Content-Length", "" + file.length());
                headers.add("Access-Control-Expose-Headers","Model_Version");
                headers.add("Model_Version",boxModel.getCorrectVersion());
                // 使用流式传输下载文件
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        else{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Expose-Headers","Model_Version");
            headers.add("Model_Version","modelAlreadyNewest!");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(null);
        }
    }

    @ApiOperation("更新代码")
    @RequestMapping("/updateCode")
    public ResponseEntity<Resource> updateCode(@RequestParam(value = "box_id")String boxId,
            HttpServletResponse response) throws Exception {
        BoxModelEntity boxModel =boxModelService.getOne(boxId);

        if(boxModel.getCodeVersion() == 1){
            try{
                String path = codeFile;
                // path是指想要下载的文件的路径
                File file = new File(path);
                Resource resource = new FileSystemResource(file);

                // 设置响应头
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                // 告知浏览器文件的大小
                headers.add("Content-Length", "" + file.length());
                // 使用流式传输下载文件
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
        else{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Expose-Headers","Code_Version");
            headers.add("Code_Version","codeAlreadyNewest!");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(null);
        }
    }

    @ApiOperation("更新代码确认")
    @GetMapping("/updateCodeConfirm")
    public R updateCodeConfirm(@RequestParam(value = "box_id")String boxId) throws Exception {
        R r = R.ok();
        boxModelService.setCodeVersion(boxId);
        return r;
    }

    @ApiOperation("确认模型版本")
    @GetMapping("/confirmBoxModelVersion")
    public R confirmBoxVersion(@RequestParam(value = "box_id")String boxId,
                               @RequestParam(value = "current_version")String currentVersion)
    {
        R r = R.ok();
        boxModelService.confirmBoxModelVersion(boxId,currentVersion);
        r.addData("result","success");
        return r;
    }

    @ApiOperation("获取处理参数")
    @GetMapping("/getProcessParam")
    public R getProcessParam(){
        R r = R.ok();
        List<ProcessParam> result = processParamService.getAll();
        r.addData("result",result);
        return r;

    }
}
