package cn.com.zhanss.wework.javaapi.parser;

import cn.com.zhanss.wework.javaapi.parser.xml.XmlParser;
import cn.com.zhanss.wework.javaapi.parser.xml.entity.WecomMsg;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desc 解析器测试
 * @author zhanshuchan
 * @date 2021/7/21
 */
public class ParserTest {

    @Resource
    private XmlParser xmlParser;

    @Test
    public void testXml() {
        String xml = "<xml><ToUserName><![CDATA[ww80b28d2f0a5db223]]></ToUserName><FromUserName><![CDATA[sys]]></FromUserName><CreateTime>1626751398</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[change_external_tag]]></Event><ChangeType><![CDATA[create]]></ChangeType><Id><![CDATA[etjmgTCQAAYIEtCEn30FEiMU9jH_HaaA]]></Id><TagType><![CDATA[tag]]></TagType></xml>";

        xmlParser.parser(xml, WecomMsg.class);

    }

    @Test
    public void testJson() {
        String json = "[{\"msgType\":\"text\",\"event\":\"MASSSENDJOBFINISH\",\"eventKey\":\"key\",\"chatId\":\"ghjke232\"}]";

        List<WecomMsg> wecomMsgs = JSONObject.parseArray(json, WecomMsg.class);
        for (WecomMsg wecomMsg : wecomMsgs) {
            System.out.println("wecomMsg--->"+ wecomMsg);
        }

    }

}
