package cn.com.zhanss.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperApplicationTests {

    // 连接Zookeeper集群配置
    private String connectString = "192.168.0.130:2181,192.168.0.131:2181,192.168.0.132:2181";
    // 连接超时时间
    private int seeesionTimeout = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {
        // Watcher是一个监听回调函数
        zkClient = new ZooKeeper(connectString, seeesionTimeout, watchedEvent -> {
            try {
                // 监听"/"节点路径/节点的变化
                List<String> children = zkClient.getChildren("/", true);
                for(String child : children) {
                    System.out.println("child:"+ child);
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 主线程
     * 在主线程中启动Zookeeper客户端
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {

        String path = zkClient.create(
                // 节点名称
                "/zhanss",
                // 数据
                "University".getBytes(),
                // 权限控制
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                // 创建的节点类型：持久型、持久型带序号、暂时型、暂时型带序号
                CreateMode.PERSISTENT);
        System.out.println(path);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/zhanss", false);
        System.out.println(stat);
        System.out.println(stat == null ? "not exist" : "exist");
    }

}
