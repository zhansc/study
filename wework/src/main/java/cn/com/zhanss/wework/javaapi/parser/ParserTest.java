package cn.com.zhanss.wework.javaapi.parser;

import cn.com.zhanss.wework.javaapi.parser.xml.XmlParser;
import cn.com.zhanss.wework.javaapi.parser.xml.entity.WecomMsg;
import org.junit.Test;

import javax.annotation.Resource;

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

}
