package sample.Graph;

// Used to save the vertices pairs (edges) for a vertex
public class Edge {

    private int[] verticesIds; // Contains the ids of the vertices
    private int[] costsToReachVertices; // Contains the costs to reach the vertices (weight)

    public Edge(){}

    // Adds the given vertex to the pair set and removes the current farthest
    public void replaceVertex(int vertexId, int costToReachVertex, int prev){

        if(containsVertex(vertexId))
            return;

        int worstVertexId = 0;
        int costToReachWorstVertex = -1 * Integer.MAX_VALUE;
        for(int i = 0; i < verticesIds.length; i++)
        {
            if (prev != verticesIds[i] && costToReachWorstVertex <= costsToReachVertices[i])
            {
                worstVertexId = i;
                costToReachWorstVertex = costsToReachVertices[i];
            }
        }
        verticesIds[worstVertexId] = vertexId;
        costsToReachVertices[worstVertexId] = costToReachVertex;
    }

    private boolean containsVertex(int vertexId) {
        for (int verticesId : verticesIds) {
            if (vertexId == verticesId)
                return true;
        }
        return false;
    }

    public void setVerticesIds(int[] verticesIds) {
        this.verticesIds = new int[verticesIds.length];
        System.arraycopy(verticesIds, 0, this.verticesIds, 0, this.verticesIds.length);
    }

    public void setCostsToReachVertices(int[] costsToReachVertices) {
        this.costsToReachVertices = new int[costsToReachVertices.length];
        System.arraycopy(costsToReachVertices, 0, this.costsToReachVertices, 0, this.costsToReachVertices.length);
    }

    public int[] getVerticesIds() {
        return verticesIds;
    }

    public int[] getCostsToReachVertices() {
        return costsToReachVertices;
    }
}
