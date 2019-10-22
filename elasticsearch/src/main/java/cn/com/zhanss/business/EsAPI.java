package cn.com.zhanss.business;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * 测试ES API
 *
 * @author zhanss
 * @since 2019/10/3
 */
public class EsAPI {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        Map<String, Object> sourceMap = new Hashtable<String, Object>();
        sourceMap.put("id", "1");
        sourceMap.put("name", "刘备");
        sourceMap.put("title", "三国");
        sourceMap.put("content", "东汉末年...");
        String esIndexName = "三国";
        String esTypeName = "蜀";
        IndexRequest indexRequest = new IndexRequest(esIndexName, esTypeName);
        indexRequest.id("1").source(sourceMap);
        // 指定索引名称
        try {
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void deleteIndex() {

    }

}
