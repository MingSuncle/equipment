package com.example.equipmentmanagementspring.deviceConfig.controller;

import com.example.equipmentmanagementspring.deviceConfig.entity.ChannelEntity;
import com.example.equipmentmanagementspring.deviceConfig.service.ChannelService;
import com.example.equipmentmanagementspring.deviceConfig.service.EventService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "通道相关")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelService channelService;

    private final EventService eventService;

    public ChannelController(ChannelService channelService, EventService eventService) {
        this.channelService = channelService;
        this.eventService = eventService;
    }

    @ApiOperation("获取通道")
    @GetMapping("getChannel")
    public R getChannelByIpcId(@RequestParam(value = "ipc_id")String ipcId,
                               @RequestParam(value = "box_id")String boxId){
        R r =R.ok();
        List<ChannelEntity> result = channelService.getChannel(ipcId,boxId);
        Integer total = channelService.getChannelNum(ipcId,boxId);
        r.addData("result",result);
        r.addData("total",total);
        return r;
    }
}
