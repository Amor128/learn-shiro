package com.ermao.ls.sja.POJO.VO;

import java.io.Serializable;

/**
 * @author Ermao
 * Date: 2023/2/11 21:22
 */
public class RespVO {

    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private static final String DEFAULT_FAIL_MESSAGE = "fail";
    private static final int DEFAULT_SUCCESS_CODE = 200;
    private static final int DEFAULT_FAIL_CODE = 201;

    private String message;
    private Integer code;
    private Object data;

    private RespVO() {
    }

    private RespVO(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    private RespVO(String message, Integer code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static RespVO success() {
        return new RespVO(DEFAULT_SUCCESS_MESSAGE, DEFAULT_SUCCESS_CODE);
    }

    public static RespVO success(String message) {
        return new RespVO(message, DEFAULT_SUCCESS_CODE);
    }

    public static RespVO success(Object data) {
        return new RespVO(DEFAULT_SUCCESS_MESSAGE, DEFAULT_SUCCESS_CODE, data);
    }

    public static RespVO fail() {
        return new RespVO(DEFAULT_FAIL_MESSAGE, DEFAULT_FAIL_CODE);
    }

    public static RespVO fail(String message) {
        return new RespVO(message, DEFAULT_FAIL_CODE);
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
