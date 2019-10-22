package cn.com.zhanss.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 服务端
 *
 * @author zhanss
 * @since 2019/9/22
 */
public class DistributeServer {

    private ZooKeeper zkServer;

    // 连接Zookeeper集群配置
    private String connectString = "192.168.0.130:2181,192.168.0.131:2181,192.168.0.132:2181";

    // 每一个会话连接超时时间（2s）
    private int sessionTimeout = 2000;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        DistributeServer server = new DistributeServer();
        // 1、连接Zookeeper集群
        server.getConnect();

        // 2.注册节点：创建节点（短暂的带序号的），传入各服务器的主机名
        server.registry(args[0]);

        // 业务逻辑
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void registry(String hostname) throws KeeperException, InterruptedException {
        zkServer.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    private void getConnect() throws IOException {
        zkServer = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {

        });
    }

}
