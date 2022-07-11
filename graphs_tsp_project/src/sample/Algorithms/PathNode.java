package sample.Algorithms;

import sample.Graph.Edge;

import java.util.ArrayList;

// Represents a node (state), which contains information of the path taken for this state
class PathNode implements Comparable<PathNode>{
    private int currentVertexId; // The if of the vertex this path has last visited
    private double lb; // Representing the lower bound of this state
    private boolean[] visitedVertices; // Representing which vertices have been visited so far
    private int totalVisitedVertices; // The number of the vi
    // sited vertices
    private int pathTotalCost; // The total cost of the path taken so far
    private ArrayList<Edge> edges; // Used to calculate the lower bound, contains the pairs with the edges for each vertex
    private PathNode previousPathNode; // Pointer to the previous path node

    // Overridden compareTo method, in order to compare two paths first by their lower bound in the priority queue used
    @Override
    public int compareTo(PathNode p2) {
        int lbComparison = Double.compare(this.getLb(),p2.getLb());
        if (lbComparison != 0)
            return lbComparison;
        else
        return -1;
    }

    // Constructor for the initial path node
    PathNode(int currentVertexId, ArrayList<Edge> closestPairs)
    {
        edges = closestPairs;
        visitedVertices = new boolean[closestPairs.size()];
        visitedVertices[currentVertexId] = true;
        totalVisitedVertices = 1;
        previousPathNode = null;
        pathTotalCost = 0;
        lb = calculateLowerBound();
    }

    // Constructor used for each path  node
    PathNode(int vertexToVisit, int costToReachVertex, PathNode parentPathNode) {

        this.previousPathNode = parentPathNode;

        // Copying the previous path taken so far
        this.edges = new ArrayList<>();
        for (Edge edge : parentPathNode.getEdge())
        {
            Edge copyEdge = new Edge();
            copyEdge.setVerticesIds(edge.getVerticesIds());
            copyEdge.setCostsToReachVertices(edge.getCostsToReachVertices());
            edges.add(copyEdge);
        }

        // Sets the vertex which is going to be visited
        this.currentVertexId = vertexToVisit;

        // Copying the previous visited vertices so far and sets the current as visited
        visitedVertices = new boolean[parentPathNode.getVisitedVertices().length];
        for (int i = 0; i < visitedVertices.length; i++)
            visitedVertices[i] = parentPathNode.getVisitedVertices()[i];
        visitedVertices[currentVertexId] = true;


        // Replacing the vertices pairs with the edges of the two vertices used
        if(parentPathNode.getPreviousPathNode() != null)
            edges.get(parentPathNode.getCurrentVertexId()).replaceVertex(currentVertexId,costToReachVertex,parentPathNode.getPreviousPathNode().getCurrentVertexId());
        else
            edges.get(parentPathNode.getCurrentVertexId()).replaceVertex(currentVertexId,costToReachVertex,-1);
        edges.get(currentVertexId).replaceVertex(parentPathNode.getCurrentVertexId(),costToReachVertex,parentPathNode.getCurrentVertexId());

        totalVisitedVertices = parentPathNode.getTotalVisitedVertices() + 1;
        pathTotalCost = parentPathNode.getPathTotalCost() + costToReachVertex;
        lb = calculateLowerBound();
    }

    // Calculates and returns the lower bound for this path
    private double calculateLowerBound(){
        double sum = 0 ;
        for (Edge edge : edges)
            sum += edge.getCostsToReachVertices()[0] + edge.getCostsToReachVertices()[1];
        return sum/2;
    }

    int getCurrentVertexId() {
        return currentVertexId;
    }

    double getLb() {
        return lb;
    }

    PathNode getPreviousPathNode() {
        return previousPathNode;
    }

    boolean[] getVisitedVertices() {
        return visitedVertices;
    }

    int getPathTotalCost() {
        return pathTotalCost;
    }

    private ArrayList<Edge> getEdge() {
        return edges;
    }

    int getTotalVisitedVertices(){
        return totalVisitedVertices;
    }
}
