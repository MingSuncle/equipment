package com.example.equipmentmanagementspring.form;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ipcResponse {
    private List<eventResponse> video_ai_event_list;
}
