package com.example.equipmentmanagementspring.deviceConfig.controller;


import com.example.equipmentmanagementspring.deviceConfig.entity.AreaEntity;
import com.example.equipmentmanagementspring.deviceConfig.service.AreaService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "检测区域相关")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/area")
public class AreaController {
    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @ApiOperation("获取区域")
    @GetMapping("/getArea")
    public R getArea(@RequestParam(value = "box_id")String boxId,
                     @RequestParam(value = "ipc_id")String ipcId,
                     @RequestParam(value = "channel_id") Integer channelId){
        R r = R.ok();
        List<AreaEntity> result = areaService.getArea(boxId, ipcId, channelId);
        Integer total = areaService.getAreaNum(boxId, ipcId, channelId);
        r.addData("result",result);
        r.addData("total",total);
        return r;
    }
}
