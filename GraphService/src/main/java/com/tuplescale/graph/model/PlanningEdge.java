package com.tuplescale.graph.model;

import org.jgrapht.graph.DefaultEdge;

public class PlanningEdge extends DefaultEdge {
    private int relativeOrder;

    /**
     * Constructs a relationship edge
     *
     * @param relativeOrder the label of the new edge.
     * 
     */
    public PlanningEdge(int relativeOrder) {
        this.relativeOrder = relativeOrder;
    }

    /**
     * Gets the label associated with this edge.
     *
     * @return edge label
     */
    public int getRelativeOrder() {
        return relativeOrder;
    }

    @Override
    public String toString() {
        return "(" + getSource() + " : " + getTarget() + " : " + Integer.toString(relativeOrder) + ")";
    }
}