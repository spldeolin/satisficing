package com.spldeolin.satisficing.allison1875;

import com.spldeolin.allison1875.common.config.CommonConfig;
import com.spldeolin.allison1875.persistencegenerator.config.PersistenceGeneratorConfig;
import com.spldeolin.allison1875.persistencegenerator.service.TableAnalyzerService;
import com.spldeolin.satisficing.allison1875.persistencegenerator.TableAnalyzerServiceImpl2;

/**
 * @author Deolin 2024-06-15
 */
public class PersistenceGeneratorModule extends
        com.spldeolin.allison1875.persistencegenerator.PersistenceGeneratorModule {

    private final CommonConfig commonConfig;

    public PersistenceGeneratorModule(CommonConfig commonConfig,
            PersistenceGeneratorConfig persistenceGeneratorConfig) {
        super(commonConfig, persistenceGeneratorConfig);
        this.commonConfig = commonConfig;
    }

    @Override
    protected void configure() {
        super.configure();
        bind(TableAnalyzerService.class).toInstance(
                new TableAnalyzerServiceImpl2(commonConfig.getBasePackage() + ".enums"));
    }

}
