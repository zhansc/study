package cn.com.zhanssl.nosql;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * redis实现抢红包
 * 二倍均值法
 *
 * @author zhanshuchan
 * @date 2024/1/30
 **/
public class RedPacketUtil {

    /***
     * 发红包算法，金额参数以分为单位
     * @param totalMoney
     * @param redPackageNumber
     * @return
     */
    private Integer[] splitRedPackageAlgorithm(int totalMoney,int redPackageNumber) {
        Integer[] redPackageNumbers = new Integer[redPackageNumber];
        // 已经被抢夺的红包金额,已经被拆分塞进子红包的金额
        int useMoney = 0;

        for (int i = 0; i < redPackageNumber; i++) {
            if(i == redPackageNumber - 1) {
                redPackageNumbers[i] = totalMoney - useMoney;
            } else {
                // 二倍均值算法，每次拆分后塞进子红包的金额 = 随机区间(0,(剩余红包金额M ÷ 未被抢的剩余红包个数N) * 2)
                int avgMoney = ((totalMoney - useMoney) / (redPackageNumber - i)) * 2;
                // 左闭右开
                redPackageNumbers[i] = 1 + new Random().nextInt(avgMoney - 1);
            }
            useMoney = useMoney + redPackageNumbers[i];
        }
        return redPackageNumbers;
    }

    public String sendRedPackage(int totalMoney,int redPackageNumber) {
        //1 拆红包，将总金额totalMoney拆分为redPackageNumber个子红包
        Integer[] splitRedPackages = splitRedPackageAlgorithm(totalMoney,redPackageNumber);
        //拆分红包算法通过后获得的多个子红包数组
        //2 发红包并保存进list结构里面且设置过期时间
        String key = RED_PACKAGE_KEY + new Random(10000);
        redisTemplate.opsForList().leftPushAll(key,splitRedPackages);
        redisTemplate.expire(key,1, TimeUnit.DAYS);

        //3 发红包OK，返回前台显示
        return key+"\t"+ Collections.singletonList(Arrays.stream(splitRedPackages).mapToInt(Integer::valueOf).toArray());
    }

    /**
     * 红包缓存key
     */
    private static final String RED_PACKAGE_KEY = "red:package";
    /**
     * 已经抢到红包的用户key
     */
    private static final String RED_PACKAGE_CONSUME_KEY = "red:package:consume";
    @Resource
    private RedisTemplate redisTemplate;

    public String robRedPackage(String redPackageKey,String userId) {
        //1 验证某个用户是否抢过红包，不可以多抢
        Object redPackage = redisTemplate.opsForHash().get(RED_PACKAGE_CONSUME_KEY + redPackageKey, userId);
        //2 没有抢过可以去抢红包，否则返回-2表示该用户抢过红包了
        if(null == redPackage) {
            //2.1 从大红包(list)里面出队一个作为该客户抢的红包，抢到了一个红包
            Object partRedPackage = redisTemplate.opsForList().leftPop(RED_PACKAGE_KEY + redPackageKey);
            if(partRedPackage != null) {
                //2.2 抢到红包后需要记录进入hash结构，表示谁抢到了多少钱的某个子红包
                redisTemplate.opsForHash().put(RED_PACKAGE_CONSUME_KEY + redPackageKey, userId, partRedPackage);
                System.out.println("用户:"+userId+"\t 抢到了多少钱的红包："+partRedPackage);
                //TODO 后续异步进mysql或者MQ进一步做统计处理,每一年你发出多少红包，抢到了多少红包，年度总结
                // 红包信息，每个子红包明细，抢红包记录
                return String.valueOf(partRedPackage);
            }
            // 抢完了
            return "errorCode:-1,红包抢完了";
        }
        //3 某个用户抢过了，不可以作弊抢多次
        return "errorCode:-2,   message:"+userId+"\t"+"你已经抢过红包了，不能重新抢";
    }
}

