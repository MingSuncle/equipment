package com.example.equipmentmanagementspring.deviceConfig.form;

import lombok.Data;

import java.util.List;

@Data
public class Channel {
    private Integer channel_id;
    private String channel_name;
    private String video_id;
    private String video_name;
    private String video_brand;
    private String video_ip;
    private String video_username;
    private String video_password;
    private String video_port;
    private Integer video_fps;
    private String video_stream;
    private String video_url;
    private Integer state;
    private List<DetectArea> detect_area_list;
    private String AIevent_id;
}
