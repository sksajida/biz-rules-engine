package com.tuplescale.graph.expression;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConditionalExpressionExtractor extends ExpressionExtractor {

    private static final String REGEX = "[if|else\\s\\r\\n]+([^\\{]+)\\s*\\{([^}]+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public ConditionalExpressionExtractor(ParamMapper paramMapper) {
        super(paramMapper);
    }

    @Override
    boolean match(String expr) {
        if (StringUtils.isEmpty(expr)) return false;
        return Pattern.matches(REGEX, expr);
    }

    @Override
    void validate(String expr) throws IllegalArgumentException {
        if (StringUtils.isEmpty(expr)) throw new IllegalArgumentException("Expr cannot be null");
    }

    @Override
    public String convert(String expr) throws IllegalArgumentException {
        Matcher matcher = PATTERN.matcher(expr);
        String condnStmt, block, mappedExpr;
        StringBuilder exprBuilder = new StringBuilder();
        while (matcher.find()) {
            condnStmt = matcher.group(1);
            condnStmt = condnStmt != null ? condnStmt.trim() : condnStmt;
            block = matcher.group(2);

            if (!StringUtils.isEmpty(condnStmt)) {
                exprBuilder.append(" /* case when ");
                mappedExpr = super.convert(condnStmt.trim());
                mappedExpr = replaceLogicalOperators(mappedExpr);
                exprBuilder.append(String.format("%s then */ ", mappedExpr));
                mappedExpr = super.convert(block.trim());
                exprBuilder.append(mappedExpr);
            } else {
                exprBuilder.append(" /* else */ ");
                mappedExpr = super.convert(block.trim());
                exprBuilder.append(mappedExpr);
            }
        }
        if (exprBuilder.length() > 0) exprBuilder.append(" /* end */");
        return exprBuilder.toString();
    }

    private String replaceLogicalOperators(String mappedExpr) {
        if (StringUtils.isEmpty(mappedExpr)) return mappedExpr;
        mappedExpr = mappedExpr.replace("==", "=");
        mappedExpr = mappedExpr.replace("!=", "<>");
        mappedExpr = mappedExpr.replace("&&", "AND");
        mappedExpr = mappedExpr.replace("||", "OR");
        return mappedExpr;
    }
}
