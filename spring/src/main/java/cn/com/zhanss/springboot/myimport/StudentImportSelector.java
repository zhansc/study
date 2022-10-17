package cn.com.zhanss.springboot.myimport;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 1、@Import导入的类，直接注入IoC容器
 * 2、@Import导入的类实现了ImportSelector接口，重写了selectImports方法，
 * 则注入的是该方法返回的字符串数组类型的Bean
 * 3、
 * @author zhanss
 * @since 2022-10-13
 */
public class StudentImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"cn.com.zhanss.spring.service.impl.TeacherService"};
    }

}
