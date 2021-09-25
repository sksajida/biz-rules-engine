package com.tuplescale.graph.expression;

public class SQLExpressionConverter {
    public static void main(String[] args) {
        String expr = "parameter1/avgby_numberofdetails";
        String condExpr = "if (parameter2 > 0) {\n" +
                "parameter1 } else { 0 }";
        condExpr = "if (parameter2 > 0 && \n" +
                "parameter3 < 100 || \n" +
                "parameter4 == 2) {\n" +
                " parameter1/2\n" +
                "} else if(p > q) { \n" +
                "pa * qa/da\n" +
                "} else {\n" +
                "2 + 4\n" +
                "}";

        String convertedExpr = null;

        ParamMapper mapper = new ParamMapper();

        ExpressionExtractor extractor = new ArithmeticExpressionExtractor(mapper);
        convertedExpr = extractor.convert(expr);
        System.out.printf("Expr: %s, \nConvertedExpr: %s\n\n", expr, convertedExpr);

        ExpressionExtractor condExtractor = new ConditionalExpressionExtractor(mapper);
        convertedExpr = condExtractor.convert(condExpr);
        System.out.printf("Expr: %s, \nConvertedExpr: %s\n\n", condExpr, convertedExpr);
    }
}
