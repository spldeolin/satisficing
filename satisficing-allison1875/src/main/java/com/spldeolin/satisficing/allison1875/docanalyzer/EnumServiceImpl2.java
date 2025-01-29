package com.spldeolin.satisficing.allison1875.docanalyzer;

import com.spldeolin.allison1875.docanalyzer.dto.AnalyzeEnumConstantsRetval;
import com.spldeolin.allison1875.docanalyzer.service.impl.EnumServiceImpl;
import com.spldeolin.satisficing.api.BaseEnum;

/**
 * @author Deolin 2024-02-26
 */
public class EnumServiceImpl2 extends EnumServiceImpl {

    /**
     * 分析枚举时，统一枚举接口为BaseEnum
     *
     * @see BaseEnum
     */
    @Override
    protected AnalyzeEnumConstantsRetval analyzeEnumConstant(Object enumConstant) {
        if (enumConstant instanceof BaseEnum) {
            BaseEnum<?> baseEnum = (BaseEnum<?>) enumConstant;
            AnalyzeEnumConstantsRetval result = new AnalyzeEnumConstantsRetval();
            result.setCode(baseEnum.getCode().toString());
            result.setTitle(baseEnum.getTitle());
            return result;
        }
        return super.analyzeEnumConstant(enumConstant);
    }

}