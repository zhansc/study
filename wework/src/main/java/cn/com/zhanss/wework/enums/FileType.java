package cn.com.zhanss.wework.enums;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * 文件类型
 *
 * @author zhanss
 * @since 2022-08-23
 */
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum FileType {
    /**
     * 文件类型
     */
    IMAGE(1, "image", Lists.newArrayList("jpg", "jpeg", "bmp", "png")),

    ;
    int type;

    String name;

    /**
     * 支持的文件格式
     */
    List<String> formats;
}