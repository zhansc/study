package cn.com.zhanss.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * 错误码
 *
 * @author zhanss
 * @since 2022-08-22
 */
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode implements IErrorCode {
    /**
     * 通用错误码
     */
    COMMON_ERROR(0000_0000, "系统错误"),
    PARAM_ERROR(0000_0001, "参数错误"),

    /**
     * 业务错误码
     */
    FILE_FORMAT_ERROR(0001_000, "文件格式不支持"),

    ;
    int code;
    String message;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}