package com.spldeolin.satisficing.api;


/**
 * 所有枚举的直接或间接接口
 *
 * @param <C> 枚举的code的类型 e.g.: String
 * @author Deolin 2020-11-08
 */
public interface BaseEnum<C> {

    /**
     * 获取枚举的code的方式
     *
     * 当派生的枚举对象转化为JSON时，JSON Value会来自这个方法
     */
    C getCode();

    /**
     * 获取枚举的标题的方式
     */
    String getTitle();

}
