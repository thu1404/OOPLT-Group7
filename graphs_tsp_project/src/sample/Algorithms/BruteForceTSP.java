package sample.Algorithms;

import sample.Graph.Vertex;

import java.util.Stack;

public class BruteForceTSP extends Algorithm {
    private int n; // Representing the total vertices of the graph
    private int [][] graph; // Representing the adjacency matrix of the graph
    private boolean[] visitedVertices; // Representing which vertices have been currently visited
    private int shortestPathCost; // Representing the total cost of the path taken
    private Vertex lastVertex; // The last visited vertex before reaching the root again (Contains pointers with the path taken)

    public BruteForceTSP(int[][] graph)
    {
        this.n = graph.length;
        this.graph = graph;
        this.visitedVertices = new boolean[n];
        this.shortestPathCost = Integer.MAX_VALUE;
        this.lastVertex = null;

        // Mark first vertex as visited since we use it as the root
        visitedVertices[0] = true;
    }

    // Returns an integer representing the shortest's path cost
    public int getShortestPathCost() {
        backTracking(new Vertex(0,null,0),  1);
        return shortestPathCost;
    }

    // Method to find the shortest path
    private void backTracking(Vertex currentVertex, int verticesVisited)
    {
        // If last vertex is reached and it has a link to the root vertex then
        // keep the minimum value out of the total cost
        // of traversal and "ans"
        // Returning to check for more possible values
        if (verticesVisited == n && graph[currentVertex.getId()][0] > 0)
        {
            if (shortestPathCost <= currentVertex.getCostToReach() + graph[currentVertex.getId()][0])
                return;

                // Better path found
            else
            {
                shortestPathCost = currentVertex.getCostToReach() + graph[currentVertex.getId()][0];
                lastVertex = currentVertex;
                return;
            }
        }

        // Loop to traverse the adjacency list of the current vertex and increasing the visited vertices
        // by 1, moving to the next vertex and increasing the new vertex cost by graph[currentVertex,i] value
        for (int i = 0; i < n; i++)
        {
            if (!visitedVertices[i] && graph[currentVertex.getId()][i] > 0)
            {
                // Mark as visited
                visitedVertices[i] = true;

                Vertex nextVertex =  new Vertex(i, currentVertex, currentVertex.getCostToReach() + graph[currentVertex.getId()][i]);
                backTracking(nextVertex, verticesVisited + 1);

                // Mark ith node as unvisited after the recursion return
                visitedVertices[i] = false;
            }
        }
    }

    // Returns a Stack of integers representing the path of the best shortest route to take
    public Stack<Integer> getShortestPath() {

        Stack<Integer> shortestPath = new Stack<>();
        shortestPath.push(0);

        Vertex currentVertex  = lastVertex;

        while(currentVertex != null)
        {
            shortestPath.push(currentVertex.getId());
            currentVertex = currentVertex.getPrev();
        }

        return shortestPath;
    }

    public static void printResult(int[][] graph) {
        Stack<Integer> bestPath;
        long start;
        long end;
        float elapsedTimeInSec;
        System.out.println("Brute force: ");
        // finding the time before the operation is executed
        start = System.currentTimeMillis();
        // Finds the shortest path cost with the brute force
        BruteForceTSP bruteForceTSP = new BruteForceTSP(graph);
        try {
            System.out.println("Path cost: " + bruteForceTSP.getShortestPathCost());
            bestPath = bruteForceTSP.getShortestPath();

            System.out.print("Path Taken: " + bestPath.pop());
            while (!bestPath.isEmpty())
                System.out.print("-> " + bestPath.pop());

            System.out.println();

            // finding the time after the operation is executed
            end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            elapsedTimeInSec = (end - start) / 1000F;
            System.out.println(elapsedTimeInSec + " seconds");
            System.out.println();
        } catch (OutOfMemoryError error) {
            bruteForceTSP = null;
            System.out.println("Error: Max heap size reached");
        }
    }

}
