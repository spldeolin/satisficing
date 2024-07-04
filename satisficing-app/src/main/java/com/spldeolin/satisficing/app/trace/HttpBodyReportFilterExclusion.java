package com.spldeolin.satisficing.app.trace;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断 Request Track 机制需要排除哪些请求
 *
 * @author Deolin 2020-12-25
 */
public interface HttpBodyReportFilterExclusion {

    boolean isExcluded(HttpServletRequest request);

}
