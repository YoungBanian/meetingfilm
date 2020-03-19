package com.zhengrz.meetingfilm.utils.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonServiceException extends Exception {

    private Integer code;
    private String message;

    public CommonServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
