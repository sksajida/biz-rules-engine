package com.tuplescale.graph.reader;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class TimeSeriesDataReader {

    private static final String TIMESERIES_DATA_FILE = "C:\\Work\\Workspace\\graph\\sql\\dump\\timeseries_data.csv";

    public static Set<Integer> getTSIdSet() {
//        CSVToBeanIn in = new CSVToBeanIn<TimeseriesData>(new File(TIMESERIES_DATA_FILE), TimeseriesData.class);
//        List<TimeseriesData> timeseriesData = in.readAll();
//        return timeseriesData.stream().map(t -> t.TS_ID).collect(Collectors.toCollection(HashSet::new));

        Integer[] distinctTSIds =  {226,244,225,243,260,261,278,315,207,314,297,333,208,228,371};
        return Arrays.stream(distinctTSIds).collect(Collectors.toSet());

    }
}
