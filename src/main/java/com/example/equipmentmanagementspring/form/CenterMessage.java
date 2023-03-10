package com.example.equipmentmanagementspring.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data

public class CenterMessage {

    @NotNull(message = "必填 0-图片 1-视频")
    private Integer file_type;


    @NotNull(message = "必填")
    private String file_name;

    @NotNull(message = "必填，项目编码")
    private String center_id;

    @NotNull(message = "必填，AI盒子编号")
    private String box_id;

    @NotNull(message = "必填，摄像头编号")
    private String video_id;

    @NotNull(message = "必填，上传时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String time;

    @NotNull(message = "必填，事件类型")
    private String event_type;

    @NotNull(message = "必填，项目编码")
    private String center_project;

    @NotNull(message = "必填，事件描述")
    private String description;



//    @ApiModelProperty(value = "file", name = "图片二进制流")
//    @NotNull(message = "文件")
//    private String file;

}
