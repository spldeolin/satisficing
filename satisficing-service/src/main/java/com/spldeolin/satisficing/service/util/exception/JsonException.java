package com.spldeolin.satisficing.service.util.exception;

import com.spldeolin.satisficing.client.BaseException;
import com.spldeolin.satisficing.service.util.JsonUtils;

/**
 * 工具类Jsons内部抛出的异常，调用方可自行决定如何处理
 *
 * @author Deolin 2020-03-05
 * @see JsonUtils
 */
public class JsonException extends BaseException {

    private static final long serialVersionUID = 2506389302288058433L;

    public JsonException(Throwable cause) {
        super(cause);
    }

}
