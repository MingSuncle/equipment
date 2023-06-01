package com.example.equipmentmanagementspring.deviceConfig.controller;


import com.example.equipmentmanagementspring.deviceConfig.dao.ModelInformationDao;
import com.example.equipmentmanagementspring.deviceConfig.entity.ModelInformationEntity;
import com.example.equipmentmanagementspring.deviceConfig.form.ModelForm;
import com.example.equipmentmanagementspring.deviceConfig.service.ModelInformationService;
import com.example.equipmentmanagementspring.deviceConfig.utils.FileIO;
import com.example.equipmentmanagementspring.utils.R;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Api(tags = "模型")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/model")
public class ModelController {
    private final ModelInformationService modelInformationService;
    private final ModelInformationDao modelInformationDao;

    @Value("/root/modelFile/")
    private String modelExplore;

    public ModelController(ModelInformationService modelInformationService, ModelInformationDao modelInformationDao) {
        this.modelInformationService = modelInformationService;
        this.modelInformationDao = modelInformationDao;
    }

    @ApiOperation("增加模型")
    @PostMapping("/addModel")
    public R addModel(@RequestBody ModelInformationEntity modelInformationEntity){
        R r = R.ok();
        modelInformationEntity.setModelVersion("1.0.0");
        if(modelInformationService.isModelCreated(modelInformationEntity.getModelId())){
            return R.error(300,"已存在");
        }
        modelInformationService.saveOrUpdateByMultiId(modelInformationEntity);
        return r;
    }

    @ApiOperation("增加新版本")
    @PostMapping("/addVersion")
    public R addVersion(@RequestBody ModelInformationEntity modelInformationEntity) throws IOException {
        R r =R.ok();
        String modelVersion = modelInformationEntity.getModelVersion();
        String modelId = modelInformationEntity.getModelId();

        if(modelInformationService.isCreated(modelId,modelVersion)){
            return R.error(300,"已存在");
        }
        else{
            String modelPath = modelExplore+modelId+"/"+modelVersion;
            Path path = Paths.get(modelPath);
            Path pathCreate = Files.createDirectories(path);
            modelInformationService.saveOrUpdateByMultiId(modelInformationEntity);
            return r;
        }

    }

    @ApiOperation("修改模型")
    @PostMapping("/modifyModel")
    public R modifyModel(@RequestBody ModelInformationEntity modelInformationEntity){
        R r =R.ok();
        modelInformationService.saveOrUpdateByMultiId(modelInformationEntity);
        return r;
    }

    @ApiOperation("删")
    @GetMapping("/deleteModel")
    public R deleteModel(@RequestParam(value = "model_id") String modelId,
                         @RequestParam(value = "model_version")String modelVersion){
        R r = R.ok();
        ModelInformationEntity model = new ModelInformationEntity();
        model.setModelId(modelId);
        model.setModelVersion(modelVersion);
//        Path path = modelInformationService.getModelPath(modelId, modelVersion);
        String modelPath = modelExplore+modelId+"/"+modelVersion;
        Path path = Paths.get(modelPath);
        FileIO fileIO = new FileIO();
        try {
//            删除文件以及文件夹
            fileIO.DeleteFileDir(path);
            modelInformationService.deleteByMultiId(model);
        }catch (Exception e){
            String errorStr = "error: " + e.getStackTrace()[0].getClassName() + "." + e.getStackTrace()[0].getMethodName()
                    + "() line:" + e.getStackTrace()[0].getLineNumber() + "\n" + e.toString();
            System.out.println(errorStr);
            return R.error(errorStr);
        }
        return r;

    }


    @ApiOperation("查全部模型")
    @GetMapping("/getAllModel")
    public R getModel(){
        R r = R.ok();
        List<ModelInformationEntity> result = modelInformationDao.getAllModel();
        r.addData("result",result);
        return r;
    }

    @ApiOperation("按类别获取模型以及相应数量")
    @GetMapping("/getAllModelByType")
    public R getModelByType(){
        R r = R.ok();
        List<ModelForm> result = modelInformationDao.getModelByType();
        Integer total = modelInformationDao.getModelNumByType();
        r.addData("result",result);
        r.addData("total",total);
        return r;
    }

    @ApiOperation(("查看模型的不同版本"))
    @GetMapping("/getModelById")
    public R getModelById(@RequestParam(value = "model_id")String modelId){
        R r = R.ok();
        List<ModelInformationEntity> result = modelInformationService.getModelById(modelId);
        Integer total = modelInformationService.getModelNumById(modelId);
        r.addData("result",result);
        r.addData("total",total);
        return r;
    }
    @ApiOperation("上传模型文件")
    @PostMapping("/upload")
    @ApiOperationSupport(order = 20)
    public R uploadModel(@RequestParam("file")MultipartFile file,
                         @RequestParam("model_id")String modelId,
                         @RequestParam("model_version")String modelVersion) throws IOException {
        R r = R.ok();
        String modelPath = modelExplore+modelId+"/"+modelVersion;
        Path path = Paths.get(modelPath);
        Path pathCreate = Files.createDirectories(path);
        modelPath = modelPath+"/";
        try{
            FileIO fileIO = new FileIO();
            String result = fileIO.uploadFile(modelPath,file);
            modelInformationService.saveModelPath(modelId,modelVersion,result);
            return r;
        }catch (Exception e){
            e.printStackTrace();
            return R.error("fail");
        }

    }
}
