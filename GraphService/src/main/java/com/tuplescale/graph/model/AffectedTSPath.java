package com.tuplescale.graph.model;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class AffectedTSPath {
//    private Map<String, PlanningVertex> timeseriesMap;
    private LinkedHashSet<AffectedTSCalcNode> tsCalcNodes;
    private String affectedTSBy;

    public AffectedTSPath() {
    }

    public AffectedTSPath(String affectedTSBy) {
        this.affectedTSBy = affectedTSBy;
//        this.timeseriesMap = new HashMap<>();
        this.tsCalcNodes = new LinkedHashSet<>();
    }

//    public void addTimeSeriesGlobally(PlanningVertex st) {
//        timeseriesMap.put(st.toString(), st);
//    }

    public void addTsCalcNode(AffectedTSCalcNode tsCalcNode) {
        this.tsCalcNodes.add(tsCalcNode);
    }

//    public Map<String, PlanningVertex> getTimeseriesMap() {
//        return timeseriesMap;
//    }
//
//    public void setTimeseriesMap(Map<String, PlanningVertex> timeseriesMap) {
//        this.timeseriesMap = timeseriesMap;
//    }

    public LinkedHashSet<AffectedTSCalcNode> getTsCalcNodes() {
        return tsCalcNodes;
    }

    public void setTsCalcNodes(LinkedHashSet<AffectedTSCalcNode> tsCalcNodes) {
        this.tsCalcNodes = tsCalcNodes;
    }

    public String getAffectedTSBy() {
        return affectedTSBy;
    }

    public void setAffectedTSBy(String affectedTSBy) {
        this.affectedTSBy = affectedTSBy;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AffectedTSCalcNode tsCalcNode: tsCalcNodes) {
            sb.append("-----Level: "+tsCalcNode.getLevel()+"-----\n");
            sb.append(tsCalcNode.toString());
        }
        return sb.toString();
    }
}
