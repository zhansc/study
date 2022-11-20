package cn.com.zhanss.datastructure.stack;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.BufferOverflowException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName: SearchExpression
 * @Description:
 * @Author: jianghuiyun
 * @Date: 2021/10/8 下午1:52
 * @Version: 1.0
 */
@Slf4j
public class SearchExpression extends RPN {

    @Getter
    @AllArgsConstructor
    public enum Op {
        /**
         * 顺序很重要，如：'='不能在非等于操作符前面，不然识别会出现识别错误，其他组合操作符同理
         */
        aeq("=="), neq("!="), gte(">="), lte("<="), gt(">"), lt("<"), eq("="),
        notin("notin"), in("in"), notexist("notexist"), exist("exist"), contains("contains"),
        ;
        private String key;

        public static List<Op> allOp() {
            return Arrays.asList(Op.values());
        }

        public static List<String> allOpKey() {
            return Arrays.stream(Op.values()).map(op -> op.key).collect(Collectors.toList());
        }

        public static boolean isOp(String key) {
            return allOpKey().contains(key);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Condition {
        /**
         * AND 优先级大于 OR
         */
//        AND("&&"), OR("||");
        AND("AND"), OR("OR"),
        ;
        private String key;

        public static List<String> allCondition() {
            return Arrays.stream(Condition.values()).map(condition -> condition.key).collect(Collectors.toList());
        }

        public static boolean isCondition(String key) {
            return allCondition().contains(key);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Category {
        GROUP("group"), SINGLE("single"),
        ;
        private String category;

    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        INT("int"), STRING("String"), ARRAY("array"),
        ;
        private String type;
    }

    private Map<String, Integer> ops = new HashMap<String, Integer>() {{
        this.put(Condition.AND.key, 1);
        this.put(Condition.OR.key, 1);
        this.put(Op.eq.key, 2);
        this.put(Op.neq.key, 2);
        this.put(Op.aeq.key, 2);
        this.put(Op.gte.key, 2);
        this.put(Op.lte.key, 2);
        this.put(Op.gt.key, 2);
        this.put(Op.lt.key, 2);
    }};

    /**
     * 判断是否操作数
     *
     * @param elem
     * @return
     */
    @Override
    protected boolean isOperand(String elem) {
        String e = elem.trim().toLowerCase();
        return !isOperator(e) && checkBracket(e) == 0;
    }

    /**
     * 判断是否操作符
     *
     * @param elem
     * @return
     */
    @Override
    protected boolean isOperator(String elem) {
        String e = elem.trim().toLowerCase();
        return ops.containsKey(e);
    }

    /**
     * 判断两操作符优先级
     *
     * @param op1
     * @param op2
     * @return -1 op1优先级低，0 op1和op2优先级一样，1 op1优先级高
     */
    @Override
    protected int computeOperator(String op1, String op2) {
        int a = ops.get(op1.trim().toLowerCase());
        int b = ops.get(op2.trim().toLowerCase());
        return Ints.compare(a, b);
    }

    @Override
    protected List<String> tokenize(String expression) {
        List<String> parts = new ArrayList<>();
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            parts.add(matcher.group());
        }

        return parts;
    }

    @Override
    protected Object calc(Object v1, Object v2, String operator) {
        if (operator.equals(Condition.OR.key)) {
            return ImmutableMap.of("should", Arrays.asList(convert(v1), convert(v2)), "minimum_should_match", 1);
        } else {
            return ImmutableMap.of("must", Arrays.asList(convert(v1), convert(v2)));
        }
    }

    protected Object convert(Object o) {
        if (o instanceof String) {
            return toSingleCondition((String) o);
        }

        return ImmutableMap.of("bool", o);
    }

    protected Object toSingleCondition(String s) {
        Op opt = Op.allOp().stream().filter(op -> s.contains(op.key)).findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("not found any op in expression [%s]", s)));

        String[] strings = s.split(opt.key, 2);
        String scope = strings[0].trim();
        String expect = strings[1].trim();

        if (expect.matches("^\".+\"$")) {
            expect = expect.substring(1, expect.length() - 1);
        }

        return buildSingleDSL(scope, expect, opt);
    }

    protected Object buildSingleDSL(String scope, String expect, Op op) {
        Map<String, Object> map = new HashMap<>();
        switch (op) {
            case eq:
                if (!findField(scope, true, f -> {
                    map.put("match", ImmutableMap.of(f, expect));
                })) {
                    findField(scope, false, f -> {
                        map.put("bool", ImmutableMap.of("filter", ImmutableMap.of("term", ImmutableMap.of(f, expect))));
                    });
                }
                break;
            case aeq:
                if (!findField(scope, false, f -> {
                    map.put("bool", ImmutableMap.of("filter", ImmutableMap.of("term", ImmutableMap.of(f, expect))));
                })) {
                    findField(scope, true, f -> {
                        map.put("match_phrase", ImmutableMap.of(f, expect));
                    });
                }
                break;
            case neq:
                if (!findField(scope, true, f -> {
                    ImmutableMap of = ImmutableMap.of("match", ImmutableMap.of(f, "value"));
                    map.put("bool", ImmutableMap.of("must_not", of));
                })) {
                    findField(scope, false, f -> {
                        ImmutableMap of = ImmutableMap.of("term", ImmutableMap.of(f, ImmutableMap.of("value", expect)));
                        map.put("bool", ImmutableMap.of("must_not", of));
                    });
                }
                break;
            case gte:
                findField(scope, false, f -> {
                    map.put("range", ImmutableMap.of(f, ImmutableMap.of("gte", expect)));
                });
                break;
            case gt:
                findField(scope, false, f -> {
                    map.put("range", ImmutableMap.of(f, ImmutableMap.of("gt", expect)));
                });
                break;
            case lt:
                findField(scope, false, f -> {
                    map.put("range", ImmutableMap.of(f, ImmutableMap.of("lt", expect)));
                });
                break;
            case lte:
                findField(scope, false, f -> {
                    map.put("range", ImmutableMap.of(f, ImmutableMap.of("lte", expect)));
                });
                break;
            default:
                throw new UnsupportedOperationException("暂不支持的语法：" + op.key);
        }

        if (map.isEmpty()) {
            throw new IllegalArgumentException(String.format("不支持的搜索域：%s", scope));
        }

        return map;
    }

    /**
     * 查找映射字段名
     *
     * @param input
     * @param analyzed 是否分析字段，true时为字段，false时为字段.raw
     * @param consumer
     * @return
     */
    private boolean findField(String input, boolean analyzed, Consumer<String> consumer) {

        return true;
    }

    /**
     * 表达式的正则校验
     */
    private Pattern pattern = Pattern.compile("");

    @Override
    public Object calculate(String expression) {
        Map<String, Object> o;
        if (expression.contains(Condition.OR.key) || expression.contains(Condition.AND.key)) {
            o = new LinkedHashMap<>();
            try {
                o.put("query", ImmutableMap.of("bool", super.calculate(expression)));
            } catch (EmptyStackException e) {
                throw new IllegalArgumentException("参照语法说明输入正确表达式");
            }

        } else {
            o = new LinkedHashMap<>();
            o.put("query", toSingleCondition(expression));
        }
        return o;
    }

    public static final Map<String, Object> evaluate(String expression) {
        return (Map<String, Object>) new SearchExpression().calculate(expression);
    }

    public static void main(String[] args) {
        /**
         * 解析表达式
         * 方案一：字段、操作符、value值都是数据库查出来的，对数据的格式可控，表达式的格式是自动生成的，
         * 所以可以根据空格来拆分表达式字符串（不允许操作用户手动修改表达式）
         * 方案二：
         */

        String exp = " alarmTag in [\"Miner\",\"event_wannamine\"] OR category != \"/Audit\" AND destHostName notexist OR attacker exist";
        String exp1 = "alarmTag in [\"Miner\",\"勒索软件\"] OR destAddress notin [\"192.168.1.250\"]";
        String exp2 = "alarmTag in [\"Miner\"] AND attacker exist OR destAddress notin [\"10.20.171.96\"]";
        String exp3 = "victim contains \"10.20.152.11\" AND attacker notexist";
//        Map<String, Object> evaluate = evaluate(exp);
        KqlGroupDTO orKqlGroup = new KqlGroupDTO();
        boolean bingo = parseWithSpace(exp, orKqlGroup);
        System.out.println(bingo);
    }

    /**
     * 空格
     */
    public static final String BLANK_SPACE = " ";
    /**
     * 双引号
     */
    public static final String DOUBLE_QUOTATION_MARKS = "\"";
    /**
     * 方括号左边
     */
    public static final String LEFT_BRACKET = "[";
    /**
     * 方括号右边
     */
    public static final String RIGHT_BRACKET = "]";

    /**
     * 校验KQL表达式语法格式
     * @param exp
     * @return
     */
    public static boolean parseWithSpace(String exp, KqlGroupDTO kqlGroup) {
        // 需要匹配的字段
        List<String> toQueryFieldList = new ArrayList<>();

        // 根据空格拆分KQL表达式
        String[] splits = exp.split(BLANK_SPACE);
        // 去除空格
        List<String> itemList = Arrays.stream(splits).filter(StringUtils::isNotEmpty).collect(Collectors.toList());

        // 根据条件将关键词列表拆分单个表达式组
        List<List<String>> andItemList = new ArrayList<>();
        List<List<String>> orItemList = new ArrayList<>();
        List<String> subItemList = null;
        String lastOp = "";
        for (String item : itemList) {
            // AND 优先级大于 OR
            if (Condition.AND.key.equals(item) || (Condition.OR.key.equals(item) && Condition.AND.key.equals(lastOp))) {
                lastOp = item;
                // 表达式开头直接提供条件Op，没有提供字段和属性值
                if (CollectionUtils.isEmpty(subItemList)) {
                    throw new BufferOverflowException("KQL语法解析错误，请输入完整表达式！");
                }
                andItemList.add(subItemList);
                subItemList = null;
            } else if (Condition.OR.key.equals(item)) {
                lastOp = item;
                if (CollectionUtils.isEmpty(subItemList)) {
                    throw new BufferOverflowException("KQL语法解析错误，请输入完整表达式！");
                }
                orItemList.add(subItemList);
                subItemList = null;
            } else {
                if (subItemList == null) {
                    subItemList = new ArrayList<>();
                } else if (subItemList.size() >= 3) {
                    // 字段名+操作符+属性值，不超过三项
                    throw new BufferOverflowException("KQL语法解析错误，请输入正确的表达式！");
                }
                subItemList.add(item);
            }
        }
        // 最后一组仅提供条件Op，没有提供字段和属性值
        if (CollectionUtils.isEmpty(subItemList)) {
            throw new BufferOverflowException("KQL语法解析错误，请输入完整表达式！");
        }
        // 最后一组
        if (Condition.AND.key.equals(lastOp)) {
            andItemList.add(subItemList);
        } else if (Condition.OR.key.equals(lastOp)) {
            orItemList.add(subItemList);
        }

        // 解析单个KQL表达式
        KqlGroupDTO andKqlGroup = getAndKqlGroup(andItemList);

        // OR条件
        List<KqlGroupDTO> orKqlGroups = Lists.newArrayList(andKqlGroup);
        for (List<String> singleKql : orItemList) {
            orKqlGroups.add(parseKqlGroup(singleKql, Condition.OR));
        }
        kqlGroup.setCategory(Category.GROUP.category);
        kqlGroup.setCondition(Condition.OR.key);
        kqlGroup.setKqlGroups(orKqlGroups);
        return true;
    }

    private static KqlGroupDTO getAndKqlGroup(List<List<String>> andItemList) {
        // AND 条件
        KqlGroupDTO subKqlGroup = new KqlGroupDTO();
        subKqlGroup.setCategory(Category.GROUP.category);
        subKqlGroup.setCondition(Condition.AND.key);
        List<KqlGroupDTO> subKqlGroups = new ArrayList<>();
        for (List<String> singleKql : andItemList) {
            subKqlGroups.add(parseKqlGroup(singleKql, Condition.AND));
        }
        subKqlGroup.setKqlGroups(subKqlGroups);
        return subKqlGroup;
    }

    public static KqlGroupDTO parseKqlGroup(List<String> singleKql, Condition condition) {
        // 从中台查的可搜索的字段列表
        List<String> fieldList = Arrays.asList("alarmTag","destHostName", "category", "attacker");
        if (singleKql.size() <= 1) {
            log.error("KQL语法解析错误，请输入完整表达式！");
            throw new BufferOverflowException("KQL语法解析错误，请输入完整表达式！");
        }
        String field;
        KqlGroupDTO kqlGroup = new KqlGroupDTO();
        kqlGroup.setCategory(Category.SINGLE.category);
        kqlGroup.setCondition(condition.key);
        for (int single = 0; single < singleKql.size(); single ++) {
            field = singleKql.get(single);
            if (single == 1) {
                boolean isOp = Op.isOp(field);
                if (!isOp) {
                    log.error("KQL语法解析错误，请输入完整或正确的表达式！");
                    throw new BufferOverflowException("KQL语法解析错误，请输入完整或正确的表达式！");
                }
                kqlGroup.setOperator(field);
            } else if (single == 2) {
                // value值不做校验，若修改了所选值，查询为空即可
                kqlGroup.setValue(field);
            } else {
                if ((!fieldList.contains(field))) {
                    log.error("不支持自定义字段 ( '" + field + "' ) 查询！");
                    throw new BufferOverflowException("不支持自定义字段 ( '" + field + "' ) 查询！");
                }
                kqlGroup.setField(field);
            }
        }
        return kqlGroup;
    }

    @Data
    @NoArgsConstructor
    static
    class KqlGroupDTO {
        /**
         * 层次类型
         * @see Category
         */
        private String category;
        /***
         * 条件
         * @see Condition
         */
        private String condition;
        /**
         * 字段
         */
        private String field;
        /**
         * 操作符
         * @see Op
         */
        private String operator;
        /**
         * 数据类型
         * @see Type
         */
        private String type;
        /**
         * 属性值
         */
        private Object value;

        /**
         * 子条件
         */
        List<KqlGroupDTO> kqlGroups;
    }


    public static boolean parseWithSpace1(String exp, List<KqlGroupDTO> kqlGroups) {
        // 从中台查的可搜索的字段列表
        List<String> fieldList = Arrays.asList("alarmTag","destHostName", "category");
        // 需要匹配的字段
        List<String> toQueryFieldList = new ArrayList<>();

        // alarmTag in [\"Miner\",\"event_wannamine\"] OR category != \"/Audit\" AND destHostName notexist
        // 根据空格拆分KQL表达式
        String[] splits = exp.split(BLANK_SPACE);
        // 去除空格
        List<String> itemList = Arrays.stream(splits).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        // [alarmTag, in, [\"Miner\",\"event_wannamine\"], OR, category, !=, \"/Audit\", AND, destHostName, notexist]

        // 根据条件将关键词列表拆分单个表达式组
        Map<String, List<String>> splitConditionMap = new HashMap<>();
        List<String> subItemList = new ArrayList<>();
        String lastItem = "";
        for (String item : itemList) {
            boolean isCondition = Condition.isCondition(item);
            lastItem = item;
            if (isCondition) {
                splitConditionMap.putIfAbsent(item, subItemList);
                // 清空
                subItemList.clear();
            } else {
                subItemList.add(item);
            }
        }
        splitConditionMap.putIfAbsent(lastItem, subItemList);
        // [ [alarmTag, in, [\"Miner\",\"event_wannamine\"]],
        //   [category, !=, \"/Audit\"],
        //   [destHostName, notexist]
        // ]

        // 解析单个KQL表达式
        if (kqlGroups == null) {
            kqlGroups = new ArrayList<>();
        }
        for (Map.Entry<String, List<String>> entry : splitConditionMap.entrySet()) {
            String condition = entry.getKey();
            List<String> singleKql = entry.getValue();
            if (singleKql.size() <= 1) {
                log.error("KQL语法解析错误，请输入完整表达式！");
                return false;
            }
            String field;
            KqlGroupDTO kqlGroup = new KqlGroupDTO();
            kqlGroup.setCondition(condition);
            for (int single = 0; single < singleKql.size(); single ++) {
                field = singleKql.get(single);
                if (single == 1) {
                    boolean isOp = Op.isOp(field);
                    if (!isOp) {
                        log.error("KQL语法解析错误，请输入完整表达式！");
                        return false;
                    }
                    kqlGroup.setOperator(field);
                } else if (single == 2) {
                    // value值不做校验，若修改了所选值，查询为空即可
                    kqlGroup.setValue(field);
                } else {
                    if ((!fieldList.contains(field))) {
                        log.error("不支持自定义字段 ( '" + field + "' ) 查询！");
                        return false;
                    }
                    kqlGroup.setField(field);
                }
            }
            kqlGroups.add(kqlGroup);
        }
        return true;
    }
}
