package cn.com.zhanss.elasticsearch.controller;

import cn.com.zhanss.elasticsearch.dto.SanguoSearchDTO;
import cn.com.zhanss.elasticsearch.service.EsSearchService;
import cn.com.zhanss.elasticsearch.vo.SearchSanguoVO;
import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 三国
 *
 * @author zhanss
 * @since 2022-08-24
 */
@RestController
@RequestMapping("/sanguo")
public class SanGuoController {

    @Resource
    private EsSearchService esSearchService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/index")
    public String createIndex() {
        String index = "sanguo_index";
        String successIndex = esSearchService.createIndex(index);
        if (index.equals(successIndex)) {
            return "success";
        }
        return "fail";
    }

    @PostMapping("/search")
    public List<SearchSanguoVO> search(SanguoSearchDTO search) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("deleted.keyword", "N"));
        boolQueryBuilder.must(QueryBuilders.termQuery("name.keyword", search.getName()));
        SearchSourceBuilder builder = new SearchSourceBuilder().query(boolQueryBuilder);
        SearchResponse searchResponse = esSearchService.search(builder);
        List<SearchSanguoVO> result = new ArrayList<>();
        if (Objects.nonNull(searchResponse.getHits())) {
            for (SearchHit hit : searchResponse.getHits()) {
                SearchSanguoVO searchSanguoVO = new Gson().fromJson(hit.getSourceAsString(), SearchSanguoVO.class);
                result.add(searchSanguoVO);
            }
        }
        return result;
    }


}
