package com.tuplescale.graph.model;

import java.util.ArrayList;
import java.util.List;

public class AffectedTSCalcNode {
    private int level;
    private List<PlanningVertex> timeseries;

    public AffectedTSCalcNode() {
    }

    public AffectedTSCalcNode(int level) {
        this.level = level;
        this.timeseries = new ArrayList<>();
    }

    public void add(PlanningVertex ts) {
        this.timeseries.add(ts);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<PlanningVertex> getTimeseries() {
        return timeseries;
    }

    public void setTimeseries(List<PlanningVertex> timeseries) {
        this.timeseries = timeseries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlanningVertex pv: timeseries) {
            sb.append(pv.stringify());
        }
        return sb.toString();
    }
}
