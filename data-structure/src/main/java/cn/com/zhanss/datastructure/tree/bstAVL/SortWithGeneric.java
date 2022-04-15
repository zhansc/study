package cn.com.zhanss.datastructure.tree.bstAVL;

import org.springframework.util.ObjectUtils;

/**
 * 带泛型的排序
 *
 * @author zhanss
 * @since 2019/10/21
 */
public class SortWithGeneric {

    public <T extends Comparable> T[] sort(T[] list) {
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        for (int i = 1; i < list.length; i ++) {
            for (int j = 0; j < list.length - i; j ++) {
                if (list[j].compareTo(list[j + 1]) > 0 ) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
        return list;
    }
}
