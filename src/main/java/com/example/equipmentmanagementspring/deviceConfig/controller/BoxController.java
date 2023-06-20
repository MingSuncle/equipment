package com.example.equipmentmanagementspring.deviceConfig.controller;


import com.example.equipmentmanagementspring.deviceConfig.form.BoxConfiguration;
import com.example.equipmentmanagementspring.service.HeartDetectService;
import com.example.equipmentmanagementspring.utils.R;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(tags = "盒子配置新")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/boxConfig")
public class BoxController {
    private final HeartDetectService heartDetectService;

    public BoxController(HeartDetectService heartDetectService) {
        this.heartDetectService = heartDetectService;
    }

    @ApiOperation("获取盒子")
    @GetMapping("/getBox")
    public R getArea() throws IOException {
        R r = R.ok();
        File file = ResourceUtils.getFile("D:/Zhaozian/Project/AI/test.json");
        ObjectMapper mapper = new ObjectMapper();
        BoxConfiguration box = mapper.readValue(file, BoxConfiguration.class);
//        Map<String,String> result = new HashMap<>();
//        result.put("box_id",box.getBox_id());
//        result.put("box_ip", box.getBox_ip());
//        result.put("box_name",box.getBox_name());
//        String jsonString = mapper.writeValueAsString(box.getThird_party_url_list());
//        result.put("third_party_url_list",jsonString);
//        mapper.writeValue(new File("test.json"),box);
        r.addData("result",box);
        return r;
    }

    @ApiOperation("获取摄像头")
    @GetMapping("/getChannel")
    public R getIpc(@RequestParam(value = "box_id")String boxId,
                    @RequestBody BoxConfiguration.Channel channel)
            throws IOException{
        R r = R.ok();
        File file = ResourceUtils.getFile("D:/Zhaozian/Project/AI/test.json");
        ObjectMapper mapper = new ObjectMapper();
        BoxConfiguration box = mapper.readValue(file, BoxConfiguration.class);
        List<BoxConfiguration.Channel> channelList = box.getChannel_list();
        channelList.add(channel);
        mapper.writeValue(new File("test2.json"),box);
        r.addData("result",channelList);
        return r;
    }

    @ApiOperation("盒子在线状态")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @GetMapping("/getBoxStatus")
    public R getBoxStatus(@RequestParam(value = "box_id")String boxId){
        R r = R.ok();

        Date date = heartDetectService.getLastDate(boxId);
        Calendar now = Calendar.getInstance(); // 获取当前时间的日历对象
        Calendar target = Calendar.getInstance();
        target.setTime(date); // 设置目标日期

        // 将目标日期的时、分、秒和毫秒都设置为当前时间的时、分、秒和毫秒，以便比较仅限于日期部分
        target.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
        target.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
        target.set(Calendar.SECOND, now.get(Calendar.SECOND));
        target.set(Calendar.MILLISECOND, now.get(Calendar.MILLISECOND));

        //判断距今天数，小于一天返回0，否则返回大于等于1的数字
        long diffInMillis = now.getTimeInMillis() - target.getTimeInMillis();
        long diffInDays = diffInMillis / (24 * 60 * 60 * 1000);

        LocalDateTime temp = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = temp.format(formatter);

        String result = "";
        if(diffInDays == 0)
        {
            result = "running";
        }
        else{
            result = "dead";
        }
        r.addData("status",result);
        r.addData("date",formattedDate);
        return r ;
    }
}
