--jdbc:postgresql://localhost:5432/auth-tutorial?currentSchema=auth-base&useSSL=false&stringtype=unspecified


drop table if exists base_sequence_management;

-- 创建序列管理表
CREATE TABLE base_sequence_management (
                                          id SERIAL PRIMARY KEY,
                                          business_type VARCHAR(50) NOT NULL UNIQUE,
                                          current_value BIGINT NOT NULL,
                                          prefix VARCHAR(10) NOT NULL,
                                          step INTEGER NOT NULL ,
                                          length INTEGER NOT NULL,
                                          created_at TIMESTAMP NOT NULL,
                                          updated_at TIMESTAMP NOT NULL,
                                          delete_status INTEGER NOT NULL

);

-- 添加表注释
COMMENT ON TABLE base_sequence_management IS '序列管理表，用于记录不同业务类型的序列号及其相关信息';

-- 添加字段注释
COMMENT ON COLUMN base_sequence_management.id IS '主键，自增';
COMMENT ON COLUMN base_sequence_management.business_type IS '业务类型，唯一约束';
COMMENT ON COLUMN base_sequence_management.current_value IS '当前值';
COMMENT ON COLUMN base_sequence_management.prefix IS '前缀';
COMMENT ON COLUMN base_sequence_management.step IS '步长，默认为1';
COMMENT ON COLUMN base_sequence_management.length IS '序列长度';
COMMENT ON COLUMN base_sequence_management.created_at IS '创建时间';
COMMENT ON COLUMN base_sequence_management.updated_at IS '更新时间';
COMMENT ON COLUMN base_sequence_management.delete_status IS '删除状态，默认为0，0表示未删除，1表示已删除';


