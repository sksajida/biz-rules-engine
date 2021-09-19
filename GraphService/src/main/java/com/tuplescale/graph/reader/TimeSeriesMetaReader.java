package com.tuplescale.graph.reader;

import com.google.common.collect.LinkedListMultimap;
import com.tuplescale.graph.model.SimpleTimeseries;
import com.tuplescale.graph.model.TimeseriesMeta;
import com.tuplescale.graph.util.ReaderUtil;

import java.io.File;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeSeriesMetaReader  {

    private static final String TIMESERIES_META_FILE = "TIMESERIES_META_202106230156.csv";

    private LinkedListMultimap<Integer, SimpleTimeseries> exprParamaterMap = LinkedListMultimap.create();

    public static Map<Integer, Integer> getTimeSeriesPlaneMap() {
        CSVToBeanIn in = new CSVToBeanIn<TimeseriesMeta>(ReaderUtil.read(TIMESERIES_META_FILE), TimeseriesMeta.class);
        List<TimeseriesMeta> timeseriesMetas = in.readAll();
        return timeseriesMetas.stream().collect(Collectors.toMap(t -> t.TS_ID, t -> t.DESTINATION_PLANE_ID, (t1, t2) -> t1));
    }


}
