package com.example.equipmentmanagementspring.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ipc_config")
public class IpcConfigEntity implements Serializable{

        private static final long serialVersionUID = 1L;

        @MppMultiId
        @TableField(value = "ipc_id")
        private String ipcId;

        @TableField(value = "ipc_name")
        private String ipcName;

        @TableField(value = "ipc_ip")
        private String ipcIp;

        @TableField(value = "ipc_position")
        private String ipcposition;

        @TableField(value = "ipc_box_no")
        private String ipcBoxNo;

        @TableField(value = "ipc_url")
        private String ipcUrl;

        @TableField(value = "ipc_fps")
        private Double ipcFps;

        @MppMultiId
        @TableField(value = "state")
        private Integer state;

        @TableField(value = "ipc_detect_area")
        private String ipcDetectArea;

        @TableField(value = "ipc_detect_time_bgn")
        private String ipcDetectTimeBgn;

        @TableField(value = "ipc_detect_time_end")
        private String ipcDetectTimeEnd;

        @TableField(value = "ipc_map_position")
        private String ipcMapPosition;

        @TableField(value = "ipc_detect_time_v2")
        private String ipcTime;


    }
