package com.tuplescale.graph.ds;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuplescale.graph.model.*;
import com.tuplescale.graph.processing.AffectedTSPathFinder;
import com.tuplescale.graph.processing.TSParamOrderFinder;
import com.tuplescale.graph.reader.CSVToBeanIn;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class GraphTest {
    public static void main(String[] args) throws Exception {
        CSVToBeanIn<CmTimeseriesParameters> in = new CSVToBeanIn<CmTimeseriesParameters>(new File("C:\\Work\\Workspace\\graph\\sql\\dump\\CM_TIMESERIES_PARAMETERS_202106221924.csv"), CmTimeseriesParameters.class);
        ParamGraph pg = new ParamGraph(in);
        TSParamOrderFinder tspof = new TSParamOrderFinder(pg);

        final String timeseriesFilepath = "C:\\Work\\Workspace\\graph\\sql\\dump\\timeseries_data.csv";
        CSVToBeanIn<TimeseriesData> timeseriesIn = new CSVToBeanIn<>(new File(timeseriesFilepath), TimeseriesData.class);
       // TimeseriesGraph tg = new TimeseriesGraph(pg, tspof, timeseriesIn);
      //  AffectedTSPathFinder tsPathFinder = new AffectedTSPathFinder(pg, tg, tspof);
        TimeseriesRequest request = new TimeseriesRequest(225, 604, 1, 1);
        //AffectedTSPath affectedTSPath = tsPathFinder.affectedTSPath(request);
        //System.out.println(affectedTSPath.toString());
     //   System.out.println(new ObjectMapper().writeValueAsString(affectedTSPath));
//        print(tg.getG());
    }

    private static void print(Graph<PlanningVertex, PlanningEdge> g) {
        DepthFirstIterator<PlanningVertex, PlanningEdge> iterator = new DepthFirstIterator<>(g);
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            PlanningVertex s = iterator.next();
            sb.append(String.format("\nsource: [%s]" , s.toString()));
            int curr = 1;
            for (PlanningEdge e: g.outgoingEdgesOf(s)) {
                Stack<PlanningVertex> stack = new Stack<>();
                Set<PlanningVertex> visited = new HashSet<>();
                dfs(g, g.getEdgeTarget(e), stack, visited);
                if (stack.isEmpty()) continue;
                sb.append(String.format(" => Child%d path => [%s]", curr++, stack.stream().map(t -> t.toString()).collect(Collectors.joining(","))));
            }
        }
        System.out.println(sb);
    }

    private static void dfs(Graph<PlanningVertex, PlanningEdge> g, PlanningVertex v, Stack<PlanningVertex> stack, Set<PlanningVertex> visited) {
        visited.add(v);
        for (PlanningEdge e : g.outgoingEdgesOf(v)) {
            if (!visited.contains(g.getEdgeTarget(e))) {
                dfs(g, g.getEdgeTarget(e), stack, visited);
            }
        }
        stack.push(v);
    }
}
