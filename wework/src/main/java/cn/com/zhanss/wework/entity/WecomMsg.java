package cn.com.zhanss.wework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/***
 * @author xutao
 * @date 8:11 AM 2020/10/24
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement //表示student是一个跟元素
public class WecomMsg {
    /**
     * 企业微信CorpID
     */
    private String from;

    /**
     * 此事件该值固定为sys，表示该消息由系统生成
     */
    private String target;

    private Long createTime;

    private MsgType msgType;

    private Event event;

    private String eventKey;

    /**
     * 企业应用的id，整型。可在应用的设置页面查看
     */
    private Integer agentId;

    /**
     * 企业微信事件
     */
    private String changeType;

    /**
     * 部门ID
     */
    @XmlElement //gender作为student的一个子元素
    private Long id;

    @XmlTransient
    public Long getId() {
        return id;
    }

    @XmlTransient
    public String getTag() {
        return tag;
    }

    private String name;
    /**
     * 父部门ID
     */
    private Long parentId;
    /**
     * 部门排序
     */
    private Long order;

    /**
     * 企业微信userid
     */
    private String userId;

    /**
     * 企业外部联系人externalUserId
     */
    private String externalUserId;

    /**
     * 企业微信NewUserID
     */
    private String newUserId;
    /**
     * 用于识别添加此用户的渠道
     */
    private String state;
    /**
     * 欢迎语
     */
    private String welcomeCode;
    /**
     * 接替失败的原因, customer_refused - 客户拒绝， customer_limit_exceed - 接替成员的客户数达到上限
     */
    private String failReason;
    /**
     * 群ID
     */
    private String chatId;
    /**
     * 变更详情。目前有以下几种：
     * add_member : 成员入群
     * del_member : 成员退群
     * change_owner : 群主变更
     * change_name : 群名变更
     * change_notice : 群公告变更
     */
    private String updateDetail;
    /**
     * 当是成员入群时有值。表示成员的入群方式
     * 0 - 由成员邀请入群（包括直接邀请入群和通过邀请链接入群）
     * 3 - 通过扫描群二维码入群
     */
    private Integer joinScene;
    /**
     * 当是成员退群时有值。表示成员的退群方式
     * 0 - 自己退群
     * 1 - 群主/群管理员移出
     */
    private Integer quitScene;
    /**
     * 当是成员入群或退群时有值。表示成员变更数量
     */
    private Integer memChangeCnt;
    /**
     * 成员部门列表，仅返回该应用有查看权限的部门id
     */
    private String department;
    /**
     * 表示所在部门是否为上级，0-否，1-是，顺序与Department字段的部门逐一对应
     */
    private String isLeaderInDept;

    private String mobile;
    /**
     * 座机
     */
    private String telephone;

    private String position;

    private String gender;

    private String email;
    /**
     * 激活状态：1=已激活 2=已禁用 4=未激活 已激活代表已激活企业微信或已关注微工作台（原企业号） 5=成员退出
     */
    private String status;

    /**
     * 头像url
     */
    private String avatar;
    /**
     * 别名
     */
    private String alias;

    private String address;
    /**
     * 异步任务id
     */
    private String jobId;
    /**
     * 标签TagId
     */
    private Long tagId;
    /**
     * 标签或标签组的ID
     */
    private String tag;
    /**
     * 标签类型
     */
    private String tagType;
    /**
     * 标签中新增的成员userid列表，用逗号分隔
     */
    private String addUserItems;
    /**
     * 标签中删除的成员userid列表，用逗号分隔
     */
    private String delUserItems;
    /**
     * 标签中新增的部门id列表，用逗号分隔
     */
    private String addPartyItems;
    /**
     * 标签中删除的部门id列表，用逗号分隔
     */
    private String delPartyItems;
    /**
     * 操作类型，字符串，目前分别有：sync_user(增量更新成员)、 replace_user(全量覆盖成员）、invite_user(邀请成员关注）、replace_party(全量覆盖部门)
     */
    private String jobType;

    private String errCode;

    private String errMsg;
}
