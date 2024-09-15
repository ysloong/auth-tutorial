

-- 用户登录信息表
CREATE TABLE user_login_info (
                                 id SERIAL PRIMARY KEY,  -- 自增ID
                                 user_code VARCHAR(20) UNIQUE NOT NULL,  -- 用户编号，唯一
                                 email VARCHAR(255) UNIQUE NOT NULL,  -- 邮箱
                                 phone_number VARCHAR(20),  -- 手机号
                                 password_hash VARCHAR(255) NOT NULL,  -- 密码（加密存储）
                                 active_status INTEGER NOT NULL,  -- 是否启用
                                 created_at TIMESTAMP NOT NULL,  -- 创建时间
                                 updated_at TIMESTAMP NOT NULL,  -- 更新时间
                                 delete_status INTEGER NOT NULL  -- 删除状态，默认为0，0表示未删除，1表示已删除
);

COMMENT ON COLUMN user_login_info.id IS '自增ID';
COMMENT ON COLUMN user_login_info.user_code IS '用户编号，唯一';
COMMENT ON COLUMN user_login_info.email IS '邮箱';
COMMENT ON COLUMN user_login_info.phone_number IS '手机号';
COMMENT ON COLUMN user_login_info.password_hash IS '密码（加密存储）';
COMMENT ON COLUMN user_login_info.active_status IS '是否启用';
COMMENT ON COLUMN user_login_info.created_at IS '创建时间';
COMMENT ON COLUMN user_login_info.updated_at IS '更新时间';
COMMENT ON COLUMN user_login_info.delete_status IS '删除状态，默认为0，0表示未删除，1表示已删除';


-- 用户基础信息表
CREATE TABLE user_base_info (
                                id SERIAL PRIMARY KEY,
                                user_code VARCHAR(20) UNIQUE NOT NULL,
                                email VARCHAR(255) UNIQUE NOT NULL,  -- 邮箱
                                phone_number VARCHAR(20),  -- 手机号
                                first_name VARCHAR(50),
                                last_name VARCHAR(50),
                                birthdate DATE,
                                address TEXT,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP NOT NULL,
                                delete_status INTEGER NOT NULL  -- 删除状态，默认为0，0表示未删除，1表示已删除
);

COMMENT ON COLUMN user_base_info.id IS '自增ID';
COMMENT ON COLUMN user_base_info.user_code IS '用户编号，外键关联 user_login_info';
COMMENT ON COLUMN user_base_info.email IS '邮箱';
COMMENT ON COLUMN user_base_info.phone_number IS '手机号';
COMMENT ON COLUMN user_base_info.first_name IS '名字';
COMMENT ON COLUMN user_base_info.last_name IS '姓氏';
COMMENT ON COLUMN user_base_info.birthdate IS '生日';
COMMENT ON COLUMN user_base_info.address IS '地址';
COMMENT ON COLUMN user_base_info.created_at IS '创建时间';
COMMENT ON COLUMN user_base_info.updated_at IS '更新时间';
COMMENT ON COLUMN user_base_info.delete_status IS '删除状态，默认为0，0表示未删除，1表示已删除';