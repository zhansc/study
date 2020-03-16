package cn.com.zhanss.datastructure.recursion;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 迷宫回溯问题
 *
 * @author zhanss
 * @since 2020/2/2
 */
public class Maze {

    /**
     * 地图
     */
    private static int[][] map;

    @Test
    public void main() {
        // 从map[1][1]处开始
        setWay(map, 1, 1);
        // 打印地图
        System.out.println("====路线====");
        print(map);
    }

    /**
     * 探索路线
     * 规则：
     * 1. 0 表示未走过，1 表示墙，2 表示走过，通路，3 表示走过，路不通
     * 2. 路线探索策略：下 -> 右 -> 上 -> 左
     * @param map 地图
     * @param i
     * @param j 出发的位置
     */
    public boolean setWay(int[][] map, int i, int j) {
        // 终点
        if (map[6][5] == 2) {
            return true;
        }
        if (map[i][j] == 0) {
            // 默认该点可通
            map[i][j] = 2;
            if (setWay(map, i + 1, j)) {
                return true;
            } else if (setWay(map, i, j + 1)) {
                return true;
            } else if (setWay(map, i -1, j)) {
                return true;
            } else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                // 路不通
                map[i][j] = 3;
                return false;
            }
        } else {
            // 非 0，则为1（墙，不通）， 2（走过，通路）， 3（走过，路不通）
            return false;
        }
    }

    /**
     * 初始化地图
     */
    @Before
    public void init() {
        map = new int [8][7];
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 7; j ++) {
                map[i][0] = 1;
                map[i][6] = 1;
            }
            for (int k = 0; k < 7; k ++) {
                map[0][k] = 1;
                map[7][k] = 1;
            }
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        // 设置死路，导致回溯
//        map[1][2] = 1;
//        map[2][2] = 1;

        // 打印地图
        System.out.println("====初始化地图====");
        print(map);
    }

    /**
     * 打印地图
     * @param map 地图
     */
    public void print(int[][] map) {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 7; j ++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

}
