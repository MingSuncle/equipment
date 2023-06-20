package com.example.equipmentmanagementspring.deviceConfig.form;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DetectArea {
    private String area_name;
    private List<List<String>> detect_time_List;
    private List<List<Double>> detect_area_scope;
    @JsonProperty("AIevent_level")
    private Integer AIevent_level;
    @JsonProperty("AIevent_processmode")
    private Integer AIevent_processmode;
    @JsonProperty("AIevent_processparam")
    private Map<String, String> AIevent_processparam;
}
