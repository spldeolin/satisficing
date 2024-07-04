package com.spldeolin.satisficing.api;

/**
 * 错误码接口
 *
 * @author Deolin 2024-04-01
 */
public interface ErrorCode {

    /**
     * 声明错误码
     */
    String code();

    /**
     * 声明该错误码的默认errorMsg
     */
    String defaultMsg();

    /**
     * 非法请求（Not Found、参数不正确、报文不正确等）
     */
    ErrorCode ILLEGAL_REQUEST = new ErrorCode() {
        @Override
        public String code() {
            return "400";
        }

        @Override
        public String defaultMsg() {
            return "非法请求";
        }
    };

    /**
     * 服务端异常（网络错误、数据库错误、服务端BUG等）
     */
    ErrorCode SERVER_EXCEPTION = new ErrorCode() {
        @Override
        public String code() {
            return "500";
        }

        @Override
        public String defaultMsg() {
            return "内部错误，请稍后重试";
        }
    };

    /**
     * 通用业务异常
     */
    ErrorCode GENERAL_BIZ_EXCEPTION = new ErrorCode() {
        @Override
        public String code() {
            return "1001";
        }

        @Override
        public String defaultMsg() {
            return null;
        }
    };

}