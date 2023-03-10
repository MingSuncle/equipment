package com.example.equipmentmanagementspring.form;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class eventResponse {

    private Integer AIevent_level;
    private Integer AIevent_processmode;
    private String AIevent_processparam;
    private Double AIevent_confidence;
    private Double AIevent_iou;
}
