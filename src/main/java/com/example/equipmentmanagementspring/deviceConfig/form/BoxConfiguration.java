package com.example.equipmentmanagementspring.deviceConfig.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BoxConfiguration {
    private Integer channelLimit;
    private Map<String, String> eventList;
    private String expiration_date;
    private Map<String, String> third_party_url_list;
    private String box_id;
    private String project_id;
    private String box_ip;
    private String box_name;
    private List<Channel> channel_list;
    private Integer isModified;
    @JsonProperty("aibox_version")
    private String box_version;

    // getters and setters

    @Data
    public static class Channel {
        @JsonProperty("AIevent_id")
        private Integer AIevent_id;
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

        // getters and setters

        @Data
        public static class DetectArea {
            private String area_name;
            @JsonProperty("AIevent_level")
            private Integer AIevent_level;
            @JsonProperty("AIevent_processmode")
            private Integer AIevent_processmode;
            @JsonProperty("AIevent_processparam")
            private Map<String, String> AIevent_processparam;
            private List<List<String>> detect_time_List;
            private List<List<Double>> detect_area_scope;

            // getters and setters
        }
    }
}
