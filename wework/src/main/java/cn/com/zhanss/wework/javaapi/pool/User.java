package cn.com.zhanss.wework.javaapi.pool;/**
 * 用户对象测试类
 *
 * @author zhanshuchan
 * @version User.java, v 0.1 2025/11/4 zhanshuchan Exp $
 */

/**
 * @author zhanshuchan
 * @date 2025/11/4
 **/
public class User {

    public static void main(String[] args) {
        CustomPool<User> pool = new CustomPool<>(new CustomPoolFactory<User>() {
            @Override
            public User create() {
                return new User();
            }
        });

        System.out.println(pool.borrow().hashCode());

        for (int i = 0; i < 2; i ++) {
            User borrow = pool.borrow();
            System.out.println(borrow.hashCode());
            pool.back(borrow);
        }
    }
}

