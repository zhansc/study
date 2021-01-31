package cn.com.zhanss.lambda;

import com.alibaba.fastjson.JSONObject;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @desc 测试
 * @author zhanshuchan
 * @date 2020/10/13
 */
public class MyTest {

    private WXBizMsgCrypt wxBizMsgCrypt;

    private static final String WEB_HOOK_KEY_STR = "key=";

    private static final String NOTIFICATION_WRAP = "\n";

    private static final String NOTIFICATION_COLON = "：";

    private static final String UNFINISHED_MISSIONS = "<a href=\"https://qiwei.youzan.com/wa/wecom-app/dashboard?appId=%s#/operation/staff-mission\">%s</a>";

    @Test
    public void testCollection() {
        Long tagKey = 20201014L;
        Map<String, Integer> fansCountMap = new HashMap<>();
        fansCountMap.put("20201014_1", 1);
        fansCountMap.put("20201014_2", 2);
        fansCountMap.put("20201014_3", 3);
        fansCountMap.put("20201014_4", 4);
        fansCountMap.put("20201014_5", 5);

        Integer count = fansCountMap.getOrDefault(String.format("%s_%s", tagKey, 2), 0);
        System.out.println("count1--->"+ count);

        count = fansCountMap.getOrDefault(String.format("%s_%s", tagKey, 3), 0);
        System.out.println("count2--->"+ count);

        count = fansCountMap.getOrDefault(String.format("%s_%s", tagKey, 6), 0);
        System.out.println("count6--->"+ count);

        String str = "java,python,c,c++";
        String str1 = "j,a,v,a";

        Optional<String> ii = Stream.of(str.split(",")).peek(s-> {
            s = String.format("%s%s", s, "-top");
            System.out.println("s-->"+ s);
        }).filter(s->{
            s = s.concat("-top");
            return s.length()==7;
        }).findFirst();
        System.out.println("ii->"+ ii.orElse(null));

        System.out.println("----------------------------");
        Stream.of("one", "two", "three", "four")
               .filter(e -> e.length() > 3)
               .peek(e -> System.out.println("Filtered value: " + e))
               .map(String::toUpperCase)
               .peek(e -> System.out.println("Mapped value: " + e))
               .collect(Collectors.toList()).forEach(System.out::println);

        Set<String> oldTags = new HashSet<>();
        oldTags.add("1234");
        oldTags.add("5678");
        oldTags.add("901234");
        System.out.println("oldTags--->" +oldTags);

        List<String> tags = new ArrayList<>();
        Set<String> newTags = tags.stream().filter(Objects::nonNull).collect(Collectors.toSet());
        newTags.add("901234");
        newTags.add("901233");

        Set<String> removeTags = (Set<String>) ((HashSet<String>) oldTags).clone();
        Set<String> addTags = (Set<String>) ((HashSet<String>) newTags).clone();
        // 历史标签比新增标签多的要删除
        removeTags.removeAll(newTags);
        // 新增标签比历史标签多的要新增
        addTags.removeAll(oldTags);
        System.out.println("removeTags--->"+ removeTags);
        System.out.println("addTags--->"+ addTags);

        try {
            this.wxBizMsgCrypt = new WXBizMsgCrypt("aSjQ2se7xWmn5fLr", "kKA5DuWoBQI6xZQ3IJE71ntglJ2F7mqgUObn8XAxE8t", "wxd021c71421c73c46");

//            String decodeStr = wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echoStr);

        } catch (AesException e) {
            throw new RuntimeException("construct wxBizMsgCrypt fail", e.getCause());
        }

        Set<String> strs = new HashSet<>();
        strs.add("12345");
        System.out.println("result--->"+ strs.add("abcd"));
        strs.add("abcdefg");
        System.out.println("result--->"+ strs.add("abcd"));

        Map<String, Object> numberCon = new HashMap<>();
        numberCon.put("aa", 0);
        numberCon.put("bb", 124);
        numberCon.put("cc", "10.9");
        System.out.println("numberCon--->"+ Long.parseLong(numberCon.get("aa").toString()));

        AtomicLong countPurchaseAmount = new AtomicLong(0);
        Long count1 = countPurchaseAmount.get();
        System.out.println("count1---->"+ count1);

        Double baiy = 3.0 ;
        double baiz = 3 ;
        double fen = baiy / baiz;
        System.out.println("fen---->"+ fen);
        DecimalFormat df1 = new DecimalFormat("##.00%");
        String baifenbi = df1.format(fen);
        System.out.println("percentage---->"+ baifenbi);

        Long longTime = 12L;
        System.out.println("longTime--->"+ Instant.ofEpochSecond(longTime).toEpochMilli());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String format = sdf.format(new Date());
        try {
            System.out.println("date--->"+ sdf.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int a = 10;
        int b = 5;
        Double round = new Double(Math.round(a/b)/100.0);
        System.out.println("round--->"+ round);

        DecimalFormat df2  = new DecimalFormat("###.00");
        System.out.println("df2--->"+ df2.parse("3.00", new ParsePosition(0)));

        StringBuilder stringBuilder = new StringBuilder("abc");
        stringBuilder.insert(0, "12");
        System.out.println("stringBuilder--->"+ stringBuilder.toString());

        Person person = new Person();
        String percentage = percentage(null, 132L, false, true);
        System.out.println("percentage111---->"+ percentage);
        person.setName(percentage);
        numberCon.put("percentage", percentage);
        numberCon.put("percentage1", null);
        System.out.println("person---->"+ person.toString());
        System.out.println("numberCon---->"+ numberCon +"<---->"+ numberCon.get("percentage1"));
        System.out.println("00".compareTo("0"));
        System.out.println(0>0);

        List<Integer> daysOfWeek = new ArrayList<>();
        daysOfWeek.add(1);
        daysOfWeek.add(6);
        List<Long> executeTimes = new ArrayList<>();
        Integer hour = 5;
        Integer minute = 12;
        Calendar calendar = Calendar.getInstance();
        daysOfWeek.forEach(week -> {
            // 一个星期的第一天是周一
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            System.out.println("calendar.getWeek--->"+ calendar.get(Calendar.DAY_OF_WEEK));

            calendar.set(Calendar.DAY_OF_WEEK, week + 1);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            Long executeTime = calendar.getTime().getTime();
            if (System.currentTimeMillis() > executeTime) {
                calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + 1);
                executeTimes.add(calendar.getTime().getTime());
                calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
            } else {
                executeTimes.add(calendar.getTime().getTime());
            }
            System.out.println("calendar---->"+ calendar);
        });
        System.out.println("executeTimes--->"+ executeTimes.toString());

        String UNFINISHED_COUNT = "<未完成任务数量>";
        String CLICK_DETAIL = "点击查看详情";
        String UNFINISHED_MISSION = "<a href=\"https://qiwei.youzan.com/wa/wecom-app/dashboard#/operation/staff-mission\">%s</a>";
        StringBuilder personalContent = new StringBuilder("今日还有<未完成任务数量>个未完成任务--update");

        int indexOfUnFinished = personalContent.indexOf(UNFINISHED_COUNT);

        personalContent.insert(indexOfUnFinished, indexOfUnFinished)
                .delete(personalContent.indexOf(UNFINISHED_COUNT), personalContent.indexOf(UNFINISHED_COUNT) + UNFINISHED_COUNT.length())
                .append(String.format("\n" + UNFINISHED_MISSION, CLICK_DETAIL));
        System.out.println("personal content---->"+ personalContent+ "\n\n");

        String STAFF_UNFINISHED_COUNT = "<员工姓名：未完成任务>";
        String UNFINISHED_DESC = "个未完成任务";
        StringBuilder groupContent = new StringBuilder("今日未完成清单如下：\n<员工姓名：未完成任务>\n请未完成的同事尽快处理完成\n<@相关员工>--update");
        for (int i = 2; i < 4; i ++) {
            int indexOfStaffUnFinished = groupContent.indexOf(STAFF_UNFINISHED_COUNT);
            String TARGET_STAFF = "<@相关员工>";
            String staffName = "詹书禅" + i;
            groupContent.insert(indexOfStaffUnFinished, staffName+ "：" + String.format(UNFINISHED_MISSION + "\n", i + UNFINISHED_DESC));
            int indexOfTargetStaff = groupContent.indexOf(TARGET_STAFF);
            groupContent.insert(indexOfTargetStaff, "@".concat(staffName));
            System.out.println("group content---->"+ groupContent);
        }

        System.out.println("LocalDateTime.now()---->"+ LocalDateTime.now());

        StringBuilder content = new StringBuilder("<你好>，是的我好<你好>");
        content.append("Hi").append("<你好>");
        System.out.println("content---->"+ content.toString().replace("<你好>", ""));


    }

    private String percentage(Object achievedCount, Long endFlowCount, boolean isrmb, boolean isPercentage) {
        if (endFlowCount == null || endFlowCount == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (achievedCount == null) {
            stringBuilder.append("0");
        } else {
            double percentage = 0.0;
            if (achievedCount instanceof Number) {
                percentage = Double.valueOf(achievedCount.toString()) / endFlowCount;
                // 分转元
                percentage = isrmb ? percentage / 100 : percentage;
                // 转百分比
                percentage = isPercentage ? percentage * 100 : percentage;
            }
            BigDecimal bigDecimal = new BigDecimal(percentage);
            // 小数点后保留2位【四舍五入】
            String percentageScale = bigDecimal.setScale(percentage <= 0 ? 0 : 2, BigDecimal.ROUND_HALF_UP).toString();
            String[] scales = percentageScale.split("\\.");
            if (scales.length > 1) {
                Integer suffix = Integer.valueOf(scales[1]);
                if (suffix <= 0) {
                    percentageScale = scales[0];
                }
            }
            stringBuilder.append(percentageScale);
        }
        return isPercentage ? stringBuilder.append("%").toString() : stringBuilder.toString();
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 30, 0, 0, 0);
        System.out.println("localDateTime.getEpochSecond --->"+ localDateTime.toInstant(ZoneOffset.of("+8")).getEpochSecond());
        LocalDateTime localDateTime1 = localDateTime.minusDays(29);
        long toEpochMilli1 = localDateTime1.toInstant(ZoneOffset.of("+8")).getEpochSecond();
        System.out.println("localDateTime1--->"+ localDateTime1+"，毫秒--->"+ toEpochMilli1);
        Long startTime = localDateTime1.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
        System.out.println("startTime--->"+ startTime);

        LocalDate endLocalDate =  LocalDate.now();
        System.out.println("endLocalDate--->"+ endLocalDate);
        long startl = endLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().getEpochSecond();
        System.out.println("today--->"+ startl);

        LocalDate startLocalDate = endLocalDate.minusDays(3);
        long endl = startLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().getEpochSecond();
        System.out.println("endl--->"+ endl);

        Long time = 1604851200000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String timeStr = sdf.format(1605196799);
        System.out.println("timeStr--->"+ timeStr);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.OCTOBER, 1);
        calendar.setTime(new Date());
        System.out.println("2020-9-1--->"+ calendar.getTime().getTime());
        System.out.println("now longtime--->"+ System.currentTimeMillis());

        Date date = new Date();
        System.out.println("date--->"+ date.getTime());

        System.out.println("date str--->"+ new Date(1605163241583L));
        Long longDate = 1605166486685L;
        Date date1 = new Date(longDate);
        System.out.println("date long--->"+ date1.getSeconds());
        System.out.println("date str--->"+ date1);

        Date currentTime = new Date();
        LocalDateTime toromorrowTime = LocalDateTime.ofInstant(currentTime.toInstant(), ZoneId.systemDefault())
                .minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        LocalDateTime localDateTime0 = toromorrowTime.minusDays(29);
        System.out.println("localDateTime0 str-->"+ localDateTime0);
        // 秒级时间戳
        System.out.println("localDateTime0 long-->"+ localDateTime0.toInstant(ZoneOffset.ofHours(8)).getEpochSecond());

        System.out.println("toromorrowTime str-->"+ toromorrowTime);
        System.out.println("toromorrowTime long-->"+ toromorrowTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = formatter.format(toromorrowTime);
        System.out.println("format-->"+ format);
        // 秒级时间戳转Date
        String parse = formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1606060800000L), ZoneId.systemDefault()));
        System.out.println("parse12345-->"+ parse);

        String format1 = formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTime.getTime()), ZoneId.systemDefault()));
        System.out.println("format1--->"+ format1);

        // 毫秒转秒
        long epochSecond = Instant.ofEpochMilli(1605186174001L).getEpochSecond();
        System.out.println("毫秒转秒epochSecond--->"+ epochSecond);

        LocalDateTime localDateTime2 = localDateTime.minusDays(2);
        long toEpochMilli2 = localDateTime2.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        long sysNow = System.currentTimeMillis();
        System.out.println("now --->"+ sysNow+ "---<---->toEpochMilli2--->"+ localDateTime2);

        Duration duration = Duration.between(Instant.ofEpochMilli(toEpochMilli2), Instant.ofEpochMilli(sysNow));
        long days = duration.toDays();
        System.out.println("now --->"+ days);
        System.out.println("compareTo--->"+ localDateTime.compareTo(localDateTime2));

        System.out.println("LocalDateTime.now().getHour()--->"+ LocalDateTime.now().getHour());

        System.out.println("JSONObject.toJSONString-->"+ JSONObject.toJSONString(null));


        List<Person> persons = new ArrayList<>();
        for (int i = 1; i < 6; i ++) {
            Person person = Person.builder()
                    .id(i)
                    .age(i)
                    .name("name"+i)
                    .order(0)
                    .score(23.0 + i)
                    .build();
            persons.add(person);
        }
        System.out.println("persons--->"+ persons);
        List<Person> personList = persons.stream()
                .sorted(Comparator.comparing(Person::getOrder).reversed()
                    .thenComparing(Person::getScore).reversed())
                .collect(Collectors.toList());
        System.out.println("personList--->"+ personList);
        System.out.println(toEpoch(23L)+ "<----epoch2Days--->"+ epoch2Days(toEpoch(23L)));

        System.out.println(millis2Epoch(1606665600000L)+ "<----epoch2Millis--->"+ epoch2Millis(1610940402L));

        double percentage = 0.0;
        System.out.println("percentage>0--->"+ (percentage <= 0.00));


    }

    private Long millis2Epoch(Long source) {
        if (source == null) {
            return null;
        }
        // 毫秒级时间转秒级时间
        long epochSecond = Instant.ofEpochMilli(source).getEpochSecond();
        System.out.println("millis2Epoch--->"+ LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.systemDefault()));
        return epochSecond;
    }

    private Long epoch2Millis(Long source) {
        if (source == null) {
            return null;
        }
        // 秒级时间转毫秒级时间戳
        long toEpochMilli = Instant.ofEpochSecond(source).toEpochMilli();
        System.out.println("toEpochMilli--->"+ LocalDateTime.ofInstant(Instant.ofEpochSecond(source), ZoneId.systemDefault()));
        return toEpochMilli;
    }

    private Long toEpoch(Long source) {
        if (source == null) {
            return null;
        }
        LocalDateTime beforeYesterday = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                .minusDays(source).withHour(0).withMinute(0).withSecond(0).withNano(0);
        // 将天数转换为数据表中对应的秒级时间
        return beforeYesterday.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();
    }

    private Long epoch2Days(Long source) {
        if (source == null) {
            return null;
        }
        LocalDateTime today = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        // 将天数转换为数据表中对应的秒级时间
        LocalDateTime daysBefore = LocalDateTime.ofInstant(Instant.ofEpochSecond(source), ZoneId.systemDefault());
        return Duration.between(daysBefore, today).toDays();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Person {
        private Integer id;

        private String name;

        private Integer order;

        private Integer age;

        private Double score;
    }

    @Test
    public void testNotification() {
        this.callbackTaskNotification(0, "今日未完成任务清单如下：\n" +
                "<员工姓名：未完成任务>\n" +
                "<@相关员工>\n" +
                "请未完成的尽快处理");

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("tomorrow--->"+tomorrow.minusDays(27).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println("tomorrow after--->"+LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public Boolean callbackTaskNotification(Integer type, String content) {
        List<String> staffIdList = new ArrayList<>();
        staffIdList.add("詹书禅");
        staffIdList.add("祝锴");

        StringBuilder contentBuilder = new StringBuilder(content);
        for (String staffName : staffIdList) {
            Integer missionCount = 10;
            if (0 == type) {
                contentBuilder = new StringBuilder(content);
                int indexOfUnFinished = content.indexOf(TaskNotificationVariable.UNFINISHED_COUNT.getDesc());
                String missionCountStr = missionCount + TaskNotificationVariable.UNFINISHED_DESC.getDesc();
                // 个人提醒消息
                if (indexOfUnFinished > -1) {
                    contentBuilder.insert(indexOfUnFinished, missionCountStr);
                }
                contentBuilder.append(NOTIFICATION_WRAP.concat(String.format(UNFINISHED_MISSIONS, "1234578654dnaljfd", TaskNotificationVariable.CLICK_DETAIL.getDesc())));
                System.out.println("contentBuilder--->"+ contentBuilder.toString().replaceAll(TaskNotificationVariable.UNFINISHED_COUNT.getDesc(), ""));

            } else if (type == 1) {
                // 群提醒
                int indexOfStaffUnFinished = content.indexOf(TaskNotificationVariable.STAFF_UNFINISHED_COUNT.getDesc());
                if (indexOfStaffUnFinished <= -1) {
                    continue;
                }
                String staffUnFinished = staffName
                        .concat(NOTIFICATION_COLON)
                        .concat(missionCount + TaskNotificationVariable.UNFINISHED_DESC.getDesc())
                        .concat(NOTIFICATION_WRAP);
                contentBuilder.insert(indexOfStaffUnFinished, staffUnFinished);
            }
        }
        return Boolean.TRUE;
    }



}
