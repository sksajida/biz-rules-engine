package com.tuplescale.graph.model;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TimeseriesUpdateResponse extends Response {
    private int numUpdated;
    private int numUpdateFailed;
    private Set<Long> failedTimeseries;
}
