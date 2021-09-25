package com.tuplescale.graph.expression;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamMapper {
    private final Map<String, Pair<String, Function<String, String>>> mapper;
    private final String REGEX = "([a-zA-z_]+)(\\d*)";
    private final Pattern PATTERN = Pattern.compile(REGEX);
    private final Function<String, String> x2xFunc = (s) -> StringUtils.isEmpty(s) ? "" : s;
    private final Function<String, String> x2xplus1Func = (s) -> StringUtils.isEmpty(s) ? "1" :
            StringUtils.isNumeric(s) ? String.valueOf(Integer.parseInt(s) + 1) : s;

    public ParamMapper() {
        mapper = new HashMap<>();
        mapper.put("parameter", Pair.of("value", x2xFunc));
        mapper.put("avgby_numberofdetails", Pair.of("nodetodetailcount", x2xplus1Func));
    }

    public String getTarget(String param) {
        Matcher matcher = PATTERN.matcher(param);
        if (matcher.find()) {
            String varName = matcher.group(1);
            if (!mapper.containsKey(varName)) return param;
            String suffix = matcher.group(2);
            Pair<String, Function<String, String>> p = mapper.get(varName);
            return p.getLeft() + p.getRight().apply(suffix);
        }
        return param;
    }
}
