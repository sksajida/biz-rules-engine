package com.tuplescale.graph.reader;

import com.tuplescale.graph.model.CmDefinitions;
import com.tuplescale.graph.util.ReaderUtil;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CMDefinitionsReader {

    private static final String CM_DEFINITIONS_FILE = "CM_DEFINITIONS_202106242119.csv";

    public static HashSet<Integer> startSet() {
        CSVToBeanIn in = new CSVToBeanIn<CmDefinitions>(ReaderUtil.read(CM_DEFINITIONS_FILE), CmDefinitions.class);
        List<CmDefinitions> cmDefinitions = in.readAll();
        return cmDefinitions.stream().map(cmd -> cmd.TS_ID).collect(Collectors.toCollection(HashSet::new));
    }

    public static List<CmDefinitions> cmDefinitions() {
        return new CSVToBeanIn<CmDefinitions>(ReaderUtil.read(CM_DEFINITIONS_FILE), CmDefinitions.class).readAll();
    }

    public static Map<Integer, CmDefinitions> cmAssociationMap() {
        return cmDefinitions().stream().collect(Collectors.toMap(cmd -> cmd.TS_ID, cmd -> cmd, (cmd1, cmd2) -> cmd1));
    }
}
