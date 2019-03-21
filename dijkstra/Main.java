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

            int query;
            int ans;
            Dijkstra djik;
            for(int i = 0; i < q; i++){
                query = kattio.getInt();
                djik = new Dijkstra(graph, s, query); 
                ans = djik.getAccWeight(query);
                if(ans == Integer.MAX_VALUE){
                    kattio.println("Impossible");
                } else {
                    kattio.println(ans);
                }
            }
            kattio.println();
        }
        kattio.close();
    }
}
