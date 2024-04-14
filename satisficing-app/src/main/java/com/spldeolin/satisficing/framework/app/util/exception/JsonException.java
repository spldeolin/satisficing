package com.spldeolin.satisficing.framework.app.util.exception;

/**
 * 工具类Jsons内部抛出的异常，调用方可自行决定如何处理
 *
 * @author Deolin 2020-03-05
 * @see JsonUtils
 */
public class JsonException extends RuntimeException {

    private static final long serialVersionUID = 2506389302288058433L;

    public JsonException(Throwable cause) {
        super(cause);
    }

}
