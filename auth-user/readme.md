# auth-user


### 表设计
* 用户登录表
* 用户信息表



CREATE TABLE auth_user (
id SERIAL PRIMARY KEY,
username VARCHAR(64),
nickname VARCHAR(255),
avatar_url TEXT,
created_at TIMESTAMP ,
updated_at TIMESTAMP,
delete_status INTEGER 
);

COMMENT ON TABLE auth_user IS '用户表';
COMMENT ON COLUMN auth_user.id IS '主键';
COMMENT ON COLUMN auth_user.username IS '用户名';
COMMENT ON COLUMN auth_user.nickname IS '昵称';
COMMENT ON COLUMN auth_user.avatar_url IS '头像URL';
COMMENT ON COLUMN auth_user.created_at IS '创建时间';
COMMENT ON COLUMN auth_user.updated_at IS '更新时间';
COMMENT ON COLUMN auth_user.delete_status IS '删除状态 0表示未删除，1表示已删除';



CREATE TABLE auth_user_credential (
id SERIAL PRIMARY KEY,
username VARCHAR(64),
credential_type VARCHAR(50),
identifier VARCHAR(255),
credential TEXT,
created_at TIMESTAMP ,
updated_at TIMESTAMP ,
delete_status INTEGER
);

COMMENT ON TABLE auth_user_credential IS '用户凭证表';
COMMENT ON COLUMN auth_user_credential.id IS '自增主键';
COMMENT ON COLUMN auth_user_credential.username IS '外键, 关联到auth_user表的username';
COMMENT ON COLUMN auth_user_credential.credential_type IS '凭证类型，例如EMAIL, PHONE, WECHAT, GOOGLE，字符串类型';
COMMENT ON COLUMN auth_user_credential.identifier IS '凭证标识符，例如邮箱地址、手机号、第三方平台的唯一标识符';
COMMENT ON COLUMN auth_user_credential.credential IS '凭证，对于邮箱和手机号可能是密码的哈希值，对于第三方登录则为空或存储access token等信息';
COMMENT ON COLUMN auth_user_credential.created_at IS '创建时间';
COMMENT ON COLUMN auth_user_credential.updated_at IS '更新时间';
COMMENT ON COLUMN auth_user_credential.delete_status IS '删除状态，0表示未删除，1表示已删除';
