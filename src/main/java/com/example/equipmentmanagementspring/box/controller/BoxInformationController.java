package com.example.equipmentmanagementspring.box.controller;


import com.example.equipmentmanagementspring.box.entity.BoxInformationEntity;
import com.example.equipmentmanagementspring.box.service.BoxInformationService;
import com.example.equipmentmanagementspring.box.utils.FileDes;
import com.example.equipmentmanagementspring.service.BoxConfigService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


@Api(tags = "盒子")
@ApiSupport(order = 10)
@RestController
@RequestMapping("/box")
@EnableMPP
public class BoxInformationController {
    private final BoxInformationService boxInformationService;
    private final BoxConfigService boxConfigService;

    public BoxInformationController(BoxInformationService boxInformationService, BoxConfigService boxConfigService) {
        this.boxInformationService = boxInformationService;
        this.boxConfigService = boxConfigService;
    }

    @ApiOperation("激活盒子")
    @PostMapping ("/registerBox")
    public R activateBox(@RequestParam(value = "box_id") String boxId) {
        R r = R.ok();
        Integer result = boxInformationService.activateBox(boxId);
        if (result == 404) {
            r = R.error(404, "Not Found");
        }
        else{
            BoxInformationEntity box = boxInformationService.getById(boxId);
            r.addData("channelLimit",box.getChannelNumberLimit());
            r.addData("eventList",box.getAIeventLimit());
            return r;
        }
        return r;
    }

    @ApiOperation("下发加密模型")
    @RequestMapping("/pushModel")
    public String pushModel(@RequestParam(value = "box_id")String boxId,
                       HttpServletResponse response) throws Exception {
        //算法加密
        BoxInformationEntity box = boxInformationService.getById(boxId);
        FileDes fileDes = new FileDes(boxId);
        fileDes.decrypt("D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书.doc", "D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书dec.doc");

        try {
            String path = "D:\\Zhaozian\\Project\\equipment\\src\\main\\数据库实体设计说明书dec.doc";
//            String path = "D:/学习笔记/研究生/antiepidemic/src/excelTemplate/staffTemplate.xlsx";
            // path是指想要下载的文件的路径
            File file = new File(path);
            System.out.println(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            System.out.println("文件后缀名：" + ext);

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            return "success";
        } catch (IOException ex) {
            ex.printStackTrace();
            return "fail";
        }
    }

}
