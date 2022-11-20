package cn.com.zhanss.common.exception;

/**
 * 业务异常
 *
 * @author zhanss
 * @since 2022-08-22
 */
public class BusinessException extends RuntimeException{

    private final int errorCode;

    public BusinessException(int errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
