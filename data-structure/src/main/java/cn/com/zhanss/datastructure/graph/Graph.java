package cn.com.zhanss.datastructure.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 *
 * @author zhanss
 * @since 2022-06-15
 */
public class Graph {

    public HashMap<Integer, Node> nodes;

    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

}
