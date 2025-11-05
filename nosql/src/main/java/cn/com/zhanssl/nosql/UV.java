package cn.com.zhanssl.nosql;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 亿级客户网站UV，用户日活统计
 * 精确统计
 * zset 大key
 * hash 大key
 * bitmap 大key 占用空间相对小
 * hyperloglog 概率统计，占用极小的空间快速统计，误差小于0.81%
 *
 * @author zhanshuchan
 * @date 2024/1/23
 **/
public class UV {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/testHyperLogLog/v1")
    public Long testHyperLogLog() {
        // 准备数组，装用户数据
        String[] users = new String[1000];
        int index = 0;
        for (int i = 1; i < 1000000; i ++) {
            // 贼值
            users[index++] = "user_" + i;
            // 每1000条发送一次
            if (i % 1000 == 0) {
                index = 0;
                stringRedisTemplate.opsForHyperLogLog().add("dau", users);
            }
        }
        //统 计数量
        return stringRedisTemplate.opsForHyperLogLog().size("dau");
    }


    /**
     * 阻塞队列LinkedBlockingDeque缓存，异步写入
     */
    LinkedBlockingDeque<String> dauList = new LinkedBlockingDeque<>();
    @GetMapping("/sizeHyperLogLog/v1")
    public Long sizeHyperLogLog(){
        // 从10亿用户id中，随机
        for (int i = 1; i < 100; i++) {
            Random random = new Random();
            int index = random.nextInt(1_000_000_000);
            // 赋值
            dauList.add("user_" + index);
        }
        // 統计数量
        return stringRedisTemplate.opsForHyperLogLog().size("dau");
    }

    private volatile boolean dauStarted = false;
    @GetMapping ("/startHyperLogLoq/v1")
    public synchronized String startHyperLogLog() {
        if (!dauStarted) {
            new Thread(() -> {
                List<String> tempModels = new ArrayList<>();
                //每10ms推送一次
                try {
                    // 清空dauList
                    // Queues.drain(dauList, tempModels, 1000, 10, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (tempModels == null || tempModels.size() == 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String[] keys = tempModels.stream().toArray(String[]::new);
                stringRedisTemplate.opsForHyperLogLog().add("dau", keys);
            }).start();
            dauStarted = true;
        }
        return "success";
    }
}