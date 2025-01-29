package com.spldeolin.satisficing.allison1875.common;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.type.Type;
import com.spldeolin.allison1875.common.dto.GenerateMvcHandlerArgs;
import com.spldeolin.allison1875.common.dto.GenerateMvcHandlerRetval;
import com.spldeolin.allison1875.common.service.impl.MvcHandlerGeneratorServiceImpl;
import com.spldeolin.satisficing.api.RequestResult;

public class MvcHandlerGeneratorServiceImpl2 extends MvcHandlerGeneratorServiceImpl {

    /**
     * 生成MVC Handler时，统一返回类型为RequestResult
     *
     * @see RequestResult
     */
    @Override
    public GenerateMvcHandlerRetval generateMvcHandler(GenerateMvcHandlerArgs args) {
        GenerateMvcHandlerRetval result = super.generateMvcHandler(args);
        Type returnType = result.getMvcHandler().getType();
        if (returnType.isVoidType()) {

            returnType.replace(StaticJavaParser.parseType(RequestResult.class.getName() + "<Void>"));
            result.getMvcHandler().getBody().get().getStatements()
                    .add(StaticJavaParser.parseStatement("return RequestResult.success();"));
        } else {
            returnType.replace(
                    StaticJavaParser.parseType(String.format(RequestResult.class.getName() + "<%s>", returnType)));
            Expression returnExpr = result.getMvcHandler().getBody().get().getStatements().get(0).asReturnStmt()
                    .getExpression().get();
            returnExpr.replace(
                    StaticJavaParser.parseExpression(String.format("RequestResult.success(%s)", returnExpr)));
        }
        return result;
    }

}