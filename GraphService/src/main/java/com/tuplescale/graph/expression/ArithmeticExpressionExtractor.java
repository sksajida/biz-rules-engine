package com.tuplescale.graph.expression;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class ArithmeticExpressionExtractor extends ExpressionExtractor {

    private static final String PATTERN = "^.*[(if)\\s]+\\(.+[<>&|=\\r\\n\\s\\)\\w]+.*\\{?$";

    public ArithmeticExpressionExtractor(ParamMapper paramMapper) {
        super(paramMapper);
    }


    @Override
    public boolean match(String expr) {
        if (StringUtils.isEmpty(expr)) return false;
        boolean isMatched = Pattern.matches(PATTERN, expr);
        return !isMatched;
    }

    @Override
    public void validate(String expr) throws IllegalArgumentException {
        if (StringUtils.isEmpty(expr)) throw new IllegalArgumentException("Expr cannot be null");
    }
}
