package com.spldeolin.satisficing.app.trace;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @author Deolin 2020-12-25
 */
@Component
public class DefaultHttpBodyReportExclusion implements HttpBodyReportFilterExclusion {

    public boolean isExcluded(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.equals("/favicon.ico") || uri.equals("/");
    }

}