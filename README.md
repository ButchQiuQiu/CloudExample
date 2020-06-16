# 启动
    config放置模块的配置,configServer和eurekaServer的配置在所属的resource文件夹下。 默认连接同名gitee的仓库，github实在是太卡了，如果有需求可以在configserver里面改。数据库转储在根目录，kafka默认绑定的broker端口号是localhost:9092,stream会自动创建主题什么的，启动kafka就行了。

# 描述
* 顶层单机SpringGateway由eureka负载均衡转发至路由安全集群
* 权限模块SpirngGateway+SpringSecurity+JWT3合一。
* 其他服务使用Cloud-Netflix体系。
* 消息队列使用SpringStream+kafka，顺便凑个springBus+Config。
* jwt只放username和登录时间，由redisServer控制账号状态，jwt只做类似sessionID一样的功能。
* 其他模块不用关心权限，其他微服务由ZUUL负载均衡。
* 鉴权，redisServer都是公共的子模块。 鉴权是需要公共编码的模块，redisServer是从redis获取数据的公共api。

----
    除开顶层网关，其他服务或者组件都可以扩展数量，顶层网关也可以换nginx。
* 小框架前端资源由zuul提供拦截。
* 网关权限全用gateway需要整webflux这个大坑，就不当革命战士了。。。

## 所有服务可以经由ZuulSecurity集群自定义负载均衡和自定义鉴权。 极度高可用，如果顶层网关压力小，所有服务从SpirngGateWay调用可以做到究极无敌的全局负载均衡，哪里不够加哪里。
    并非一切都是那么美好，我想让所有本地的必须配置，比如eureka的client和server，configserver全部集中到一个子模块中集中管理，只需要include就行了，可是configClient+eurekaClient只能声明才能跑起来不能include。就很恶心了。导致每个模块必须得声明eurekaClient，万一以后eurekaServer改端口其他也得改。