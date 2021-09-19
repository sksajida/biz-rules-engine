package com.tuplescale.graph.ds;

import com.tuplescale.graph.model.CmAttributeParameters;
import com.tuplescale.graph.model.CmDefinitions;
import com.tuplescale.graph.processing.TSParamOrderFinder;
import com.tuplescale.graph.reader.*;
import com.tuplescale.graph.model.CmTimeseriesParameters;
import com.tuplescale.graph.model.SimpleTimeseries;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class ParamGraph {

    private static Logger logger = LoggerFactory.getLogger(ParamGraph.class);

    private org.jgrapht.Graph<SimpleTimeseries, DefaultEdge> g;
    private LinkedHashSet<SimpleTimeseries> startSet = new LinkedHashSet<SimpleTimeseries>();
    private LinkedHashMap<String,SimpleTimeseries> parameterMap = new LinkedHashMap<String,SimpleTimeseries>();
    private Map<Integer, CmDefinitions> cmAssociationMap;


    public ParamGraph(@Autowired @Qualifier("paramIn") CSVToBeanIn in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        g = new DirectedAcyclicGraph<>(DefaultEdge.class);
        List<CmTimeseriesParameters> ctpList = in.readAll();
        Map<Integer, Integer> timeseriesPlaneMap = TimeSeriesMetaReader.getTimeSeriesPlaneMap();
        HashSet<Integer> stepSet = CMDefinitionsReader.startSet();
        Set<Integer> tsIdSet = TimeSeriesDataReader.getTSIdSet();
        cmAssociationMap = CMDefinitionsReader.cmAssociationMap();
        int edgeCount = 0;
        for (CmTimeseriesParameters ctp: ctpList) {
            int tsId = ctp.TS_ID;
            String name = ctp.TIME_SERIES_NAME;
            int affectedTsId = ctp.AFFECTED_TS_ID;
            int planeId = timeseriesPlaneMap.getOrDefault(tsId, -1);
            int affectedPlaneId = timeseriesPlaneMap.getOrDefault(affectedTsId, -1);
            if (!stepSet.contains(affectedTsId)) continue;
            String affectedName = ctp.AFFECTED_TIME_SERIES_NAME;
            int relPosition = ctp.RELATIVE_PERIOD_POSITION;
            String anchorTo = ctp.ANCHOR_TO;
            String key1 = "T" + ":" + Integer.toString(tsId) + ":" + Integer.toString(relPosition) + ":" + anchorTo;
            String key2 = "T" + ":" + Integer.toString(affectedTsId) + ":" + Integer.toString(0) + ":" + "CURRENT_EFFECTIVE_PERIOD";
            SimpleTimeseries st1 = null;
            SimpleTimeseries st2 = null;
            CmDefinitions cmDefinitions = null;
            if (parameterMap.containsKey(key1)) {
                st1 = parameterMap.get(key1);
            } else {
                st1 = new SimpleTimeseries(tsId, name, relPosition, anchorTo);
                st1.setPlaneId(planeId);
                cmDefinitions = cmAssociationMap.get(tsId);
                if (Objects.nonNull(cmDefinitions)) st1.setExpression(cmDefinitions.EXPRESSION);
                parameterMap.put(key1, st1);
                g.addVertex(st1);
            }
            if (parameterMap.containsKey(key2)) {
                st2 = parameterMap.get(key2);
            } else {
                st2 = new SimpleTimeseries(affectedTsId, affectedName);
                st2.setPlaneId(affectedPlaneId);
                cmDefinitions = cmAssociationMap.get(affectedTsId);
                if (Objects.nonNull(cmDefinitions)) st2.setExpression(cmDefinitions.EXPRESSION);
                parameterMap.put(key2, st2);
                g.addVertex(st2);
            }
            edgeCount++;
            g.addEdge(st1,st2);
            if (tsIdSet.contains(tsId)) {
                startSet.add(st1);
            }
            if (tsIdSet.contains(affectedTsId)) {
                startSet.add(st2);
            }
        }

        List<CmAttributeParameters> cmAttributeParameters = AttributeParamsReader.getAttrParams();
        for (CmAttributeParameters cap : cmAttributeParameters) {
            int tsId = cap.CM_PARAM_ID + 1000;
            String name = cap.ATTRIBUTE_NAME;
            int affectedTsId = cap.AFFECTED_TS_ID;
            int affectedPlaneId = timeseriesPlaneMap.getOrDefault(affectedTsId, -1);
            if (!stepSet.contains(affectedTsId)) continue;
            String affectedName = cap.TIME_SERIES_NAME;
            int relPosition = 0;
            String anchorTo = "CURRENT_EFFECTIVE_PERIOD";
            String key1 = "A" + ":" + Integer.toString(tsId) + ":" + Integer.toString(relPosition) + ":" + anchorTo;
            String key2 = "T" + ":" + Integer.toString(affectedTsId) + ":" + Integer.toString(0) + ":" + "CURRENT_EFFECTIVE_PERIOD";
            SimpleTimeseries st1 = null;
            SimpleTimeseries st2 = null;
            if (parameterMap.containsKey(key1)) {
                st1 = parameterMap.get(key1);
            } else {
                st1 = new SimpleTimeseries(tsId, name, relPosition, anchorTo);
                st1.setParameterType("A");
                parameterMap.put(key1, st1);
                g.addVertex(st1);
            }
            if (parameterMap.containsKey(key2)) {
                st2 = parameterMap.get(key2);
            } else {
                st2 = new SimpleTimeseries(affectedTsId, affectedName);
                st2.setPlaneId(affectedPlaneId);
                parameterMap.put(key2, st2);
                g.addVertex(st2);
            }
            g.addEdge(st1,st2);
            if (tsIdSet.contains(affectedTsId)) {
                startSet.add(st2);
            }
        }

    }


    public Graph<SimpleTimeseries, DefaultEdge> getG() {
        return g;
    }

    public void setG(Graph<SimpleTimeseries, DefaultEdge> g) {
        this.g = g;
    }

    public LinkedHashSet<SimpleTimeseries> getStartSet() {
        return startSet;
    }

    public void setStartSet(LinkedHashSet<SimpleTimeseries> startSet) {
        this.startSet = startSet;
    }

    public static void main(String[] args) {
        final String timeseriesParamsFilepath = "C:\\Work\\Workspace\\graph\\sql\\dump\\CM_TIMESERIES_PARAMETERS_202106221924.csv";
        CSVToBeanIn in = new CSVToBeanIn<CmTimeseriesParameters>(new File(timeseriesParamsFilepath), CmTimeseriesParameters.class);
        ParamGraph g = new ParamGraph(in);
        TSParamOrderFinder tspof = new TSParamOrderFinder(g);
        Map<SimpleTimeseries, Stack<SimpleTimeseries>> affectedTSMap = findAffectedTs(g.getG(), new SimpleTimeseries(35, "TS35", 0, "CURRENT_EFFECTIVE_PERIOD"));
        System.out.println(affectedTSMap);
    }

    private static Map<SimpleTimeseries, Stack<SimpleTimeseries>> findAffectedTs(Graph<SimpleTimeseries, DefaultEdge> g,
                                                                                 SimpleTimeseries st) {
        Map<SimpleTimeseries, Stack<SimpleTimeseries>> affectedTSMap = new HashMap<>();
        for (DefaultEdge e : g.outgoingEdgesOf(st)) {
            Stack<SimpleTimeseries> path = new Stack<>();
            depthSearch(g, g.getEdgeTarget(e), path);
            affectedTSMap.put(g.getEdgeTarget(e), path);
        }
        return affectedTSMap;
    }

    private static void depthSearch(Graph<SimpleTimeseries, DefaultEdge> g, SimpleTimeseries st, Stack<SimpleTimeseries> path) {
        path.push(st);
        for (DefaultEdge e : g.outgoingEdgesOf(st)) {
            depthSearch(g, g.getEdgeTarget(e), path);
        }
    }

    public LinkedHashMap<String, SimpleTimeseries> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(LinkedHashMap<String, SimpleTimeseries> parameterMap) {
        this.parameterMap = parameterMap;
    }
}
