package com.example.equipmentmanagementspring.form;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConfigResponse {
    private List<String> center_third_party_url_list;
    private String center_ip;
    private String center_id;
    private String center_project;
    private List<ConfigRequestResponseBody> center_manager_box_list;

}
