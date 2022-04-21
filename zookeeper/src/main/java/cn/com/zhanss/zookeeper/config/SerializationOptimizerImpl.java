package cn.com.zhanss.zookeeper.config;

import org.apache.dubbo.common.serialize.support.SerializationOptimizer;

import java.util.Collection;

/**
 * 序列化类
 *
 * @author zhanss
 * @since 2022-04-21
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {
    @Override
    public Collection<Class> getSerializableClasses() {
        return null;
    }
}
