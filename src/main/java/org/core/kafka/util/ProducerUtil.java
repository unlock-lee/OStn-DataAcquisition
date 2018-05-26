package org.core.kafka.util;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * Created by ChangLee on 2018/5/25.
 */
public class ProducerUtil {

    public static void producerSend(Object message){
        if(StringUtils.isBlank((CharSequence) message)){
            return;
        }
        Properties props = new Properties();
        //broker列表
        props.put("metadata.broker.list", "192.168.0.102:9092");
        //串行化
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        //创建生产者配置对象
        ProducerConfig config = new ProducerConfig(props);
        //创建生产者
        Producer<String, String> producer = new Producer<String, String>(config);
        KeyedMessage<String, String> msg = new KeyedMessage<String, String>("GeneralNews","100" , message.toString());
        producer.send(msg);
        System.out.println("send over!");
    }

}
