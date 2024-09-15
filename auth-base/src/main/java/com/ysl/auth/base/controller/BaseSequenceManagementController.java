package com.ysl.auth.base.controller;


import com.ysl.auth.base.service.IBaseSequenceManagementService;
import com.ysl.auth.common.dto.BaseSequenceManagementDto;
import com.ysl.auth.common.message.BaseResMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 序列管理表，用于记录不同业务类型的序列号及其相关信息 前端控制器
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@Slf4j
@RestController
public class BaseSequenceManagementController {

    @Resource
    private IBaseSequenceManagementService baseSequenceManagementService;

    @PostMapping(value = "/baseSequenceManagement/add")
    public BaseResMessage<Object> add(@RequestBody BaseSequenceManagementDto dto) {
        baseSequenceManagementService.add(dto);
        return BaseResMessage.success();
    }


    @GetMapping(value = "/baseSequenceManagement/get")
    public BaseResMessage<Object> get(@RequestParam String businessType) {
        return BaseResMessage.success(baseSequenceManagementService.getByBusinessType(businessType));
    }

    @GetMapping(value = "/baseSequenceManagement/generateCode")
    public BaseResMessage<String> generateCode(@RequestParam String businessType) {
        return BaseResMessage.success(baseSequenceManagementService.generateCode(businessType));
    }
}
