package com.example.equipmentmanagementspring.mapper;


import com.example.equipmentmanagementspring.form.ChartForm;
import com.example.equipmentmanagementspring.form.EventForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface ChartsDao {

    /**
     * 饼图
     * @author Zhao Zian
     */
    List<ChartForm> CountSum(String userId);

    /**
     * IPC折线图
     * @author Zhao Zian
     */
    List<ChartForm> IpcData(String userId);

    /**
     * 今日数据折线图
     * @author Zhao Zian
     */
    List<ChartForm> todayData(String userId,String eventId);

    /**
     * 按日统计折线图
     * @author Zhao Zian
     */
    List<ChartForm> dataByDay(String userId,String eventId);

    /**
     * 上线算法数
     * @author Zhao Zian
     */
    Integer OnlineAlgorithm();

    List<EventForm> getEvent();


    /**
     * 报警总数
     * @author Zhao Zian
     */
    Integer totalNum(String userId);

    /**
     * 报警最多
     * @author Zhao Zian
     */
    String MostAlarmed(String userId);

    /**
     * 今日报警
     * @author Zhao Zian
     */
    Integer todayAlarmed(String userId);
}
