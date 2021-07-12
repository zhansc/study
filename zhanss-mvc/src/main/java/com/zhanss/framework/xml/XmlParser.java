package com.zhanss.framework.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @desc xml解析器
 * @author zhanshuchan
 * @date 2021/6/27
 */
public class XmlParser {

    public static String getBasePackage(String xml){

        try {
            SAXReader saxReader = new SAXReader();
            InputStream inputStream = XmlParser.class.getClassLoader().getResourceAsStream(xml);

            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element element = rootElement.element("component-scan");
            Attribute attribute = element.attribute("page-package");
            return attribute.getText();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
