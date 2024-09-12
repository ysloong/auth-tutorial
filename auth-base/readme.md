# auth-base 服务说明文档



## 生产业务编码

### 方案1 使用Java业务代码方式实现序列管理
### 方案2 使用数据库函数方式实现序列管理（废弃，可移植性差，数据库依赖）
~~~sql
-- 创建序列管理表
CREATE OR REPLACE FUNCTION generate_business_code(p_business_type VARCHAR)
    RETURNS VARCHAR AS $$
DECLARE
    v_current_value BIGINT;
    v_prefix VARCHAR;
    v_length INTEGER;
    v_code VARCHAR;
BEGIN
    -- 更新当前值并获取必要信息
    UPDATE base_sequence_management
    SET current_value = current_value + step,
        updated_at = CURRENT_TIMESTAMP
    WHERE business_type = p_business_type
    RETURNING current_value, prefix, length
        INTO v_current_value, v_prefix, v_length;

    -- 如果没有找到匹配的业务类型，抛出异常
    IF v_current_value IS NULL THEN
        RAISE EXCEPTION 'Business type % not found', p_business_type;
    END IF;

    -- 生成编码
    v_code := v_prefix || LPAD(v_current_value::TEXT, v_length - LENGTH(v_prefix), '0');

    RETURN v_code;
END;
$$ LANGUAGE plpgsql;
~~~

### 接口1
### 接口2

## 模块2

