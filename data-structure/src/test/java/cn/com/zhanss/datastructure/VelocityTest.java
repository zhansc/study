package cn.com.zhanss.datastructure;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Velocity模板引擎
 * @author zhanshuchan
 * @date 2019/11/22
 */
public class VelocityTest {

    @Test
    public void testVelocity () {
        // 初始化模板引擎
        VelocityEngine velocityEngine = new VelocityEngine();
//        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        // 获取模板文件
//        Template template = velocityEngine.getTemplate(VM_PATH);

        // 设置变量，velocityContext是一个类似map的结构
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name", "world");
        List<String> list = new ArrayList<>();
        list.add("jack");
        list.add("kitty");
        velocityContext.put("list", list);

        // 输出渲染后的结果
        StringWriter stringWriter = new StringWriter();
        velocityEngine.evaluate(velocityContext, stringWriter, "", "我的名字是${name}，是${list[0]}，是${list[1]}");
//        template.merge(velocityContext, stringWriter);
        System.out.println("stringWriter==>:"+ stringWriter.toString());
    }

}
