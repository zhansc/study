package cn.com.zhanss.springmvc.service;

import cn.com.zhanss.entity.WecomMsg;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;


/**
 * @desc 用户
 * @author zhanshuchan
 * @date 2021/6/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "wecomMarshaller")
    private Unmarshaller unmarshaller;

    @Override
    public void sayHello() {

        String xml = "<xml><ToUserName><![CDATA[ww80b28d2f0a5db223]]></ToUserName><FromUserName><![CDATA[sys]]></FromUserName><CreateTime>1626751398</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[change_external_tag]]></Event><ChangeType><![CDATA[create]]></ChangeType><Id><![CDATA[etjmgTCQAAYIEtCEn30FEiMU9jH_HaaA]]></Id><TagType><![CDATA[tag]]></TagType></xml>";
        try {
            WecomMsg wecomMsg = (WecomMsg) unmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(xml.getBytes())));
            System.out.println("wecomMsg----->"+ wecomMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("say hello!!");
    }
}
