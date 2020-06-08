* 顶层单机SpringGateway由eureka负载均衡转发至路由安全集群
* 权限模块SpirngGateway+SpringSecurity+JWT3合一。
* 其他服务使用Cloud-Netflix体系。
* 消息队列使用SpringStream+kafka，顺便凑个springBus+Config。
* jwt只放username和登录时间，由redisServer控制账号状态，jwt只做类似sessionID一样的功能。
* 其他模块不用关心权限，负载均衡不使用客户端显式ribbon，在eureka自定义负载均衡。可以序列servlet请求持久化尝试取代消息队列，可以做到同步/异步延迟削峰。
* 鉴权，redisServer都是公共的子模块。 鉴权是需要公共编码的模块，redisServer是从redis获取数据的公共api。

----
    除开顶层网关，其他服务或者组件都可以扩展数量，顶层网关也可以使用nginx。