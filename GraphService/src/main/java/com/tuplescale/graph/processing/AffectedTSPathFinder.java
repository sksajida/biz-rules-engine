package com.tuplescale.graph.processing;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.graph.MutableGraph;
import com.google.common.graph.Traverser;
import com.tuplescale.graph.ds.ParamGraph;
import com.tuplescale.graph.ds.TimeseriesGraph;
import com.tuplescale.graph.model.AffectedTSCalcNode;
import com.tuplescale.graph.model.AffectedTSPath;
import com.tuplescale.graph.model.CmDefinitions;
import com.tuplescale.graph.model.PlanningEdge;
import com.tuplescale.graph.model.PlanningVertex;
import com.tuplescale.graph.model.SimpleTimeseries;
import com.tuplescale.graph.model.TimeseriesRequest;
import com.tuplescale.graph.service.NodeProcessor;
import com.tuplescale.graph.util.GraphSequenceIDver2;

@Component
public class AffectedTSPathFinder {
    private ParamGraph paramGraph;
    private TimeseriesGraph timeseriesGraph;
    private TSParamOrderFinder tsParamOrderFinder;
    private Map<Integer, CmDefinitions> cmAssociationMap;
    private NodeProcessor nodeProcessor;
    
    private static Logger logger = LoggerFactory.getLogger(AffectedTSPathFinder.class);

    public AffectedTSPathFinder(@Autowired ParamGraph paramGraph,
                                @Autowired TimeseriesGraph timeseriesGraph,
                                @Autowired TSParamOrderFinder tsParamOrderFinder,
                                @Autowired NodeProcessor nodeProcessor) {
        this.paramGraph = paramGraph;
        this.timeseriesGraph = timeseriesGraph;
        this.cmAssociationMap = timeseriesGraph.getCmAssociationMap();
        this.tsParamOrderFinder = tsParamOrderFinder;
        this.nodeProcessor = nodeProcessor;
    }


    public void overrideAffectedNodes(TimeseriesRequest request) throws Exception {
        short dataType = 1; // numeric
        short paramerType = 1; // timeseries
        int dimId = request.getRelationId();
        int tbId = request.getTbId();
        int tsId = request.getTsId();
        long graphSeqId = GraphSequenceIDver2.toGraphSequence(dimId, (short) tsId, (short) tbId, dataType, paramerType);
        //logger.info("Is graphSeqId exists: {}", timeseriesGraph.getVertexMap().containsKey(graphSeqId));
        bfs1(timeseriesGraph.getVertexMap().get(graphSeqId),request);
    }

	private void bfs(PlanningVertex start, TimeseriesRequest request) throws CloneNotSupportedException {
		Graph<PlanningVertex, PlanningEdge> g = timeseriesGraph.getG();
		BreadthFirstIterator<PlanningVertex, PlanningEdge> iterator = new BreadthFirstIterator<PlanningVertex, PlanningEdge>(
				g, start);
		String startKey = start.toString();
		Set<String> vertexSet = new HashSet<String>();
		vertexSet.add(startKey);
		PlanningVertex vertex = null;
		Set<Integer> depthCovered = new HashSet<>();
		while (iterator.hasNext()) {
			vertex = iterator.next();
			String vertexKey = vertex.toString();
			if (vertexSet.contains(vertexKey))
				continue;
//            vertex.evaluate(g);
			PlanningVertex vCopy = (PlanningVertex) vertex;
			Set<PlanningVertex> dependents = getDependents(vertex, request.getRelationId(), vCopy.getTsId(),
					request.getTbId());
			int depth = iterator.getDepth(vertex);
			if (!depthCovered.contains(depth)) {
				
				if(depth > 2)
					logger.info("  overriding for depth "+depth);
				
				try {
					nodeProcessor.process(vertex, dependents);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			depthCovered.add(depth);
		}
	}
	
	
	private void bfs1(PlanningVertex start, TimeseriesRequest request) throws CloneNotSupportedException {
		//logger.info(" Thread name "+Thread.currentThread());
		MutableGraph<PlanningVertex> g = timeseriesGraph.getMyGraph();
		Set<String> vertexSet = new HashSet<String>();
		vertexSet.add(start.toString());
		 Traverser.forGraph(g).breadthFirst(start).forEach((vertex)-> 
		 {
			 
			 		Set<PlanningVertex> dependents = g.predecessors(vertex);
					/*Set<PlanningVertex> dependents = getDependents(vertex, request.getRelationId(), vertex.getTsId(),
							request.getTbId());*/
						try {
							nodeProcessor.process(vertex, dependents);
						} catch (Exception e) {
							e.printStackTrace();
						}
				
		 });
	}

    public AffectedTSPath affectedTSPath(TimeseriesRequest request) throws Exception {
        short dataType = 1; // numeric
        short paramerType = 1; // timeseries
        int dimId = request.getRelationId();
        int tbId = request.getTbId();
        int planeId = request.getPlaneId();
        Set<Integer> depthCovered = new HashSet<>();
        AffectedTSPath affectedTSPath = new AffectedTSPath(String.valueOf(request.getTsId()));
        AffectedTSCalcNode affectedTSCalcNode = null;
        Map<SimpleTimeseries, Integer> affectedTSMap = tsParamOrderFinder.getAffectedTSMap(request.getTsId());
        for (SimpleTimeseries st: affectedTSMap.keySet()) {
            if ("A".equals(st.getParameterType())) continue;
            short tsId = (short) st.getTsId();
            long graphSeqId = GraphSequenceIDver2.toGraphSequence(dimId, tsId, (short) tbId, dataType, paramerType);
            PlanningVertex original = timeseriesGraph.getVertexMap().get(graphSeqId);
            PlanningVertex dv = (PlanningVertex) original.clone();
            int tsIdInt = (int) tsId;
//            int impactedPlaneId = timeseriesGraph.getTimeseriesPlaneMap().get(tsIdInt);
//            if (planeId != impactedPlaneId) logger.error("plane_id's don't match:" + planeId + "," + impactedPlaneId);
//            CmDefinitions cmd = cmAssociationMap.get(tsIdInt);
//            if (cmd == null ) continue; // means this timeseries doesn't belong to this CMAssociation
//            if (cmd.EXPRESSION_NAME == null) logger.error("EXPRESSION_NAME is null for tsId=" + tsIdInt);
//            String expressionName = cmd.EXPRESSION_NAME;
//            dv.setExpressionName(expressionName);
//            dv.setExpression(cmd.EXPRESSION);
            //dv.setDependents(getDependents(dv, dimId, tsIdInt, tbId));
            int depth = affectedTSMap.get(st);
            if (!depthCovered.contains(depth)) {
                if (Objects.nonNull(affectedTSCalcNode)) affectedTSPath.addTsCalcNode(affectedTSCalcNode);
                affectedTSCalcNode = new AffectedTSCalcNode(depth);
            }
            affectedTSCalcNode.add(dv);
//            affectedTSPath.addTimeSeriesGlobally(dv);
            depthCovered.add(depth);
        }
        return affectedTSPath;

    }

    private Set<PlanningVertex> getDependents(PlanningVertex dv, int dimId, int tsId, int tbId) {
        return timeseriesGraph.getExprParamaterMap().get(tsId).stream().map(st -> {
            long graphSeqId = GraphSequenceIDver2.toGraphSequence(dimId, (short) st.getTsId(), (short) tbId, (short) 1, (short) 1);
            PlanningVertex dtr = new PlanningVertex(graphSeqId, 0.0);
            PlanningVertex temp = timeseriesGraph.getVertexMap().getOrDefault(graphSeqId, null);
            if (Objects.nonNull(temp)) dtr.setValue(temp.getValue());
            return dtr;
        }).collect(Collectors.toSet());
    }

    /*public AffectedTSPath affectedTSPath_old(TimeseriesRequest request) {
        long graphSeqId = GraphSequenceIDver2.toGraphSequence(request.getRelationId(), (short) request.getTsId(), (short) request.getTbId(), request.dataType, request.parameterType);
        SimpleTimeseries start = new SimpleTimeseries(request.getTsId(), request.getName(),
                request.getRelativePos(), request.getAnchorTo());
        BreadthFirstIterator<SimpleTimeseries, DefaultEdge> iterator = new BreadthFirstIterator<SimpleTimeseries, DefaultEdge>(paramGraph.getG(), start);
        AffectedTSPath affectedTSPath = new AffectedTSPath(start.toString());
        LinkedHashMap<SimpleTimeseries, Integer> affectedTSList = new LinkedHashMap<SimpleTimeseries, Integer>();
        String startKey = start.toString();
        LinkedHashMap<String, Integer> printTSList = new LinkedHashMap<String, Integer>();
        Set<String> vertexSet = new HashSet<String>();
        Graph<SimpleTimeseries, DefaultEdge> subGraph = new DirectedAcyclicGraph<>(DefaultEdge.class);
        vertexSet.add(startKey);
        subGraph.addVertex(start);
        Set<Integer> depthCovered = new HashSet<>();
        AffectedTSCalcNode affectedTSCalcNode = null;
        CmDefinitions cmDefinitions = null;
        while (iterator.hasNext()) {
            SimpleTimeseries vertex = iterator.next();
            String vertexKey = vertex.toString();
            if (vertexSet.contains(vertexKey)) continue;
            int depth = iterator.getDepth(vertex);
            if (!depthCovered.contains(depth)) {
                if (Objects.nonNull(affectedTSCalcNode)) affectedTSPath.addTsCalcNode(affectedTSCalcNode);
                affectedTSCalcNode = new AffectedTSCalcNode(depth);
            }
            if (!vertexKey.equals(startKey)) {
                affectedTSList.put(vertex, depth);
                printTSList.put(vertexKey, depth);
                TreeSet <String> dependents = timeseriesGraph.getExprParamaterMap().get(vertex.getTsId()).stream().map(st -> st.toString()).collect(Collectors.toCollection(TreeSet::new));
                vertex.setDependents(dependents);
                cmDefinitions = cmAssociationMap.getOrDefault(vertex.getTsId(), null);
                if (cmDefinitions != null) vertex.setExpression(cmDefinitions.getEXPRESSION());
                affectedTSCalcNode.add(vertex);
                affectedTSPath.addTimeSeriesGlobally(vertex);
            }
            subGraph.addVertex(vertex);
            SimpleTimeseries parentVertex = iterator.getParent(vertex);
            if (vertexSet.contains(parentVertex.toString())) {
                subGraph.addEdge(parentVertex, vertex);
            }
            depthCovered.add(depth);
        }
        return affectedTSPath;
    }*/
}
