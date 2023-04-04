package cn.com.zhanss.datastructure.linkedlist;

/**
 * 英雄节点
 *
 * @author zhanss
 * @since 2019/10/31
 */
public class HeroNode {
    // 英雄排名
    public int no;

    // 英雄名字
    public String name;

    // 英雄昵称
    public String nickName;

    // 下一位
    public HeroNode next;

    public HeroNode (){}

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
