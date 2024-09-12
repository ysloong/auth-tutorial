package com.ysl.auth.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysl.auth.base.entity.BaseSequenceManagement;
import com.ysl.auth.base.mapper.BaseSequenceManagementMapper;
import com.ysl.auth.base.service.IBaseSequenceManagementService;
import com.ysl.auth.common.dto.BaseSequenceManagementDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 序列管理表，用于记录不同业务类型的序列号及其相关信息 服务实现类
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@Service
public class BaseSequenceManagementServiceImpl extends ServiceImpl<BaseSequenceManagementMapper, BaseSequenceManagement> implements IBaseSequenceManagementService {
    @Override
    public void add(BaseSequenceManagementDto dto) {
        BaseSequenceManagement entity = new BaseSequenceManagement();
        BeanUtils.copyProperties(dto, entity);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        this.save(entity);
    }

    @Override
    public BaseSequenceManagementDto getByBusinessType(String businessType) {
        BaseSequenceManagementDto baseSequenceManagementDto = new BaseSequenceManagementDto();
        QueryWrapper<BaseSequenceManagement> wrapper = new QueryWrapper<>();
        wrapper.eq("business_type", businessType);
        BaseSequenceManagement baseSequenceManagement = this.getOne(wrapper);
        BeanUtils.copyProperties(baseSequenceManagement, baseSequenceManagementDto);
        return baseSequenceManagementDto;
    }

    @Transactional
    @Override
    public String generateCode(String businessType) {
        LambdaUpdateWrapper<BaseSequenceManagement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(BaseSequenceManagement::getBusinessType, businessType)
                .setSql("current_value = current_value + step")
                .setSql("updated_at = NOW()");

        boolean updated = this.update(updateWrapper);
        if (!updated) {
            throw new RuntimeException("Failed to update sequence for business type: " + businessType);
        }

        BaseSequenceManagement sequence = this.lambdaQuery()
                .eq(BaseSequenceManagement::getBusinessType, businessType)
                .one();

        if (sequence == null) {
            throw new RuntimeException("Sequence not found for business type: " + businessType);
        }

        String numberPart = String.format("%0" + (sequence.getLength() - sequence.getPrefix().length()) + "d", sequence.getCurrentValue());
        return sequence.getPrefix() + numberPart;
    }

}
