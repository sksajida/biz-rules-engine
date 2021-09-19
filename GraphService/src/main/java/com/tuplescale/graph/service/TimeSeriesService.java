package com.tuplescale.graph.service;

import com.tuplescale.graph.ds.TimeseriesGraph;
import com.tuplescale.graph.model.PlanningVertex;
import com.tuplescale.graph.model.TimeseriesUpdateReq;
import com.tuplescale.graph.model.TimeseriesUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TimeSeriesService {

    @Autowired
    TimeseriesGraph timeseriesGraph;

    public TimeseriesUpdateResponse update(List<TimeseriesUpdateReq> timeseriesUpdateReqs) {
        Map<Long, Double> timeseriesUpdateMap = timeseriesUpdateReqs.stream().collect(Collectors.toMap(t -> t.getVertexId(), t -> t.getValue(), (t1, t2) -> t1));
        return timeseriesGraph.update(timeseriesUpdateMap);
    }

    public PlanningVertex findTs(long vertexId) {
        return timeseriesGraph.getVertex(vertexId);
    }
}
