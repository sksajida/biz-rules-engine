package com.tuplescale.graph.ds;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.tuplescale.graph.model.CmDefinitions;
import com.tuplescale.graph.model.PlanningEdge;
import com.tuplescale.graph.model.PlanningVertex;
import com.tuplescale.graph.model.SimpleTimeseries;
import com.tuplescale.graph.model.TimeseriesData;
import com.tuplescale.graph.model.TimeseriesUpdateResponse;
import com.tuplescale.graph.processing.TSParamOrderFinder;
import com.tuplescale.graph.reader.CMDefinitionsReader;
import com.tuplescale.graph.reader.CSVToBeanIn;
import com.tuplescale.graph.reader.TimeSeriesMetaReader;
import com.tuplescale.graph.reader.TimeseriesParamsReader;
import com.tuplescale.graph.util.GraphSequenceIDver2;

@Component
public class TimeseriesGraph {
    private static Logger logger = LoggerFactory.getLogger(TimeseriesGraph.class);

    @Autowired
    private ParamGraph paramGraph;

    @Autowired
    private TSParamOrderFinder tspof;

  //  private Graph<PlanningVertex, PlanningEdge> g =
    //        new DirectedAcyclicGraph<PlanningVertex, PlanningEdge>(PlanningEdge.class);
    
    
    MutableGraph<PlanningVertex> myGraph= GraphBuilder.directed().build();

    private TreeMap<Integer, SimpleTimeseries> execOrderMap;

    private Map<Integer, Integer> timeseriesPlaneMap;

    private Map<Integer, CmDefinitions> cmAssociationMap;

    private LinkedListMultimap<Integer, SimpleTimeseries> exprParamaterMap = null;

    private int planesMatched = 0;

    private LinkedHashMap<Long, PlanningVertex> vertexMap = new LinkedHashMap<Long, PlanningVertex>();

    public TimeseriesGraph(@Autowired ParamGraph paramGraph,
                           @Autowired TSParamOrderFinder tspof,
                           @Autowired @Qualifier("timeseriesDataIn") CSVToBeanIn in) throws Exception {
        if (in == null) throw new IllegalArgumentException("argument in is null");
        this.paramGraph = paramGraph;
        this.tspof = tspof;
        short dataType = 1; // numeric
        short paramerType = 1; // timeseries
        long seqCount = 0;
        timeseriesPlaneMap = TimeSeriesMetaReader.getTimeSeriesPlaneMap();
        cmAssociationMap = CMDefinitionsReader.cmAssociationMap();
        exprParamaterMap = TimeseriesParamsReader.getExprParamMap();
        this.execOrderMap = tspof.getExecOrderMap();
        Set<Integer> nodeIdSet=new HashSet<>();
        while (in.hasNext()) {
            TimeseriesData td = (TimeseriesData) in.readNext();
            
            int relationId = td.getRELATION_ID();
            short tsId = (short) td.getTS_ID();
            short tbId = (short) td.getTB_ID();
            double value = td.getVALUE();
            long graphSeqId = GraphSequenceIDver2.toGraphSequence(relationId, tsId, tbId, dataType, paramerType);
            PlanningVertex pv = new PlanningVertex(graphSeqId, value);
            int tsIdint = (int) tsId;
            if (!timeseriesPlaneMap.containsKey(tsIdint))
                logger.error("tsId not found in plane map: " + tsIdint);
            //int planeId = timeseriesPlaneMap.get(tsIdint);
            if (cmAssociationMap.containsKey(tsIdint)) {
                CmDefinitions cmd = cmAssociationMap.get(tsIdint);
                String expressionName = cmd.EXPRESSION_NAME;
                pv.setExpressionName(expressionName);
                pv.setExpression(cmd.EXPRESSION);
            }
            //if graphSeqId not present add pv as new node 
            if (!vertexMap.containsKey(graphSeqId)) {
                //g.addVertex(pv);
                myGraph.addNode(pv);
                //System.out.println(" ne Vertex  "+pv);
                vertexMap.put(graphSeqId, pv);
            } else {
            	//if grapseqId is present update node value 
                pv = vertexMap.get(graphSeqId);
                pv.setValue(value);
                continue;
            }
           
            //if(tsIdint == 228) logger.debug("plane_id for 228 tsId:" + planeId);
            //addImpactedVertexNew(planeId, pv);
            //if(tsIdint == 228) logger.debug("plane_id for 228 tsId:" + planeId);
            //dependentNode.put(planeId, pv);
            
//            if(myGraph.nodes().size() >= 1000000) {
//            	logger.info(" breaking ..........");
//            	break;
//            }
            
            seqCount++;
            if (seqCount % 1000 == 0) {
                logger.info("Seq count is {}, latest1 record: {} , size {}", seqCount, td.ID,myGraph.nodes().size());
            }
       }//while loop close
//        if(!in.hasNext()) {
//        	Set<Entry<Integer,PlanningVertex>> entrySet=dependentNode.entrySet();
//        	System.out.println(entrySet.size());
//        	for(Entry<Integer,PlanningVertex> entry:entrySet) {
//        		
//        		addImpactedVertexNew(entry.getKey(), entry.getValue());
//        	}
//        }
        myGraph.nodes().forEach(node->{
        	nodeIdSet.add(node.getTsId());
        });
        nodeIdSet.forEach(System.out::println);
        for(PlanningVertex node:myGraph.nodes()) {
        	addImpactedVertexNew(timeseriesPlaneMap.get(node.getTsId()), node);
        }
        logger.info("Num planes matched is: {}", planesMatched);
    }


    private void addImpactedVertexNew(Integer planeId, PlanningVertex pv) throws Exception {
        long vertextId = pv.getVertexId();
        int tsIdv = (int) GraphSequenceIDver2.getTsId(vertextId);
        dfs(pv.getVertexId(), pv, new SimpleTimeseries(tsIdv), 1);
    }

    private final short dataType = 1; // numeric
    private final short paramerType = 1; // timeseries

    private PlanningVertex constructDv(long vertexId, SimpleTimeseries st) {
        int dimId = GraphSequenceIDver2.getDetailId(vertexId);
        short tbId = GraphSequenceIDver2.getPeriodId(vertexId);
        long graphSeqId = GraphSequenceIDver2.toGraphSequence(dimId, (short) st.getTsId(), tbId, dataType, paramerType);
        PlanningVertex dv = null;
        if (vertexMap.containsKey(graphSeqId)) dv=vertexMap.get(graphSeqId);

//        PlanningVertex dv = new PlanningVertex(graphSeqId, 0.0d);
//        CmDefinitions cmd = cmAssociationMap.get(st.getTsId());
//        if (cmd == null) throw new NoSuchElementException("Timeseries doesn't belong to the CMAssociation..."); // means this timeseries doesn't belong to this CMAssociation
//        if (cmd.EXPRESSION_NAME == null) logger.error("EXPRESSION_NAME is null for tsId=" + st.getTsId());
//        String expressionName = cmd.EXPRESSION_NAME;
//        dv.setExpressionName(expressionName);
//        dv.setExpression(cmd.EXPRESSION);
//        //g.addVertex(dv);
//        myGraph.addNode(dv);
//       // System.out.println(" dv Vertex  "+dv);
//        vertexMap.put(graphSeqId, dv);
        return dv;
    }

    private void dfs(long startVertex, PlanningVertex pv, SimpleTimeseries pvSt, int relativeOrder) {
        if (paramGraph.getG().containsVertex(pvSt)) {
        	 
            for (DefaultEdge e : paramGraph.getG().outgoingEdgesOf(pvSt)) {
                SimpleTimeseries st = paramGraph.getG().getEdgeTarget(e);
                PlanningVertex dv = constructDv(startVertex, st);
               
                if (dv==null) continue; 
                    //g.addEdge(pv, dv, new PlanningEdge(relativeOrder));
                    myGraph.putEdge(pv, dv);
                    System.out.println(pv+" dfs edge  "+dv);
                    dfs(startVertex, dv, st, relativeOrder + 1);
            }
        }
        addExpressionEdges(GraphSequenceIDver2.getDetailId(startVertex), pv.getTsId(), GraphSequenceIDver2.getPeriodId(startVertex), pv);
    }


    private void addExpressionEdges(int dimId, int tsId, short tbId, PlanningVertex upstr) {
        if (!exprParamaterMap.containsKey(tsId)) return;
        List<SimpleTimeseries> stList = exprParamaterMap.get(tsId);
        if (stList.isEmpty()) return;
        int relativeOrder = 0;
        int anchorTBId = 586;
        for (SimpleTimeseries st : stList) {
            short relPos = (short) st.getRelativePos();
            String anchorTo = st.getAnchorTo();
            short paramTsId = (short) st.getTsId();
            String attOrTs = st.getParameterType();
            short parameterType = 1; // Timeseries
            short dataType = 1; // Numeric
            short localTBId = tbId;
			/*if ("T".equals(attOrTs)) {
				if (anchorTo.equals("CURRENT_EFFECTIVE_PERIOD")) {
					localTBId = (short) (localTBId + relPos);
				} else if (anchorTo.equals("IMPORT_PERIOD")) {
					localTBId = (short) (anchorTBId + relPos);
				}
			}*/
            if ("A".equals(attOrTs)) parameterType = 2; // Attribute
            long graphSeqId = GraphSequenceIDver2.toGraphSequence(dimId, paramTsId, localTBId, dataType, parameterType);
            PlanningVertex dstr = null;
            if (vertexMap.containsKey(graphSeqId)) {
                dstr = vertexMap.get(graphSeqId);
            } else {
                dstr = new PlanningVertex(graphSeqId, 0.0d);
               // g.addVertex(dstr);
                myGraph.addNode(dstr);
                //System.out.println(" exp Node  "+dstr);
            }
            relativeOrder++;
            try {
              //  g.addEdge(dstr, upstr, new PlanningEdge(relativeOrder));
                myGraph.putEdge(dstr, upstr);
                //System.out.println(dstr+" exp edge  "+upstr);
            } catch (Exception e) {
                logger.error(dstr.toString() + "," + upstr.toString());
            }
        }
    }

    public TimeseriesUpdateResponse update(Map<Long, Double> timeseriesUpdateMap) {
        int numUpdated = 0;
        int numUpdateFailed = 0;
        Set<Long> failedTimeseriesSet = new HashSet<>();
        for (Map.Entry<Long, Double> e : timeseriesUpdateMap.entrySet()) {
            PlanningVertex pv = vertexMap.get(e.getKey());
            if (Objects.nonNull(pv)) {
                pv.setValue(e.getValue());
              //  g.addVertex(pv);
                myGraph.addNode(pv);
                numUpdated++;
            } else {
                numUpdateFailed++;
                failedTimeseriesSet.add(e.getKey());
            }
        }
        return new TimeseriesUpdateResponse(numUpdated, numUpdateFailed, failedTimeseriesSet);
    }

    public Graph<PlanningVertex, PlanningEdge> getG() {
        return null; //g;
    }
    
    
    public MutableGraph<PlanningVertex> getMyGraph() {
        return myGraph;
    }

    public void setG(Graph<PlanningVertex, PlanningEdge> g) {
        //this.g = g;
    }

    public TreeMap<Integer, SimpleTimeseries> getExecOrderMap() {
        return execOrderMap;
    }

    public void setExecOrderMap(TreeMap<Integer, SimpleTimeseries> execOrderMap) {
        this.execOrderMap = execOrderMap;
    }

    public Map<Integer, Integer> getTimeseriesPlaneMap() {
        return timeseriesPlaneMap;
    }

    public void setTimeseriesPlaneMap(Map<Integer, Integer> timeseriesPlaneMap) {
        this.timeseriesPlaneMap = timeseriesPlaneMap;
    }

    public Map<Integer, CmDefinitions> getCmAssociationMap() {
        return cmAssociationMap;
    }

    public void setCmAssociationMap(Map<Integer, CmDefinitions> cmAssociationMap) {
        this.cmAssociationMap = cmAssociationMap;
    }

    public LinkedListMultimap<Integer, SimpleTimeseries> getExprParamaterMap() {
        return exprParamaterMap;
    }

    public void setExprParamaterMap(LinkedListMultimap<Integer, SimpleTimeseries> exprParamaterMap) {
        this.exprParamaterMap = exprParamaterMap;
    }

    public LinkedHashMap<Long, PlanningVertex> getVertexMap() {
        return vertexMap;
    }

    public void setVertexMap(LinkedHashMap<Long, PlanningVertex> vertexMap) {
        this.vertexMap = vertexMap;
    }

    public static void main(String[] args) {
//        final String filepath = "C:\\Work\\Workspace\\graph\\sql\\dump\\timeseries_data.csv";
//        CSVToBeanIn in = new CSVToBeanIn(new File(filepath), TimeseriesData.class);
//        TimeseriesGraph g = new TimeseriesGraph(in);
    }

    public PlanningVertex getVertex(long vertexId) {
        PlanningVertex pv = vertexMap.get(vertexId);
        try {
            return Objects.nonNull(pv) ? (PlanningVertex) pv.clone() : null;
        } catch (CloneNotSupportedException e) {
            logger.error("Clone is failed", e);
            return null;
        }
    }
}
