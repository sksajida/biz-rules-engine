package com.tuplescale.graph.reader;

import com.google.common.collect.LinkedListMultimap;
import com.tuplescale.graph.model.CmAttributeParameters;
import com.tuplescale.graph.model.CmTimeseriesParameters;
import com.tuplescale.graph.model.SimpleTimeseries;
import com.tuplescale.graph.util.ReaderUtil;

import java.io.File;
import java.util.List;

public class TimeseriesParamsReader {
    private static final String ATTRIBUTE_PARAMS_FILE = "CM_TIMESERIES_PARAMETERS_202106221924.csv";


    public static LinkedListMultimap<Integer, SimpleTimeseries> getExprParamMap() {
        LinkedListMultimap<Integer,SimpleTimeseries> exprParamaterMap = LinkedListMultimap.create();
        CSVToBeanIn in = new CSVToBeanIn<CmTimeseriesParameters>(ReaderUtil.read(ATTRIBUTE_PARAMS_FILE), CmTimeseriesParameters.class);
        List<CmTimeseriesParameters> cmTimeseriesParameters = in.readAll();
        for (CmTimeseriesParameters ctp : cmTimeseriesParameters) {
            SimpleTimeseries st = null;
            int tsId = ctp.AFFECTED_TS_ID;
            st = new SimpleTimeseries(ctp.TS_ID, ctp.TIME_SERIES_NAME, ctp.RELATIVE_PERIOD_POSITION,
                    ctp.ANCHOR_TO);
            exprParamaterMap.put(tsId, st);
        }
        List<CmAttributeParameters> cmAttributeParameters = AttributeParamsReader.getAttrParams();
        for (CmAttributeParameters cap: cmAttributeParameters) {
            int tsId = cap.AFFECTED_TS_ID;
            SimpleTimeseries st = null;
            st = new SimpleTimeseries(cap.CM_PARAM_ID, cap.ATTRIBUTE_NAME);
            st.setParameterType("A");
            exprParamaterMap.put(tsId,st);
        }
        return exprParamaterMap;
    }
}
