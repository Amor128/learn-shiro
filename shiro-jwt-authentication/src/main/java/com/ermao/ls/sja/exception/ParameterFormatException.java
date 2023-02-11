package com.ermao.ls.sja.exception;

/**
 * @author Ermao
 * Date: 2023/2/11 21:05
 */
public class ParameterFormatException extends RuntimeException {
    public ParameterFormatException() {
        super();
    }

    public ParameterFormatException(String message) {
        super(message);
    }
}
