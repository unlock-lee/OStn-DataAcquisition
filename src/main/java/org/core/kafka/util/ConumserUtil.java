package org.core.kafka.util;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * kafka消费者
 */
public class ConumserUtil {

    public static void transmit(){
        Properties props = new Properties();
        //指定zk地址
        props.put("zookeeper.connect", "192.168.0.102:2181");
        //指定组ID
        props.put("group.id", "g3");
        //指定zk会话超时时间
        props.put("zookeeper.session.timeout.ms", "500");
        //指定zk同步时间
        props.put("zookeeper.sync.time.ms", "250");
        //自动提交间隔时间
        props.put("auto.commit.interval.ms", "1000");
        //偏移量自动重置
        props.put("auto.offset.reset", "smallest");
        //创建消费者配置对象
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        Map<String, Integer> map = new HashMap<>();
        map.put("GeneralNews", new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> msgs = Consumer.createJavaConsumerConnector(new ConsumerConfig(props)).createMessageStreams(map);
        List<KafkaStream<byte[], byte[]>> msgList = msgs.get("GeneralNews");
        for(KafkaStream<byte[],byte[]> stream : msgList){
            ConsumerIterator<byte[],byte[]> it = stream.iterator();
            while(it.hasNext()){
                byte[] message = it.next().message();
                //调用Netty服务端进行消息转发
                System.out.println(new String(message));
            }
        }
    }

}
