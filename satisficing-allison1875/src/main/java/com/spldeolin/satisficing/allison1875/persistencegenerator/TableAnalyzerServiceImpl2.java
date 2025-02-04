package com.spldeolin.satisficing.allison1875.persistencegenerator;

import static com.github.javaparser.StaticJavaParser.parseAnnotation;
import static com.github.javaparser.utils.CodeGenerationUtils.f;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.spldeolin.allison1875.common.ast.AstForestContext;
import com.spldeolin.allison1875.common.ast.FileFlush;
import com.spldeolin.allison1875.common.config.CommonConfig;
import com.spldeolin.allison1875.common.constant.BaseConstant;
import com.spldeolin.allison1875.common.service.ImportExprService;
import com.spldeolin.allison1875.common.util.JavadocUtils;
import com.spldeolin.allison1875.common.util.MoreStringUtils;
import com.spldeolin.allison1875.persistencegenerator.dto.InformationSchemaDTO;
import com.spldeolin.allison1875.persistencegenerator.dto.TableAnalysisDTO;
import com.spldeolin.allison1875.persistencegenerator.facade.dto.JavaTypeDTO;
import com.spldeolin.allison1875.persistencegenerator.facade.dto.PropertyDTO;
import com.spldeolin.allison1875.persistencegenerator.service.impl.TableAnalyzerServiceImpl;
import com.spldeolin.satisficing.api.BaseEnum;

/**
 * @author Deolin 2025-01-19
 */
@Singleton
public class TableAnalyzerServiceImpl2 extends TableAnalyzerServiceImpl {

    private final String enumPackage;

    @Inject
    private CommonConfig commonConfig;

    @Inject
    private ImportExprService importExprService;

    public TableAnalyzerServiceImpl2(String enumPackage) {
        this.enumPackage = enumPackage;
    }

    /**
     * 分析 T(com.foo.Bar) 和 E(foo=bar quz=baaz)
     */
    @Override
    public TableAnalysisDTO analyzeFromSameTable(List<InformationSchemaDTO> infoSchemas) {
        Map<String, InformationSchemaDTO> infoSchemasMap = Maps.newHashMap();
        infoSchemas.forEach(o -> infoSchemasMap.put(o.getColumnName(), o));

        TableAnalysisDTO tableAnalysis = super.analyzeFromSameTable(infoSchemas);
        for (PropertyDTO property : tableAnalysis.getProperties()) {
            InformationSchemaDTO infoSchema = infoSchemasMap.get(property.getColumnName());
            String columnType = infoSchema.getDataType();
            if ("date".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalDate.class));
            }
            if ("time".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalTime.class));
            }
            if ("datetime".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalDateTime.class));
            }
            if ("timestamp".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalDateTime.class));
            }
            JavaTypeDTO enumOrForceType = enumOrForceType(columnType, property.getDescription(),
                    property.getColumnName(), tableAnalysis);
            if (enumOrForceType != null) {
                property.setJavaType(enumOrForceType);
                String description = property.getDescription();
                description = Pattern.compile("T\\((.+?)\\)").matcher(description).replaceFirst("").trim();
                description = Pattern.compile("E\\((.+?)\\)").matcher(description).replaceFirst("").trim();
                property.setDescription(description);
            }
        }
        return tableAnalysis;
    }

    @Override
    public TableAnalysisDTO analyzeFromDdl(SQLCreateTableStatement createTableStmt) {
        Map<String, SQLColumnDefinition> columnDefsMap = Maps.newHashMap();
        createTableStmt.getColumnDefinitions()
                .forEach(o -> columnDefsMap.put(o.getName().getSimpleName().replace("`", ""), o));

        TableAnalysisDTO tableAnalysis = super.analyzeFromDdl(createTableStmt);
        for (PropertyDTO property : tableAnalysis.getProperties()) {
            SQLColumnDefinition columnDef = columnDefsMap.get(property.getColumnName());
            String columnType = columnDef.getDataType().getName();
            if ("date".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalDate.class));
            }
            if ("time".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalTime.class));
            }
            if ("datetime".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalDateTime.class));
            }
            if ("timestamp".equalsIgnoreCase(columnType)) {
                property.setJavaType(new JavaTypeDTO().setClass(LocalDateTime.class));
            }
            JavaTypeDTO enumOrForceType = enumOrForceType(columnType, property.getDescription(),
                    property.getColumnName(), tableAnalysis);
            if (enumOrForceType != null) {
                property.setJavaType(enumOrForceType);
                String description = property.getDescription();
                description = Pattern.compile("T\\((.+?)\\)").matcher(description).replaceFirst("").trim();
                description = Pattern.compile("E\\((.+?)\\)").matcher(description).replaceFirst("").trim();
                property.setDescription(description);
            }
        }
        return tableAnalysis;
    }

    private JavaTypeDTO enumOrForceType(String columnType, String columnComment, String columnName,
            TableAnalysisDTO tableAnalysis) {

        Pattern enumPattern = Pattern.compile("E\\((.+?)\\)");
        Matcher enumMatcher = enumPattern.matcher(columnComment);
        if (enumMatcher.find() && StringUtils.equalsAnyIgnoreCase(columnType, "varchar", "char", "text", "longtext")) {
            String enumName = MoreStringUtils.toUpperCamel(tableAnalysis.getTableName()) + MoreStringUtils.toUpperCamel(
                    columnName) + "Enum";

            CompilationUnit cu = new CompilationUnit();
            cu.setPackageDeclaration(enumPackage);
            EnumDeclaration ed = new EnumDeclaration();
            JavadocUtils.setJavadoc(ed, concatEntityDescription(tableAnalysis),
                    commonConfig.getAuthor() + " " + LocalDate.now());
            ed.addAnnotation(parseAnnotation("@lombok.Getter"));
            ed.addAnnotation(parseAnnotation("@lombok.AllArgsConstructor"));
            ed.setPublic(true);
            ed.setName(enumName);

            ed.addImplementedType(BaseEnum.class.getName() + "<String>");
            for (String part : enumMatcher.group(1).split(" ")) {
                String[] split = part.split("=");
                String enumConstantName = split[0].replaceAll("[^a-zA-Z0-9]", "");
                if (StringUtils.isNumeric(enumConstantName.substring(0, 1))) {
                    enumConstantName = "e" + enumConstantName;
                }
                EnumConstantDeclaration ecd = new EnumConstantDeclaration().setName(enumConstantName)
                        .addArgument(new StringLiteralExpr(split[0])).addArgument(new StringLiteralExpr(split[1]));
                ed.addEntry(ecd);
            }
            ed.addMember(StaticJavaParser.parseBodyDeclaration(
                    "@com.fasterxml.jackson.annotation.JsonValue private final String code;"));
            ed.addMember(StaticJavaParser.parseBodyDeclaration("private final String title;"));
            ed.addMember(StaticJavaParser.parseBodyDeclaration(
                            "public static boolean valid(String code) { return Arrays.stream(values())" + ".anyMatch"
                                    + "(anEnum -> anEnum.getCode().equals(code)); }").asMethodDeclaration()
                    .setJavadocComment("判断参数code是否是一个有效的枚举"));
            ed.addMember(StaticJavaParser.parseBodyDeclaration(
                            f("@com.fasterxml.jackson.annotation.JsonCreator public static %s of(String code) { "
                                    + "return Arrays" + ".stream(values()).filter(anEnum -> anEnum.getCode().equals" + "(code))"
                                    + ".findFirst().orElse(null); }", enumName)).asMethodDeclaration()
                    .setJavadocComment("获取code对应的枚举"));
            ed.addMember(StaticJavaParser.parseBodyDeclaration("@Override public String toString() { return code; }")
                    .asMethodDeclaration());
            cu.addType(ed);
            Path enumPath = CodeGenerationUtils.fileInPackageAbsolutePath(AstForestContext.get().getSourceRoot(),
                    enumPackage, enumName + ".java");
            cu.setStorage(enumPath);
            importExprService.extractQualifiedTypeToImport(cu);
            cu.addImport("java.util.Arrays");
            tableAnalysis.getFlushes().add(FileFlush.build(cu));

            return new JavaTypeDTO().setSimpleName(ed.getNameAsString())
                    .setQualifier(enumPackage + "." + ed.getNameAsString());
        }

        Pattern typePattern = Pattern.compile("T\\((.+?)\\)");
        Matcher typeMatcher = typePattern.matcher(columnComment);
        if (typeMatcher.find()) {
            String qualifier = typeMatcher.group(1);
            try {
                qualifier = StaticJavaParser.parseType(qualifier).toString();
                return new JavaTypeDTO().setSimpleName(MoreStringUtils.splitAndGetLastPart(qualifier, "."))
                        .setQualifier(qualifier);
            } catch (Exception ignored) {
            }
        }
        return null;
    }


    private String concatEntityDescription(TableAnalysisDTO tableAnalysis) {
        String result = "";
        if (commonConfig.getEnableNoModifyAnnounce()) {
            result += BaseConstant.JAVA_DOC_NEW_LINE + BaseConstant.NO_MODIFY_ANNOUNCE;
        }
        if (commonConfig.getEnableLotNoAnnounce()) {
            result +=
                    BaseConstant.JAVA_DOC_NEW_LINE + BaseConstant.LOT_NO_ANNOUNCE_PREFIXION + tableAnalysis.getLotNo();
        }
        return result;
    }

}
