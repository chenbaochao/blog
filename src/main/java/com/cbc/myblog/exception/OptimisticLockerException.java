package com.cbc.myblog.exception;

/**
 * Created by cbc on 2017/11/13.
 */
public class OptimisticLockerException extends RuntimeException {

    public OptimisticLockerException(String msg) {
        super(msg);
    }

    public OptimisticLockerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}