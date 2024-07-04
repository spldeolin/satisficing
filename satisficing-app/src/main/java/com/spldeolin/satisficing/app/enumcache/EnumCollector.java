package com.spldeolin.satisficing.app.enumcache;

import java.util.Map;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.google.common.base.CaseFormat;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.spldeolin.satisficing.api.EnumAncestor;

/**
 * @author Deoli 2024-06-08
 */
@Component
public class EnumCollector implements ApplicationRunner {

    private static final Table<String, String, String> enumCache = HashBasedTable.create();

    @Override
    public void run(ApplicationArguments args) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("")).setScanners(new SubTypesScanner()));

        for (@SuppressWarnings("rawtypes") Class<? extends EnumAncestor> anEnum : reflections.getSubTypesOf(
                EnumAncestor.class)) {

            String enumName = anEnum.getSimpleName();
            if (enumName.endsWith("Enum")) {
                enumName = enumName.substring(0, enumName.length() - 4);
            }
            enumName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, enumName);

            for (EnumAncestor<?> enumConstant : anEnum.getEnumConstants()) {
                enumCache.put(enumName, enumConstant.getCode().toString(), enumConstant.getTitle());
            }
        }

    }

    public Map<String, String> listEnumContants(String enumName) {
        return enumCache.rowMap().get(enumName);
    }

}
