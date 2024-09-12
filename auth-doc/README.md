# auth-tutorial

## 需求
1 docker 部署微服务
2 jenkins 自动构建
3 gitlab 代码管理

### 技术架构
* jdk21
* spring boot 3.3.2
* spring cloud 2023.0.2
* maven <font color=red>待定</font>

## 服务端口

| 服务名        | 端口 | 说明                           |
| ------------- | ---- | ------------------------------ |
| auth-gateway  | 6001 | API网关                        |
| auth-security | 6002 | 鉴权、认证                     |
| auth-user     | 6003 | 用户                           |
| auth-base     | 6004 | 基础                           |
| auth-common   | -    | 全局实体类、异常、响应、工具类 |
| auth-parent   | -    | 版本管理                       |
| auth-doc      | -    | 文档                           |
|               |      |                                |



## 数据库配置



| 服务名    | 数据库        | schema    | IP        | PORT | user  | pw    |      |
| --------- | ------------- | --------- | --------- | ---- | ----- | ----- | ---- |
| auth-user | auth-tutorial | auth-user | localhost | 5432 | loong | loong |      |
| auth-base | auth-tutorial | auth-base | localhost | 5432 | loong | loong |      |
|           |               |           |           |      |       |       |      |

