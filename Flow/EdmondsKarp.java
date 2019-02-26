import java.util.*;

public class EdmondsKarp{
    ArrayList<LinkedList<Edge>> graph;
    int[][] flowing;
    int flow;
    int n;

    public EdmondsKarp(ArrayList<LinkedList<Edge>> graph, int s, int t){
        this.graph = graph;
        flow = 0;
        n = graph.size();
        flowing = new int[n][n];
        int[] parent = new int[n];
        int pathFlow;
        int curr, prev;
        while((pathFlow = bfs(s,t, parent)) > 0){
            flow += pathFlow;
            curr = t;
            while(curr != s){
                prev = parent[curr]; 
                flowing[prev][curr] += pathFlow;
                flowing[curr][prev] -= pathFlow;
                curr = prev;
            } 
        }            
    }

    public int[][] getFlowGraph(){
        return flowing.clone();
    }

    public int getMaxFlow(){
        return flow;
    }

    public int bfs(int s, int t, int[] parent){
        boolean[] visited = new boolean[n];
        int[] minCapInPath = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        minCapInPath[s] = Integer.MAX_VALUE;
        int current;
        int resFlow;
        while(!queue.isEmpty()){
            current = queue.poll(); 
            visited[current] = true;
            for(Edge e : graph.get(current)){
                resFlow = e.cap - flowing[current][e.to];
                if(!visited[e.to] && (resFlow > 0)){
                    parent[e.to] = current; 
                    minCapInPath[e.to] = Math.min(minCapInPath[current], resFlow); 
                    if(e.to != t){
                        queue.add(e.to); 
                    } else {
                        return minCapInPath[t];
                    }
                }
            } 
        }
        return 0; 
    }
}
