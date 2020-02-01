package cn.com.zhanss.datastructure;
import com.alibaba.fastjson.JSON;
import lombok.*;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.assertj.core.util.Preconditions;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Velocity模板引擎
 * @author zhanshuchan
 * @date 2019/11/22
 */
public class VelocityTest {

    @Test
    public void testVelocity () {
        // 初始化模板引擎d
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
        velocityEngine.evaluate(velocityContext, stringWriter,
                "",
                "我的名字是${name}，是${list[0]}，是${list[1]}");
//        template.merge(velocityContext, stringWriter);
        System.out.println("stringWriter==>:"+ stringWriter.toString());
    }

    @Test
    public void testReplace() {
        String templateContent = "{\"templateId\":\"${templateId}\",\"page\":\"${page}\",\"data\":{\"keyword1.DATA \":\"value \":\"$ {keyword1}\",\"keyword2.DATA\":\"value\":\"${keyword2}\",\"keyword3.DATA\":\"value\":\"${keyword3}\",\"keyword4.DATA\":\"value\":\"${keyword4}\"},\"emphasisKeyword\":\"first.DATA\"}";
        String[] arr = {"1","2","3","4"};
        List<String> kIdList = Arrays.asList(arr);
        List<MiniProgramKeywordDetail> keyWords = new ArrayList<>();
        for (int i = 1; i < 5; i ++) {
            MiniProgramKeywordDetail keywordDetail = new MiniProgramKeywordDetail();
            keywordDetail.setKeywordId(i+"");
            if (i == 1) {
                keywordDetail.setRule("thing");
            } else if (i == 4) {
                keywordDetail.setRule("name");
            } else {
                keywordDetail.setRule("number");
            }
            keyWords.add(keywordDetail);
        }
        if (!StringUtils.isEmpty(templateContent)) {
            Map<String, Integer> ruleMap = new HashMap<>();
            for (int i = 1; i <= kIdList.size(); i ++) {
                for (MiniProgramKeywordDetail keywordDetail : keyWords) {
                    if (!keywordDetail.getKeywordId().equals(kIdList.get(i-1))) {
                        continue;
                    }
                    String rule = keywordDetail.getRule();
                    Integer ruleNum = 0;
                    if (ruleMap.containsKey(rule)) {
                        ruleNum = ruleMap.get(rule);
                    }
                    ruleNum ++;
                    ruleMap.put(rule, ruleNum);
                    templateContent = templateContent.replaceAll("keyword"+ i +".DATA", rule+ "0"+ ruleMap.get(rule));
                    System.out.println("第"+i+"版：templateContent===>"+ templateContent);
                }
            }
        }
        System.out.println("最终版：templateContent===>"+ templateContent);
    }

    @Test
    public void testJavaBase() {
        MiniProgramKeywordDetail miniProgramKeywordDetail = MiniProgramKeywordDetail.builder()
                .keywordId("all")
                .build();

        System.out.println("miniProgramKeywordDetail===>"+miniProgramKeywordDetail);


        List<Integer> cities = Arrays.asList(1, 2, 3, 4);
        String templateKey = cities.stream().map(Object::toString).collect(Collectors.joining(","));
        System.out.println("templateKey====>"+templateKey);
    }


    @Test
    public void test11() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 24, 12, 11, 0);
        LocalDateTime offLineTime = LocalDateTime.of(2019, 12, 24, 12, 21, 59);
        if (LocalDateTime.now().isAfter(offLineTime)) {
            System.out.println("还没有到时间，该下线了");
        } else {
            System.out.println("还没有到时间！");
        }

        Map<String, MiniProgramKeywordDetail> resultMap = new HashMap<>();
        MiniProgramKeywordDetail miniProgramKeywordDetail = MiniProgramKeywordDetail.builder()
                .keywordId("")
                .name("name")
                .example("example")
                .rule("rule")
                .build();
        resultMap.put("scene", miniProgramKeywordDetail);
        String result = JSON.toJSONString(resultMap);

        Map<String, Object> map = JSON.parseObject(result, Map.class);
        Map<String, MiniProgramKeywordDetail> miniProgramKeywordDetailMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            miniProgramKeywordDetailMap.put(entry.getKey(), JSON.toJavaObject((JSON)JSON.toJSON(entry.getValue()), MiniProgramKeywordDetail.class));
        }
        System.out.println("miniProgramKeywordDetailMap==>"+ miniProgramKeywordDetailMap);

        Preconditions.checkNotNull(miniProgramKeywordDetail.getKeywordId(), "请填写关键词ID");

    }
}

@EqualsAndHashCode
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class MiniProgramKeywordDetail {
    private String keywordId;
    private String name;
    private String example;
    private String rule;

    @Builder.Default
    private Boolean boo = false;
}
