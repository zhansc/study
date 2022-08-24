package cn.com.zhanss.elasticsearch.controller;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Map;

/**
 * 测试ES API
 *
 * @author zhanss
 * @since 2019/10/3
 */
public class EsAPI {

    private RestHighLevelClient client;

//    @Before
    public void ping() {
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.2.131", 9200, "http"))
        );

        System.out.println("PONG!");
    }

    /**
     * 创建索引
     */
    @Test
    public void createIndex() {
        RestClientBuilder builder = RestClient.builder(new HttpHost("192.168.2.131", 9200, "http"));
        RestHighLevelClient esClient = new RestHighLevelClient(builder);
        Map<String, Object> sourceMap = new Hashtable<>();
        sourceMap.put("id", "1");
        sourceMap.put("name", "刘备");
        sourceMap.put("title", "三国");
        sourceMap.put("content", "东汉末年...");
        String esIndexName = "sanguo_index";
        CreateIndexRequest indexRequest = new CreateIndexRequest(esIndexName);
        indexRequest.setMasterTimeout(TimeValue.timeValueMillis( 3 * 60 * 1000));
        indexRequest.source(sourceMap);
        // 指定索引名称
        try {
            CreateIndexResponse createIndexResponse = esClient.indices().create(indexRequest, RequestOptions.DEFAULT);
            System.out.println(createIndexResponse.index());

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    @Test
    public void deleteIndex() {

    }

}
