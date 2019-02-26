public class Edge{
    public int to;
    public int weight;
    public int t0;
    public int tInc;

    public Edge(int to, int weight){
        this.to = to;
        this.weight = weight;
    }

    public Edge(int to, int weight, int t0, int tInc){
        this.to = to;
        this.weight = weight;
        this.t0 = t0;
        this.tInc = tInc;
    }
}
