package cn.com.zhanss.queue.queue;

import org.apache.activemq.broker.BrokerService;

/**
 * ActiveMQ支持基于嵌入式的broker（迷你版的ActiveMQ，就是一个ActiveMQ实例）
 *
 * @author zhanss
 * @since 2019/10/11
 */
public class EnableBroker {

    public static void main(String[] args) throws Exception {

        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();

    }

}
