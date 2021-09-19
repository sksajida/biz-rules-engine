package com.tuplescale.graph.reader;

import com.tuplescale.graph.model.CmAttributeParameters;
import com.tuplescale.graph.util.ReaderUtil;

import java.io.File;
import java.util.List;

public class AttributeParamsReader {

    private static final String ATTRIBUTE_PARAMS_FILE = "CM_ATTRIBUTE_PARAMETERS_202106242306.csv";

    public static List<CmAttributeParameters> getAttrParams() {
        CSVToBeanIn in = new CSVToBeanIn<CmAttributeParameters>(ReaderUtil.read(ATTRIBUTE_PARAMS_FILE), CmAttributeParameters.class);
        return in.readAll();
    }
}
