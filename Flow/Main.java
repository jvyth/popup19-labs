import java.util.*;

public class Main{
    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        int n = kattio.getInt();
        int m = kattio.getInt();
        int s = kattio.getInt();
        int t = kattio.getInt();
        ArrayList<LinkedList<Edge>> graph = new ArrayList<LinkedList<Edge>>(); 
        for(int i = 0; i < n; ++i){
            graph.add(new LinkedList<Edge>());
        }

        int from, to, cap;
        for(int i = 0; i < m; ++i){
            from = kattio.getInt(); 
            to = kattio.getInt(); 
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
                    sb.append(i).append(" ").append(e.to).append(" ").append(flow[i][e.to]).append("\n");
                    counter++;
                } 
            }
        }
        kattio.printf("%d %d %d\n", n, ek.getMaxFlow(), counter); 
        kattio.print(sb.toString());
        kattio.close();
    }
}
