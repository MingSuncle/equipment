package com.example.equipmentmanagementspring.controller;


import com.example.equipmentmanagementspring.form.ChartForm;
import com.example.equipmentmanagementspring.form.EventForm;
import com.example.equipmentmanagementspring.mapper.ChartsDao;
import com.example.equipmentmanagementspring.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/charts")
public class ChartsController {
    private final ChartsDao chartsDao;

    public ChartsController(ChartsDao chartsDao) {
        this.chartsDao = chartsDao;
    }

    /**
     * 饼状图所需消息
     * 各类报警事件数量
     * @author Zhao Zian
     */
    @GetMapping("/getEvent")
    public R getEvent(){
        R r = R.ok();
        List<EventForm> result = chartsDao.getEvent();
        r.addData("result",result);
        return r;
    }


    @GetMapping("/total")
    public R totalSum(@RequestParam(value = "userId") String userId){
        R r = R.ok();
        List<ChartForm> result = chartsDao.CountSum(userId);
        r.addData("result",result);
        return r;
    }


    /**
     * IPC折线图所需信息
     * @author Zhao Zian
     */
    @GetMapping("/IpcData")
    public R IpcData(@RequestParam(value = "userId") String userId){
        R r = R.ok();
        List<ChartForm> temp = chartsDao.IpcData(userId);
        List<String> xAxis = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            xAxis.add(temp.get(i).getName());
            data.add(temp.get(i).getValue());
        }
        r.addData("xAxis",xAxis);
        r.addData("data",data);
        return r;
    }
    /**
     * 仪表盘所需消息
     *报警总数、报警最多事件名、今日报警、上线算法
     * @author Zhao Zian
     */
    @GetMapping("/dashboardData")
    public R dashboardData( @RequestParam(value = "userId") String userId){
        R r = R.ok();
        Integer OnlineAlgorithm = this.chartsDao.OnlineAlgorithm();
        Integer totalNum = this.chartsDao.totalNum(userId);
        String MostAlarmed = this.chartsDao.MostAlarmed(userId);
        Integer todayAlarmed = this.chartsDao.todayAlarmed(userId);
        r.addData("totalNum",totalNum);
        r.addData("OnlineAlgorithm",OnlineAlgorithm);
        r.addData("MostAlarmed",MostAlarmed);
        r.addData("todayAlarmed",todayAlarmed);
        return r;
    }

    /**
     * 今日数据折线图
     * @author Zhao Zian
     */
    @GetMapping("/todayData")
    public R todayData(@RequestParam(value = "userId") String userId,
                       @RequestParam(value = "AIevent_id") String AIevent_id){
        R r = R.ok();
        List<ChartForm> temp = chartsDao.todayData(userId,AIevent_id);
        List<String> xAxis = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        for(int i=0;i<temp.size();i++){
            xAxis.add(temp.get(i).getName());
            data.add(temp.get(i).getValue());
        }
        r.addData("xAxis",xAxis);
        r.addData("data",data);
        return r;

    }

    /**
     * 数据按天折线图
     * @author Zhao Zian
     */
    @GetMapping("/dataByDay")
    public R dataByDay(@RequestParam(value = "userId") String userId,
                       @RequestParam(value = "AIevent_id") String AIevent_id){
        R r = R.ok();
        List<ChartForm> temp = chartsDao.dataByDay(userId,AIevent_id);
        
        r.addData("data",temp);
        return r;

    }

}
