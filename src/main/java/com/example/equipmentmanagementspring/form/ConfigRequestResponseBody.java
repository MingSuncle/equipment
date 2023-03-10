package com.example.equipmentmanagementspring.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder

public class ConfigRequestResponseBody {
  private String center_project;
  private List<String> center_third_party_url_list;
  private String box_center_ip;
  private String box_center_id;
  private String box_ip;
  private String box_id;
  private String box_name;
  private List<String> box_ai_event_list;
  private List<Map<String, Object>> box_video_information_list;

  @Data
  @Builder

  public static class video_ai_event{
    @JsonProperty("AIevent_level")
    private Integer AIevent_level;

    @JsonProperty("AIevent_processmode")
    private Integer AIevent_processmode;

    @JsonProperty("AIevent_processparam")
    private String AIevent_processparam;

    @JsonProperty("AIevent_confidence")
    private Double AIevent_confidence;

    @JsonProperty("AIevent_iou")
    private Double AIevent_iou;
  }

  @Data
  @Builder
  public static class video_detection_area{
    private Integer x;
    private Integer y;
  }
}
