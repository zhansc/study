package cn.com.zhanss.springboot.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 班长服务
 *
 * @author zhanss
 * @since 2022-10-13
 */
public class MonitorService implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"cn.com.zhanss.spring.entity.Monitor"};
    }
}
