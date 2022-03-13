package cn.com.zhanss.wework.javaapi.aes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @desc H5
 * @author zhanshuchan
 * @date 2021/7/21
 */
public class H5Test {

    private static final String URL = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private static final Pattern URL_PATTERN = Pattern.compile(URL);

    @Test
    public void testH5() {

        String wecomExternalUserId = "wmv2i2DQAACooFXaghAQ59ToAYVcK3fw";
        Long yzKdtId = 97383020L;
        Long recordTime = 1628475643L;
        Long staffId = 3320100L;
        String rowkey = "031e4418c0b6e5d180d6b7c53cb5296a_9223370399782244767";
        try {
            System.out.println("rowkey--->"+ rowkey.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String md50 = Hashing.md5().newHasher()
                .putString(wecomExternalUserId +"_"+ yzKdtId, Charsets.UTF_8)
                .hash().toString();
        System.out.println("md50----->"+ md50);
        String md51 = Hashing.md5().newHasher()
                .putString(wecomExternalUserId + "_" + staffId +"_"+ yzKdtId, Charsets.UTF_8)
                .hash().toString();
        System.out.println("md51----->"+ md51+ "_"+ recordTime);
        String md52 = Hashing.md5().newHasher()
                .putString("wmVqC8DQAAjOEXcqRsixvg7Mav9Js-XQ" +"_"+ 93850523L, Charsets.UTF_8)
                .hash().toString();
        System.out.println("md52----->"+ md52);

        String mediaUrl = "http://www.bai.com";
        boolean matches = URL_PATTERN.matcher(mediaUrl).matches();
        System.out.println("matches----->"+ matches);

        String imageUrl = "https://img01.yzcdn.cn/upload_files/2021/09/14/.FlzM0DY3KOc4dyy5MMbn2HLenl6B";
        int right = imageUrl.lastIndexOf(".");
        int left = imageUrl.lastIndexOf("/");
        if (left > right) {
            right = imageUrl.length();
        }

        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/")+ 1, right);
        System.out.println("imageName----->"+ imageName);

        String URL_REGEX = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        String imageUrl1 = "https://img01.yzcdn.cn/upload_files/2021/09/15/FkpemStFb8TDGK3yfVP1gdyG1fJ4.png";
        String imageUrl2 = "https://img01.yzcdn.cn/upload_files/2021/09/15/FmyCuoQVhTRRM0hbMSp4OwudCtDa.png";
        String imageUrl3 = "https://img01.yzcdn.cn/upload_files/2021/09/15/FkisTcugv8XBBgNZ8svjaEhMhqj6.png";
        String linkUrl = "https://www.baidu.com/";
        String miniprogramImageUrl = "https://img01.yzcdn.cn/upload_files/2021/09/15/FlnJjAqgB5JVoT-ycrMISrBBWJk3.png";
        String coverUrl = "https://img01.yzcdn.cn/upload_files/2021/06/28/Fs7vrhPQDsNmq3aDQOB6lhWLULC-.png";
        Lists.newArrayList(imageUrl1, imageUrl2, imageUrl3, linkUrl, miniprogramImageUrl, coverUrl)
                .forEach(i -> {
                    if (!i.matches(URL_REGEX)) {
                        System.out.println("url is not matches----->"+ i);
                    }
                    System.out.println("bingo");
                });


        String content = "[{\"content\":\"测试测试积分<客户昵称>\",\"msgType\":1},{\"content\":\"https://img01.yzcdn.cn/upload_files/2021/10/15/FsA3OOrh7OchjbbzgPtYQ54MN0Tj.gif\",\"msgType\":2}]";
        String name = "JMa$TeR";
        System.out.println("content--->"+ content.replaceAll("%NICKNAME%", name));

        List<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("content", "测试测试积分<客户昵称>");
        jsonObject1.put("msgType", 1);
        jsonObjects.add(jsonObject1);
        Optional<JSONObject> pointMsg = jsonObjects.stream().filter(Objects::nonNull)
                .filter(i -> StringUtils.equalsIgnoreCase
                        (i.getString("msgType"), "6"))
                .findFirst();
        Integer pointNum = null;
        if (pointMsg.isPresent()) {
            System.out.println("pointNum start");
            pointNum = pointMsg.get().getInteger("content");
        }
        System.out.println("pointNum end-->"+ pointNum);

        Map<String, String> duplicatMap = new HashMap<>();
        duplicatMap.put("你好", "nihao");
        duplicatMap.put("我好", "wohao");
        duplicatMap.put("大家好", "dajiahao");
        duplicatMap.put(null, "kong");
        System.out.println("all--->"+ duplicatMap);
        duplicatMap.remove(null);
        System.out.println("all--->"+ duplicatMap);
        System.out.println("你好--->"+ duplicatMap.get("你好 "));
        System.out.println("你好--->"+ duplicatMap.get(" 你好"));
        System.out.println("你好--->"+ duplicatMap.get("你好!"));

    }

    @Test
    public void test1() {
        // 昨日凌晨时间戳
        LocalDateTime yesterdayTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                .minusDays(1)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("yesterdayTime----->"+ yesterdayTime);
        // 前30天的凌晨时间戳
        LocalDateTime beforeThirtyTime = yesterdayTime.minusDays(0);
        System.out.println("beforeThirtyTime----->"+ beforeThirtyTime);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(null);
        list1.add(4);
        System.out.println("list1----->"+ list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(1);
        System.out.println("list1--nonNull--->"+ list1.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        System.out.println("list2----->"+ list2);
        System.out.println("list1.containsAll(list2)----->"+ list1.containsAll(list2));
        System.out.println("list2.subList()----->"+ list1.subList(0, list1.size()));

        boolean b = list1.stream().anyMatch(list2::contains);
        System.out.println("list1.containsAll(list2)----->"+ b);
        System.out.println("list2----->"+ list2);
        List<Integer> needTags = ListUtils.removeAll(list2, list1);
        list2.addAll(needTags);
        HashSet<Integer> integers = Sets.newHashSet(list2);
        System.out.println("integers----->"+ integers);
        list2.addAll(integers);
        System.out.println("list2----list2->"+ list2);
        Set<Integer> tagIdList = list2.stream().filter(Objects::nonNull).collect(Collectors.toSet());
        System.out.println("list2.addAll----->"+ tagIdList);

        list2.removeAll(new ArrayList<>());
        System.out.println("list2.removeAll()----->"+ list2);


//        System.out.println("lastOne----->"+ list2.get(list2.size()));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime startTime = LocalTime.parse("00:00:00", dtf);
        LocalTime endTime = LocalTime.parse("23:00:00", dtf);
        LocalTime localTime = LocalTime.parse("19:38:16", dtf);
//        LocalTime localTime = LocalTime.now();
        if (localTime.isAfter(startTime) && localTime.isBefore(endTime)) {
            System.out.println("-----isnow----");
        }
        JSONObject config = JSONObject.parseObject("{}");
        System.out.println("-----config----"+ config);

        System.out.println("-----json----"+ new JSONObject().toJSONString());
    }

    @Test
    public void test2() {

        InnerUser innerUser = InnerUser.builder().build();
        System.out.println("innerUsere-->"+ innerUser);
        innerUser.setId(1);
        innerUser.setIsNext(true);
        System.out.println(innerUser);
        InnerUser innerUser2 = InnerUser.builder().id(2).name("name2").build();
        List<InnerUser> innerUsers = new ArrayList<>();
        innerUsers.add(innerUser);
        innerUsers.add(innerUser2);
        List<Integer> innerUserIds = innerUsers.stream().map(InnerUser::getId).collect(Collectors.toList());
        System.out.println("innerUserIds-->"+ innerUserIds);
        Set<Integer> wecomTagIds = new HashSet<>(innerUserIds);
        System.out.println("wecomTagIds-->"+ innerUserIds);

        Integer days = 1;
        Integer hour = 9;
        Integer minute = 0;
        LocalDateTime daysAfter = LocalDateTime.now().plusDays(days);
        LocalDateTime targetLocalDateTime = LocalDateTime.of(daysAfter.getYear(),
                daysAfter.getMonth(), daysAfter.getDayOfMonth(), hour, minute);
        Date nextExecTime = Date.from(targetLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("nextExecTime-->"+ nextExecTime);
        System.out.println("nextExecTime.getTime-->"+ nextExecTime.getTime());


        LocalDateTime target = LocalDateTime.now().withHour(0)
                .withMinute(0).withSecond(0).withNano(0)
                .plusDays((0 - 1500));

        long epochSecond = target.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        System.out.println("epochSecond-->"+ epochSecond);

        System.out.println("-------------------------------");
        Map<Integer, List<Integer>> welcomeMsgStaffIdMap = new HashMap<>();
        for (int i = 1; i < 4; i ++) {
            if (welcomeMsgStaffIdMap.containsKey(i)) {
                 welcomeMsgStaffIdMap.get(i).add(i);
            } else {
                List<Integer> ones = new ArrayList<>();
                ones.add(i);
                welcomeMsgStaffIdMap.put(i, ones);
            }
        }
        System.out.println("welcomeMsgStaffIdMap-->"+ JSON.toJSONString(welcomeMsgStaffIdMap));

        String user = JSONObject.toJSONString(innerUser);
        JSONObject jsonObject = JSONObject.parseObject(user);
        List<Integer> ones = new ArrayList<>();
        ones.add(1);
        WelcomeMsgFeatureBO featureBO = new WelcomeMsgFeatureBO();
        featureBO.setOnes(ones);
        jsonObject.put("attachmentId", ones.toString());
        System.out.println("jsonObject-->"+ JSON.toJSONString(jsonObject));

        String featureStr = "{\"feature\":\"[{\\\"addLimit\\\":200,\\\"qrCode\\\":\\\"https://img01.yzcdn.cn/upload_files/2021/08/18/Fi8p_MdlihPSwnS9vYh57i_HNc0a.png\\\",\\\"sort\\\":1,\\\"state\\\":1,\\\"wecomChatId\\\":\\\"wrKIr2DAAAfaxkAIjvHlflugCk7dHFSw\\\"}]\"}";
        JSONObject featureJson = JSONObject.parseObject(featureStr);
        System.out.println("featureS-->\n"+ featureJson.getString("feature"));


        WelcomeMsgFeatureBO featureBO1 = new WelcomeMsgFeatureBO();
        featureBO1.setTwos(new HashMap<>());
        for (Map.Entry<String, String> entry : featureBO1.getTwos().entrySet()) {
            System.out.println("entry-->\n"+ entry.getValue());
        }

        List<String> list = new ArrayList<>();
        System.out.println("list-->"+ list);
        for (String s : list) {
            System.out.println("s-->\n"+ s);
        }

        System.out.println("计算字节长度1--->"+ "\uD83D\uDCDA限时免费｜商家实战【案例】5万字，18个场景，50个案例".getBytes().length);
        System.out.println("计算字节长度2--->"+ "限时免费｜商家实战【案例】5万字，18个场景，50个案例".getBytes().length);
        System.out.println("计算字节长度3--->"+ "限时免费|商家实战案例|5万字,18个场景,50个案例".getBytes().length);

        Set<String> excludeExternalUserIds = new HashSet<>();
        List<InnerUser> users = new ArrayList<>();
        try {
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdfa = new SimpleDateFormat(strDateFormat);
            for (int i = 1; i < 4; i ++) {
                InnerUser innerUser1 = new InnerUser();
                innerUser1.setId(i);
                innerUser1.setName("name:"+ i);
                if (i / 2 == 0) {
                    innerUser1.setIsNext(true);
                    innerUser1.setDate(sdfa.parse("2021-12-08 22:50:31"));
                } else {
                    innerUser1.setIsNext(false);
                    innerUser1.setDate(sdfa.parse("2021-12-06 22:50:31"));
                }
                users.add(innerUser1);
            }
        } catch (Exception e) {

        }
        users.forEach(user1 -> excludeExternalUserIds.add(user1.getName()));
        System.out.println("excludeExternalUserIds==>"+ excludeExternalUserIds);

        List<InnerUser> returnUsers = new ArrayList<>();
        returnUsers.add(InnerUser.builder().id(12).name("name12").build());
        Integer order = this.buildParam(users, returnUsers, 0, 1);
        System.out.println("buildParam==>"+ order+ "\n" + JSON.toJSONString(returnUsers));

        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
        try {
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            Date date = sdf.parse("2021-12-06 22:50:31");
            System.out.println("date--->"+ date);
            System.out.println("date.getTime()--->"+ date.getTime());
            System.out.println("compare--->"+ (date.getTime() < 1638860282000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("users--->"+ users);
        Set<Integer> oldAddTagIds = users.stream().filter(Objects::nonNull)
                .filter(item -> item.getDate().getTime() < 1638860282000L)
                .map(InnerUser::getId).collect(Collectors.toSet());
        System.out.println("oldAddTagIds--->"+ oldAddTagIds);

        List<String> wecomExternalUserIds = new ArrayList<>();
        wecomExternalUserIds.add("a");
        wecomExternalUserIds.add("b");

        int indexOf = wecomExternalUserIds.indexOf("b");
        String wecomExternalUserId = null;
        if (indexOf < 0) {
            if (CollectionUtils.isEmpty(wecomExternalUserIds)) {
                System.out.println("last one");
            } else {
                wecomExternalUserId = wecomExternalUserIds.get(NumberUtils.INTEGER_ZERO);
            }
        } else if (indexOf + 1 >= wecomExternalUserIds.size()) {
            wecomExternalUserId = wecomExternalUserIds.get(NumberUtils.INTEGER_ZERO);
        } else {
            wecomExternalUserId = wecomExternalUserIds.get(indexOf + 1);
        }
        System.out.println("wecomExternalUserId-->"+ wecomExternalUserId);

        List<InnerUser> list2 = new ArrayList<>();
        Map<Integer, List<InnerUser>> listMap = list2.stream().collect(Collectors.groupingBy(InnerUser::getId));
        System.out.println("listMap"+ listMap);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class WelcomeMsgFeatureBO {
        List<Integer> ones;

        Map<String, String> twos;
    }

    private Integer buildParam(List<InnerUser> users, List<InnerUser> returnUsers, Integer order, Integer type) {
        if (CollectionUtils.isEmpty(users)) {
            return order;
        }
        if (NumberUtils.INTEGER_ONE.equals(type)) {
            returnUsers = users.stream().map(user -> InnerUser.builder()
                    .id(user.getId())
                    .name(user.getName()+ type)
                    .isNext(user.getIsNext())
                    .build()).collect(Collectors.toList());
            order = returnUsers.get(0).getId();
        } else if (NumberUtils.INTEGER_ZERO.equals(type)) {
            returnUsers = users.stream().map(user -> InnerUser.builder()
                    .id(user.getId())
                    .name(user.getName()+ "_" +type)
                    .isNext(user.getIsNext())
                    .build()).collect(Collectors.toList());
            order = returnUsers.get(returnUsers.size() - 1).getId();
        }
        return order;
    }

}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class InnerUser {
    Integer id;

    String name;

    Boolean isNext = Boolean.TRUE;

    Date date;
}