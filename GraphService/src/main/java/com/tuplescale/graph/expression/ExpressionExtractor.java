package com.tuplescale.graph.expression;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpressionExtractor {

    private static final String VAR_EXTRACTION_PATTERN = "([\\w\\d_]+)";

    ParamMapper paramMapper;

    public ExpressionExtractor(ParamMapper paramMapper) {
        this.paramMapper = paramMapper;
    }

    abstract boolean match(String expr);

    abstract void validate(String expr) throws IllegalArgumentException;

    String convert(String expr) throws IllegalArgumentException {
        validate(expr);
        Pattern pattern = Pattern.compile(VAR_EXTRACTION_PATTERN);
        Matcher matcher = pattern.matcher(expr);
        Set<String> operands = new HashSet<>();
        while (matcher.find()) {
            operands.add(matcher.group(1));
        }
        for (String operand: operands) {
            expr = expr.replace(operand, paramMapper.getTarget(operand));
        }
        return expr;
    }
}
