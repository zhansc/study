package cn.com.zhanss.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * ES服务
 *
 * @author zhanss
 * @since 2022-08-24
 */
public interface EsSearchService {

    String createIndex(String index);

    SearchResponse search(SearchSourceBuilder builder);
}
