package cn.com.zhanss.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户端
 *
 * @author zhanss
 * @since 2019/9/22
 */
public class DistributeClient {

    private String connectString = "192.168.0.130:2181,192.168.0.131:2181,192.168.0.132:2181";

    // 连接超时时间
    private int sessionTimeout = 2000;

    private ZooKeeper zkClient;

    @Test
    public void main() throws IOException, KeeperException, InterruptedException {
        DistributeClient client = new DistributeClient();
        // 1、连接Zookeeper集群
        client.getConnect();

        // 获取服务器列表，并注册监听
        client.getChildren();

        // 业务逻辑
        client.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/servers", true);
        List<String> domains = new ArrayList<>();
        for(String child : children) {
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            domains.add(new String(data));
        }
        // 输出所有在线服务器主机名
        System.out.println(domains);
    }

    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {
            // 监听获取在线服务器列表方法
            try {
                getChildren();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
