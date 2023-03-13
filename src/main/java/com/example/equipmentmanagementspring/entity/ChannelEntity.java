package com.example.equipmentmanagementspring.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("channel_config")
public class ChannelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField(value = "channel_id")
    private String channelId;

    @MppMultiId
    @TableField(value = "box_id")
    private String boxId;

    @MppMultiId
    @TableField(value = "video_id")
    private String videoId;

    @TableField(value = "channel_name")
    private String channelName;

    @TableField(value = "event_id")
    private Integer eventId;

    @TableField(value = "video_fps")
    private Double videoFps;

    @TableField(value = "video_port")
    private String videoPort;

    @TableField(value = "video_stream")
    private String videoStream;
}
