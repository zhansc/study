package cn.com.zhanss.wework.javaapi.pool;/**
 * 自定义对象池工厂
 *
 * @author zhanshuchan
 * @version CustomPoolFactory.java, v 0.1 2025/11/4 zhanshuchan Exp $
 */

/**
 * @author zhanshuchan
 * @date 2025/11/4
 **/
public interface CustomPoolFactory<F> {

    /**
     * 创建对象
     * @return
     */
    F create();
}
