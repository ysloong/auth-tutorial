auth-security



sessionId 需要存在服务器，而jwt不需要存在服务器，只需要存在客户端(localStorage, sessionStorage)，在服务器进行验证即可。（适合分布式系统）
token = head.payload.signature

请求时，将jwt放在headers中的Authorization字段中，解决XSS和XSRF问题



1 实现登录拦截



1 定义入口

2 定义接受参数的实体

3 filter 提取参数

4 provider 校验认证