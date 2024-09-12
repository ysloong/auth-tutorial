package com.ysl.auth.base.service;

import com.ysl.auth.base.entity.BaseSequenceManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ysl.auth.common.dto.BaseSequenceManagementDto;

/**
 * <p>
 * 序列管理表，用于记录不同业务类型的序列号及其相关信息 服务类
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
public interface IBaseSequenceManagementService extends IService<BaseSequenceManagement> {

    void add(BaseSequenceManagementDto dto);

    BaseSequenceManagementDto getByBusinessType(String businessType);

    String generateCode(String businessType);
}
