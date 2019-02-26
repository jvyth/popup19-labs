import java.util.*;

public class BellmanFord{
    int[] parent;
    long[] dist;
    ArrayList<LinkedList<Edge>> edges;

    public BellmanFord(ArrayList<LinkedList<Edge>> edges, int source){
        this.edges = edges;
        int n = edges.size();
        parent = new int[n];
        dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);        
        dist[source] = 0;
        Edge e;
        long potentialBetterDist;
        for(int i = 0; i < n; ++i){
            for(int from = 0; from < n; ++from){
                Iterator<Edge> it = edges.get(from).iterator();
                while(it.hasNext()){
                    e = it.next();      
                    potentialBetterDist = dist[from] + e.weight;
                    if(dist[from] == Long.MAX_VALUE){
                        continue;
                    }
                    if(potentialBetterDist < dist[e.to]){
                        dist[e.to] = potentialBetterDist;
                        parent[e.to] = from;
                    } 
                }
            }
        }
        
        //System.out.println(Arrays.toString(dist));
        for(int from = 0; from < n; ++from){
            Iterator<Edge> it = edges.get(from).iterator();
            while(it.hasNext()){
                e = it.next();      
                if(dist[from] == Long.MIN_VALUE || dist[from] == Long.MAX_VALUE){
                    continue;
                } else { 
                    potentialBetterDist = dist[from] + e.weight;
                }
                if(potentialBetterDist < dist[e.to]){
                    dist[from] = Long.MIN_VALUE;
                    markInf(from);
                } 
            }
        }
       // while(!stack.isEmpty()){
       //     dist[stack.pop()] = Long.MIN_VALUE;
       // }
    } 

    private void markInf(int from){
        boolean[] visited = new boolean[edges.size()];
        Stack<Integer> stack = new Stack<>(); 
        stack.push(from);
        int current;
        int neigh;
        while(!stack.empty()){
            current = stack.pop();    
            visited[current] = true;
            LinkedList<Edge> neighs = edges.get(current);
            Iterator<Edge> it = neighs.iterator();
            while(it.hasNext()){
                neigh = it.next().to;
                if(!visited[neigh]){
                    dist[neigh] = Long.MIN_VALUE;
                    stack.push(neigh);
                }
            }
        }
    }

    public long getMinDistTo(int target){
        return dist[target];
    }

    public int[] shortestPath(){
        return parent.clone();
    }
}
