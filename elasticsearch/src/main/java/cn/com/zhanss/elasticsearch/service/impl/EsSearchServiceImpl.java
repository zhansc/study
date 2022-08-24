package cn.com.zhanss.elasticsearch.service.impl;

import cn.com.zhanss.elasticsearch.service.EsSearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * ES服务
 *
 * @author zhanss
 * @since 2022-08-24
 */
@Component
public class EsSearchServiceImpl implements EsSearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    private static final String INDEX = "sanguo_index";

    @Override
    public String createIndex(String index) {
        if (StringUtils.isEmpty(index)) {
            return "";
        }
        CreateIndexResponse indexResponse = null;
        try {
//            if (restHighLevelClient.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT)) {
//                return "";
//            }

            indexResponse = restHighLevelClient.indices()
                    .create(new CreateIndexRequest(index), RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return indexResponse == null ? "" : indexResponse.index();
    }

    @Override
    public SearchResponse search(SearchSourceBuilder builder) {
        SearchRequest searchRequest = new SearchRequest(INDEX);
        searchRequest.source(builder);
        try {
            return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }
}
