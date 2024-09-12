package com.ysl.auth.user.feign;

import com.ysl.auth.common.exceptions.SystemConstants;
import com.ysl.auth.common.message.BaseResMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * auth-base 服务
 * @author long
 */
@FeignClient(name = "auth-base", fallback = IServiceBaseFeign.HystrixClientFallback.class)
public interface IServiceBaseFeign {


    /**
     * 生成编码
     *
     * @param businessType 业务类型
     * @return 编码
     */
    @GetMapping(value = "/baseSequenceManagement/generateCode")
    BaseResMessage<String> generateCode(@RequestParam String businessType);

    @Component
    class HystrixClientFallback implements IServiceBaseFeign {
        @Override
        public BaseResMessage<String> generateCode(String businessType) {
            return new BaseResMessage<>(SystemConstants.HYSTRIX_RES_CODE, SystemConstants.HYSTRIX_RES_MESSAGE);
        }
    }
}
