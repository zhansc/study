package cn.com.zhanss.springboot.myimport;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhanss
 * @since 2022-10-13
 */
public class TeacherImportSelector implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 通过registry 注册器自定义Bean
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        System.out.println("beanDefinitionNames.length--->"+ beanDefinitionNames.length);
    }

    class MyGroup implements DeferredImportSelector.Group {

        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {

        }

        @Override
        public Iterable<Entry> selectImports() {
            return null;
        }
    }
}
