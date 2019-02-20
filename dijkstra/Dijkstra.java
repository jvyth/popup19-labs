import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ListIterator;

public class Dijkstra{
    PriorityQueue<Node> tentative;
    int[] parents;
    Node[] nodes;

    public Dijkstra(ArrayList<LinkedList<Edge>> nb, int s){
        tentative = new PriorityQueue<>();
        parents = new int[nb.size()];
        nodes = new Node[nb.size()];
        for(int i = 0; i < nb.size(); i++){
            nodes[i] = new Node(i);
        }
        nodes[s].accWeight = 0;
        Node current = nodes[s];
        Node neigh;
        Node parent;
        Edge edge;
        LinkedList<Edge> nbs;
        ListIterator<Edge> it;
        tentative.add(nodes[s]);
        while(!tentative.isEmpty()){
            parent = current;
            current = tentative.poll();
            parents[current.index] = parent.index;
            current.visited = true;
            it = nb.get(current.index).listIterator();
            while(it.hasNext()){
                edge = it.next();
                neigh =  nodes[edge.to];
                if(!neigh.visited){
                    if(neigh.accWeight > current.accWeight + edge.weight){
                        if(tentative.contains(neigh)){
                            tentative.remove(neigh);
                        }
                        neigh.accWeight = current.accWeight + edge.weight;
                        tentative.add(neigh);
                    }
                }
            }
        }
    }

    public int[] shortestPath(){
        return parents;
    }

    public int getAccWeight(int index){
        return nodes[index].accWeight;
    }

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

            Dijkstra djik = new Dijkstra(graph, s);
            int[] parents = djik.shortestPath();

            int query;
            int ans;
            for(int i = 0; i < q; i++){
                query = kattio.getInt();
                ans = djik.getAccWeight(query);
                if(ans == Integer.MAX_VALUE){
                    //System.out.println("Impossible");
                    kattio.println("Impossible");
                } else {
                    //System.out.println(ans);
                    kattio.println(ans);
                }
            }
            //System.out.println();
            kattio.println();
        }
    }
}
