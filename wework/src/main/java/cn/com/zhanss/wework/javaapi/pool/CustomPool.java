package cn.com.zhanss.wework.javaapi.pool;
/**
 * 自定义对象池
 *
 * @author zhanshuchan
 * @version CustomPool.java, v 0.1 2025/11/4 zhanshuchan Exp $
 */

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhanshuchan
 * @date 2025/11/4
 **/
public class CustomPool<F> {

    CustomPoolFactory<F> factory;

    LinkedBlockingQueue<F> pool = new LinkedBlockingQueue<>();

    public CustomPool(CustomPoolFactory<F> factory) {
        this.factory = factory;
    }

    /**
     * 获取对象
     * @return
     */
    F borrow() {
        F f = pool.poll();
        if (f == null) {
            f = factory.create();
        }
        pool.offer(f);
        return f;
    }


    void back(F f) {
        pool.offer(f);
    }

}
