package com.spldeolin.satisficing.allison1875;

import com.spldeolin.allison1875.common.config.CommonConfig;
import com.spldeolin.allison1875.common.service.MvcHandlerGeneratorService;
import com.spldeolin.allison1875.handlertransformer.HandlerTransformerConfig;
import com.spldeolin.allison1875.handlertransformer.service.FieldService;
import com.spldeolin.satisficing.allison1875.common.MvcHandlerGeneratorServiceImpl2;
import com.spldeolin.satisficing.allison1875.handlertransformer.FieldServiceImpl2;

/**
 * @author Deolin 2024-06-15
 */
public class HandlerTransformerModule extends com.spldeolin.allison1875.handlertransformer.HandlerTransformerModule {

    public HandlerTransformerModule(CommonConfig commonConfig, HandlerTransformerConfig handlerTransformerConfig) {
        super(commonConfig, handlerTransformerConfig);
    }

    @Override
    protected void configure() {
        super.configure();
        bind(FieldService.class).toInstance(new FieldServiceImpl2());
        bind(MvcHandlerGeneratorService.class).toInstance(new MvcHandlerGeneratorServiceImpl2());
    }

}
