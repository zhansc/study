package cn.com.zhanss.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @desc 任务提醒内容占位符
 * @author zhanshuchan
 * @date 2021/1/23
 */
@Getter
@AllArgsConstructor
public enum TaskNotificationVariable {

    /**
     * 今日未完成任务清单
     */
    UNFINISHED_MISSION_LIST("UNFINISHED_MISSION_LIST", "今日未完成任务清单如下："),

    /**
     * 个未完成任务
     */
    UNFINISHED_DESC("UNFINISHED_DESC", "个未完成任务"),

    /**
     * <未完成任务数量>
     */
    UNFINISHED_COUNT("UNFINISHED_COUNT", "<未完成任务数量>"),

    /**
     * <员工姓名：未完成任务>
     */
    STAFF_UNFINISHED_COUNT("UNFINISHED_COUNT", "<员工姓名：未完成任务>"),

    /**
     * 点击查看详情
     */
    CLICK_DETAIL("CLICK_DETAIL", "点击查看详情"),

    ;

    private String code;

    /**
     * 占位符描述
     */
    private String desc;

}
