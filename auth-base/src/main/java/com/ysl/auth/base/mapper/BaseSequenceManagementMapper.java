package com.ysl.auth.base.mapper;

import com.ysl.auth.base.entity.BaseSequenceManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 序列管理表，用于记录不同业务类型的序列号及其相关信息 Mapper 接口
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@Mapper
public interface BaseSequenceManagementMapper extends BaseMapper<BaseSequenceManagement> {

}
