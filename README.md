一个基于Socket实时数据采集脚手架.

Q1：项目简介?
A1：目前是作为客户端去连接多个服务端,后面会加入服务端连接客户端模式实现二种模式切换或混合存在.

Q2：这个项目可以做什么？
A2：基于Socket进行通讯的，需要Socket进行数据采集中转的.

Q3：为什么要加中间件消息队列？
A3：实时数据采集时加上业务逻辑处理数据无法实时处理这么多数据，加入消息队列进行存储处理.

Q4：为什么是Web版本?
A4：本来是打算做成Web配置的,后面有点忙不开简化了.

Q5：src/main/java/org/core/client
A5：主要是做了重连 NetyyClientRun为主入口，web版本可以把Run放在/web/Controller/

Q6：src/main/java/org/core/config
A6：将连接的Channel进行存储,方便后面实现Channel管理.

Q7：src/main/java/org/core/kafka/util
A7：接收到的数据会由生产者接管，业务处理数据由消费者提供.

Q8：src/main/java/org/core/server
A8：服务端模式,混合或者双模会由此包进行改造.

Q9：src/main/java/org/core/server/launch
A9：作为服务端进行发送测试数据.

PS: 后面有时间会将信息[Netty ZK KAFKA/IP|PORT等信息进行配置化]，脚手架版本的不加入任何涉及任何SQL操作.
    如果发现BUG或有问题或者更好实现方法可以直接提交合并请求.
    EMail:1575994958@qq.com.

Thank you.