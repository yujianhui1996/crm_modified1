package com.comma.fit.fitcrm.exception;

import java.io.Serializable;

/**
 * @author magang
 *
 * @description 通用系统异常
 */
public class CommonSysException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -2546448197942770268L;
    private ExpCodeEnum codeEnum;

    public CommonSysException(ExpCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }

    public CommonSysException() {

    }
}
