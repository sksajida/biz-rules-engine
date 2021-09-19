package com.tuplescale.graph.processing;

import com.tuplescale.graph.ds.ParamGraph;
import com.tuplescale.graph.model.SimpleTimeseries;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TSParamOrderFinder {

    private static final Logger logger = LoggerFactory.getLogger(TSParamOrderFinder.class);

    private ParamGraph paramGraph;
    private TreeMap<Integer,SimpleTimeseries> execOrderMap = new TreeMap<Integer,SimpleTimeseries>();
    private LinkedHashMap<SimpleTimeseries,Integer> affectedTSMap = new LinkedHashMap<SimpleTimeseries,Integer>();
    private LinkedHashMap<SimpleTimeseries, LinkedHashMap<SimpleTimeseries, Integer>> firstLevelATSMap =
            new LinkedHashMap<SimpleTimeseries, LinkedHashMap<SimpleTimeseries, Integer>>();
    private final List<SimpleTimeseries> toposortedList = new ArrayList<SimpleTimeseries>();

    public TSParamOrderFinder(@Autowired ParamGraph paramGraph) {
        this.paramGraph = paramGraph;

        TopologicalOrderIterator<SimpleTimeseries, DefaultEdge> moreDependencyFirstIterator
                = new TopologicalOrderIterator<>(paramGraph.getG());
        moreDependencyFirstIterator.forEachRemaining(simpleTS -> toposortedList.add(simpleTS));

        doBreadthFirstSearch();
    }

    private void depthFirstIteration(SimpleTimeseries start) {
        Iterator<SimpleTimeseries> iterator = new DepthFirstIterator<>(paramGraph.getG(), start);
        String startKey = start.toString();
        while (iterator.hasNext()) {
            SimpleTimeseries vertex = iterator.next();
            String vertextKey = vertex.toString();
            if(!vertextKey.equals(startKey)) {
                affectedTSMap.put(vertex,0);
            }
        }
    }

    private void doBreadthFirstSearch() {
        for (SimpleTimeseries st : paramGraph.getStartSet()) {
            //depthFirstIteration(st);
            breadthFirstIteration(st);
            //logger.debug("affectedTSMap.size():" + affectedTSMap.size());
        }
        HashSet<Integer> reverseMap = new HashSet<Integer>();
        for (SimpleTimeseries st : affectedTSMap.keySet()) {
            reverseMap.add(st.getTsId());
        }
        logger.debug("reverseMap.size():" + reverseMap.size());
        int order = 0;
        for (SimpleTimeseries st : toposortedList) {
            if (affectedTSMap.containsKey(st)) {
                affectedTSMap.put(st, order);
                execOrderMap.put(order, st);
                order++;
            }
        }
    }

    private void breadthFirstIteration(SimpleTimeseries start) {
        BreadthFirstIterator<SimpleTimeseries, DefaultEdge> iterator = new BreadthFirstIterator<SimpleTimeseries, DefaultEdge>(paramGraph.getG(), start);
        String startKey = start.toString();
        LinkedHashMap<SimpleTimeseries, Integer> affectedTSList = new LinkedHashMap<SimpleTimeseries, Integer>();
        LinkedHashMap<String, Integer> printTSList = new LinkedHashMap<String, Integer>();
        Set<String> vertexSet = new HashSet<String>();
        Graph<SimpleTimeseries, DefaultEdge> subGraph = new DirectedAcyclicGraph<>(DefaultEdge.class);
        vertexSet.add(startKey);
        subGraph.addVertex(start);
        while (iterator.hasNext()) {
            SimpleTimeseries vertex = iterator.next();
            String vertexKey = vertex.toString();
            if (vertexSet.contains(vertexKey)) continue;
            int depth = iterator.getDepth(vertex);
            if (!vertexKey.equals(startKey)) {
                affectedTSMap.put(vertex, 0);
                affectedTSList.put(vertex, depth);
                printTSList.put(vertexKey, depth);
            }
            subGraph.addVertex(vertex);
            SimpleTimeseries parentVertex = iterator.getParent(vertex);
            if (vertexSet.contains(parentVertex.toString())) {
                subGraph.addEdge(parentVertex, vertex);
            }
        }
        firstLevelATSMap.put(start, affectedTSList);
        logger.debug("For {}, affectedTSList: {}", startKey, printTSList);
    }

    public TreeMap<Integer, SimpleTimeseries> getExecOrderMap() {
        return execOrderMap;
    }

    public void setExecOrderMap(TreeMap<Integer, SimpleTimeseries> execOrderMap) {
        this.execOrderMap = execOrderMap;
    }

    public LinkedHashMap<SimpleTimeseries, Integer> getAffectedTSMap(int tsId) throws Exception {
        int relPosition = 0;
        String anchorTo = "CURRENT_EFFECTIVE_PERIOD";
        String key = "T" + ":" + Integer.toString(tsId) + ":" + Integer.toString(relPosition) + ":" + anchorTo;
        if (paramGraph.getParameterMap().containsKey(key)) {
            SimpleTimeseries st = paramGraph.getParameterMap().get(key);
            return firstLevelATSMap.get(st);
        } else {
            logger.error("TSId {}, not found in affectedTSMap!", tsId);
            return new LinkedHashMap<SimpleTimeseries, Integer>();
        }
    }

    public void setAffectedTSMap(LinkedHashMap<SimpleTimeseries, Integer> affectedTSMap) {
        this.affectedTSMap = affectedTSMap;
    }
}
