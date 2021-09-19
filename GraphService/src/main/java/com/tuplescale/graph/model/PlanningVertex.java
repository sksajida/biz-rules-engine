package com.tuplescale.graph.model;

import com.google.common.primitives.Longs;
import com.tuplescale.graph.util.GraphSequenceIDver2;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.TreeSet;


public class PlanningVertex implements Cloneable {
    private static Logger logger = LoggerFactory.getLogger(PlanningVertex.class);

    private long vertexId;
    private int tsId;
    private double value;
    private boolean isVisited = false;
    private String expressionName;
    private String expression;
  //  private Set<PlanningVertex> dependents;

    public PlanningVertex(long vertexId, double value) {
        this.vertexId = vertexId;
        this.value = value;
        this.tsId = (int) GraphSequenceIDver2.getTsId(vertexId);
      //  this.dependents = new TreeSet<>();
    }

    public void setVertexId(long vertexId) {
        this.vertexId = vertexId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setExpressionName(String ename) {
        this.expressionName = ename;
    }

    public long getVertexId() {
        return vertexId;
    }

    public double getValue() {
        return value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String getExpressionName() {
        return expressionName;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getTsId() {
        return tsId;
    }

    public void setTsId(int tsId) {
        this.tsId = tsId;
    }

    public String toString() {
        return GraphSequenceIDver2.toString(vertexId);
    }

    public String stringify() {
        int dimId = GraphSequenceIDver2.getDetailId(vertexId);
        short tbId = GraphSequenceIDver2.getPeriodId(vertexId);
        int tsIdv = (int) GraphSequenceIDver2.getTsId(vertexId);
        return String.format("TsId: %d, TbId: %d, DimId: %d, Value: %f, Expression: %s, dependents: %s\n", tsIdv, tbId, dimId, value, expression);
    }

    public int hashCode() {
        return Longs.hashCode(vertexId);
    }

    public boolean equals(Object o) {
        return (o instanceof PlanningVertex) && (getVertexId() == ((PlanningVertex)o).getVertexId());
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}