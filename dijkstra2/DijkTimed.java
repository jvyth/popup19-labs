import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ListIterator;

public class DijkTimed {
    PriorityQueue < Node > tentative;
    int[] parents;
    Node[] nodes;

    public DijkTimed(ArrayList<LinkedList<Edge>> nb, int s) {
        tentative = new PriorityQueue < > ();
        parents = new int[nb.size()];
        nodes = new Node[nb.size()];
        for (int i = 0; i < nb.size(); i++) {
            nodes[i] = new Node(i);
        }
        nodes[s].accWeight = 0;
        Node current = nodes[s];
        Node neigh;
        Node parent;
        Edge edge;
        LinkedList <Edge> nbs;
        ListIterator <Edge> it;
        tentative.add(nodes[s]);
        long waitTime = Long.MAX_VALUE;
        long currTime;
        long arrivalTime; 
        int traversalTime;
        int firstTimeSlot;
        while (!tentative.isEmpty()) {
            parent = current;
            current = tentative.poll();
            parents[current.index] = parent.index;
            current.visited = true;
            currTime = current.accWeight;
            it = nb.get(current.index).listIterator();
            while (it.hasNext()) {
                edge = it.next();
                traversalTime = edge.weight;
                firstTimeSlot = edge.t0;
                neigh = nodes[edge.to];
                arrivalTime = neigh.accWeight;

                /*       -------------------Cases----------------------
                 * 1) currTime <= firstTimeSlot -> We wait for firstTimeSlot
                 * 2) currTime > firstTimeSlot -> We wait for next available slot
                 * 3) currTime > firstTimeSlot && no increment -> Can't reach! 
                 */
                if (!neigh.visited) { //Don't go through already visited nodes, they're already best case
                    if(currTime <= firstTimeSlot){ //Wait for first timeSlot. 
                        waitTime = firstTimeSlot - currTime;
                    }
                    else if ((edge.tInc != 0) && (currTime > firstTimeSlot)) { //Else we have to find next timeslot. Find how long we've already waited since last available slot. Subtract from inc.
                        if(((currTime - firstTimeSlot) % edge.tInc) == 0){
                            waitTime = 0;
                        } else {
                            waitTime = edge.tInc - ((currTime - firstTimeSlot) % edge.tInc); 
                        }
                    } else { // If currTime > firstTimeSlot && edge.tInc == 0
                        continue;
                    }
                    // System.out.println("accWeight: " + currTime + ", t0: " + firstTimeSlot + ", tInc: " + edge.tInc);
                    // System.out.println("waitTime: " + waitTime);
                    if (arrivalTime > currTime + traversalTime + waitTime) {
                        if (tentative.contains(neigh)) {
                            tentative.remove(neigh);
                        }
                        arrivalTime = currTime + traversalTime + waitTime;
                        //System.out.println(waitTime);
                        //System.out.println(arrivalTime);
                        neigh.accWeight = arrivalTime;
                        tentative.add(neigh);
                    }
                }
            }
        }
    }

    public int[] shortestPath() {
        return parents;
    }

    public long getAccWeight(int index) {
        return nodes[index].accWeight;
    }

    private class Node implements Comparable < Node > {
        public int index;
        public long accWeight;
        public boolean visited;

        public Node(int index) {
            this.index = index;
            this.accWeight = Long.MAX_VALUE;
            visited = false;
        }

        public int compareTo(Node node) {
            return (int) accWeight - (int) node.accWeight;
        }
    }


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
            int[] parents = djik.shortestPath();

            int query;
            long ans;
            for (int i = 0; i < q; i++) {
                query = kattio.getInt();
                ans = djik.getAccWeight(query);
                if (ans == Long.MAX_VALUE) {
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
