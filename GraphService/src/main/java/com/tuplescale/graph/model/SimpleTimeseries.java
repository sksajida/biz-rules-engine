package com.tuplescale.graph.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeSet;

public class SimpleTimeseries {
    private static Logger logger = LoggerFactory.getLogger(SimpleTimeseries.class);
    private String parameterType = "T";
    private int tsId;
    private String name;
    private int relativePos;
    private int planeId;
    private String anchorTo = "CURRENT_EFFECTIVE_PERIOD";
    private String expression;
    private TreeSet<String> dependents;

    public SimpleTimeseries(int tsId, String name, int relativePos, String anchorTo) {
        this(tsId, name);
        this.relativePos = relativePos;
        this.anchorTo = anchorTo;
    }

    public SimpleTimeseries(int tsId, String name) {
        this(tsId);
        this.name = name;
    }

    public SimpleTimeseries(int tsId) {
        this();
        this.tsId = tsId;
        this.relativePos = 0;
        this.name = String.format("TS%d", tsId);
        this.dependents = new TreeSet<>();
    }

    public SimpleTimeseries() {
    }

    public String toString() {
        return parameterType + ":" + Integer.toString(tsId) + ":" + name + ":" + Integer.toString(relativePos) +
        ":" + anchorTo;
    }

    public String toShortString() {
        StringBuffer sb = new StringBuffer();
        sb.append(parameterType).append(":").append(tsId).append(":").append(name);
        if ((relativePos != 0) || !anchorTo.equals("CURRENT_EFFECTIVE_PERIOD")) {
            sb.append(":").append(relativePos).append(":").append(anchorTo);
        }
        return sb.toString();
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public void setTsId(int tsId) {
        this.tsId = tsId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelativePos(int relativePos) {
        this.relativePos = relativePos;
    }

    public void setAnchorTo(String anchorTo) {
        this.anchorTo = anchorTo;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public String getParameterType() {
        return parameterType;
    }

    public int getTsId() {
        return tsId;
    }

    public String getName() {
        return name;
    }

    public int getRelativePos() {
        return relativePos;
    }

    public String getAnchorTo() {
        return anchorTo;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public TreeSet<String> getDependents() {
        return dependents;
    }

    public void setDependents(TreeSet<String> dependents) {
        this.dependents = dependents;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object o) {
        return (o instanceof SimpleTimeseries) && (toString().equals(o.toString()));
    }

}