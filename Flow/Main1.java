import java.util.*;

public class Main1{
    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        int n = kattio.getInt();
        int s = kattio.getInt() - 1;
        int t = kattio.getInt() - 1;
        int m = kattio.getInt();
        ArrayList<LinkedList<Edge>> graph = new ArrayList<LinkedList<Edge>>(); 
        for(int i = 0; i < n; ++i){
            graph.add(new LinkedList<Edge>());
        }

        int from, to, cap;
        for(int i = 0; i < m; ++i){
            from = kattio.getInt() - 1; 
            to = kattio.getInt() - 1; 
            cap = kattio.getInt(); 

            graph.get(from).add(new Edge(to, cap));
            graph.get(to).add(new Edge(from, 0));
        }

        EdmondsKarp ek = new EdmondsKarp(graph, s, t); 
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        int[][] flow = ek.getFlowGraph(); 
        for(int i = 0; i < n; ++i){
            for(Edge e : graph.get(i)){
                if(e.cap != 0 && flow[i][e.to] > 0){
                    sb.append(i+1).append(" ").append(e.to+1).append(" ").append(flow[i][e.to]).append("\n");
                    counter++;
                } 
            }
        }
        kattio.printf("%d\n%d %d %d\n%d\n", n, s+1, t+1, ek.getMaxFlow(), counter); 
        kattio.print(sb.toString());
        kattio.close();
    }
}
