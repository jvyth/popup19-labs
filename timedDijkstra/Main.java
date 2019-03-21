import java.util.*;

public class Main{
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        int n;
        int m;
        int q;
        int s;

        while (kattio.hasMoreTokens()) {
            n = kattio.getInt();
            m = kattio.getInt();
            q = kattio.getInt();
            s = kattio.getInt();

            if (n + m + q + s == 0) {
                kattio.close();
                return;
            }

            ArrayList < LinkedList < Edge >> graph = new ArrayList < > ();

            for (int i = 0; i < n; i++) {
                graph.add(new LinkedList < Edge > ());
            }
            int from, to, t0, tInc, weight;
            for (int i = 0; i < m; i++) {
                from = kattio.getInt();
                to = kattio.getInt();
                t0 = kattio.getInt();
                tInc = kattio.getInt();
                weight = kattio.getInt();
                graph.get(from).add(new Edge(to, weight, t0, tInc));
            }

            DijkTimed djik = new DijkTimed(graph, s);
           // LinkedList<Integer> path = djik.shortestPath(graph.size()-1);
           // if(!(path == null)){
           //     for(Integer node : path) {
           //         System.out.print(node + " "); 
           //     }
           //     System.out.println();
           // } else {
           //     System.out.println("no path");
           // }

            int query;
            long ans;
            for (int i = 0; i < q; i++) {
                query = kattio.getInt();
                ans = djik.getAccWeight(query);
                if (ans == Long.MAX_VALUE) {
                    kattio.println("Impossible");
                } else {
                    kattio.println(ans);
                }
            }
            kattio.println();
        }
    }
}
