package cn.com.zhanss.datastructure;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.*;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.assertj.core.util.Preconditions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @Test
    public void test12() {
        Map<String, String> errCode2FailureEnum = new HashMap<>();
        errCode2FailureEnum.put("A", "1");
        errCode2FailureEnum.put("B", "2");
        errCode2FailureEnum.put("C", "3");
        String value = errCode2FailureEnum.getOrDefault("B", "0");
//        System.out.println("value===>"+ value);

        // 正则表达式：匹配电话号码(\+\d{2}\s?|\+\d{2}-?)? --> 国家代码 +86  或+86-)
//        String regex = "^(\\+\\d{2}\\s?|\\+\\d{2}-?)?0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|1[0-9]{2}[-]?\\d{4}[-]?\\d{4}";
        // 正则匹配手机号码
        String regex = "^(\\+\\d{2}\\s?|\\+\\d{2}-?)?(1[0-9]{2}[-]?\\d{4}[-]?\\d{4}|1[0-9]{2}\\s?\\d{4}\\s?\\d{4})$";
        String phone = "187-05629811"; //接收用户在控制台输入的电话号码
        Pattern pattern = Pattern.compile(regex); //编译正则表达式
        Matcher matcher = pattern.matcher(phone); //创建给定输入模式的匹配器
        boolean bool = matcher.matches();
        if (bool) { //如果验证通过
            System.out.println("输入的电话号码格式正确。");
        } else {
            System.out.println("输入的电话号码无效，格式不正确。");
        }
        phone = "+28-158 6888 0395";
        Assert.assertTrue(pattern.matcher(phone).matches());

    }

    @Test
    public void test13() {
        List<String> jsons = Arrays.asList("\\xe4\\xb8\\xbd\\xe4\\xba\\xba","\\xe6\\x9c\\x8d\\xe8\\xa3\\x85/\\xe9\\x9e\\x8b/\\xe7\\xae\\xb1\\xe5\\x8c\\x85");

        Gson gson = new Gson();
        List<String> list = new ArrayList<>();
        for (String json : jsons) {
            list.add(gson.fromJson(json, String.class));
        }
        System.out.println("list==>"+ list);
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
