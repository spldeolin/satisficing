package com.spldeolin.satisficing.app.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deolin 2024-06-21
 */
public class TextUtils {

    private TextUtils() {
        throw new UnsupportedOperationException("Never instantiate me.");
    }

    /**
     * 将输入的字符串按半角逗号分隔成片段，忽略已被反斜杠转义的逗号，并对每个片段进行反转义处理。
     * e.g.: "apple,orange\\,banana,grape" -> ["apple", "orange,banana", "grape"]
     */
    public static List<String> splitAndUnescapeComma(String input) {
        List<String> parts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean escaped = false;

        for (char c : input.toCharArray()) {
            if (c == ',' && !escaped) {
                parts.add(sb.toString());
                sb.setLength(0);
            } else {
                if (c == '\\' && !escaped) {
                    escaped = true;
                } else {
                    sb.append(c);
                    escaped = false;
                }
            }
        }

        if (sb.length() > 0) {
            parts.add(sb.toString());
        }

        parts.replaceAll(s -> s.replace("\\,", ","));

        return parts;
    }

}
