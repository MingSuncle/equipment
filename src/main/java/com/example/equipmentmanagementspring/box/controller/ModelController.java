package com.example.equipmentmanagementspring.box.controller;


import com.example.equipmentmanagementspring.box.dao.ModelInformationDao;
import com.example.equipmentmanagementspring.box.entity.ModelInformationEntity;
import com.example.equipmentmanagementspring.box.service.ModelInformationService;
import com.example.equipmentmanagementspring.utils.R;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "模型")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/model")
public class ModelController {
    private final ModelInformationService modelInformationService;
    private final ModelInformationDao modelInformationDao;

    public ModelController(ModelInformationService modelInformationService, ModelInformationDao modelInformationDao) {
        this.modelInformationService = modelInformationService;
        this.modelInformationDao = modelInformationDao;
    }

    @ApiOperation("增/改")
    @PostMapping("/modifyModel")
    public R modifyModel(@RequestBody ModelInformationEntity modelInformationEntity){
        R r = R.ok();
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
        modelInformationService.deleteByMultiId(model);
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
}
