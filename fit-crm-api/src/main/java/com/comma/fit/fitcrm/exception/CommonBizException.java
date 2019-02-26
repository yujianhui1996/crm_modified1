package com.comma.fit.fitcrm.exception;

import java.io.Serializable;

/**
 * @Author magang
 * 通用业务异常（由异常状态码区分不同的业务异常）
 */
public class CommonBizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1719172041047970870L;
    private ExpCodeEnum codeEnum;

    public CommonBizException(ExpCodeEnum codeEnum){
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }

    public CommonBizException(){}

    public ExpCodeEnum getCodeEnum() {
        return codeEnum;
    }
}
