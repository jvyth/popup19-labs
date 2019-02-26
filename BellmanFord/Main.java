import java.util.*;

public class Main{
    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        int n;
        int m;
        int q;
        int s;

        while(kattio.hasMoreTokens()){
            n = kattio.getInt();
            m = kattio.getInt();
            q = kattio.getInt();
            s = kattio.getInt();

            if(n + m + q + s == 0){
                kattio.close();
                return;
            }

            ArrayList<LinkedList<Edge>> graph = new ArrayList<>();

            for(int i = 0; i < n; i++){
                graph.add(new LinkedList<Edge>());
            }
            int from, to, weight;
            for(int i = 0; i < m; i++){
                from = kattio.getInt();
                to = kattio.getInt();
                weight = kattio.getInt();
                graph.get(from).add(new Edge(to, weight));
            }

            BellmanFord bf = new BellmanFord(graph, s);
            int[] parents = bf.shortestPath();

            int query;
            long ans;
            for(int i = 0; i < q; i++){
                query = kattio.getInt();
                ans = bf.getMinDistTo(query);
                if(ans == Long.MAX_VALUE){
                    //System.out.println("Impossible");
                    kattio.println("Impossible");
                } else if(ans == Long.MIN_VALUE){
                    //System.out.println(ans);
                    kattio.println("-Infinity");
                } else {
                    kattio.println(ans);
                }
            }
            //System.out.println();
            kattio.println();
        }
    }
}
