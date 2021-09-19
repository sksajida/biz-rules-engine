package com.tuplescale.graph.model;

import lombok.ToString;

@ToString
public class TimeseriesRequest {
    public static final short dataType = 1;
    public static final short parameterType = 1;
    private int tsId;
    private int tbId;
    private int relationId;
    private int planeId;
    private double value;

    public TimeseriesRequest() {
    }

    public TimeseriesRequest(int tsId, int tbId, int relationId, int planeId) {
        this.tsId = tsId;
        this.tbId = tbId;
        this.relationId = relationId;
        this.planeId = planeId;
    }

    public TimeseriesRequest(int tsId, int tbId, int relationId, int planeId, double value) {
        this.tsId = tsId;
        this.tbId = tbId;
        this.relationId = relationId;
        this.planeId = planeId;
        this.value = value;
    }

    public static TimeseriesRequest of(TimeseriesData td) {
        return new TimeseriesRequest(td.getTS_ID(), td.getTB_ID(), td.getRELATION_ID(), 1, td.getVALUE());
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public int getTsId() {
        return tsId;
    }

    public void setTsId(int tsId) {
        this.tsId = tsId;
    }

    public int getTbId() {
        return tbId;
    }

    public void setTbId(int tbId) {
        this.tbId = tbId;
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
