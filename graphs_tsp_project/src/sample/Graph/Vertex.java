package sample.Graph;

public class Vertex{
    private int id; // The id of the current vertex
    private int costToReach; // The total cost so far to reach this vertex
    private Vertex prev; // The previous vertex before reaching this

    public Vertex(int id, Vertex prev, int costToReach) {
        this.id = id;
        this.prev = prev;
        this.costToReach = costToReach;
    }

    public int getId() {
        return id;
    }

    public int getCostToReach() {
        return costToReach;
    }

    public Vertex getPrev() {
        return prev;
    }
}
