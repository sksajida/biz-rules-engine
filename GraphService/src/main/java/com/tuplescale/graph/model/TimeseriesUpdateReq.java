package com.tuplescale.graph.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TimeseriesUpdateReq {
    private long vertexId;
    private double value;
}
