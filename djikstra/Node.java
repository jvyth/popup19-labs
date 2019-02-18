public class Node implements Comparable<Node>{
    public int index; 
    public int accWeight; 
    public boolean visited;

    public Node(int index){
        this.index = index;
        this.accWeight = Integer.MAX_VALUE;
        visited = false;
    }

    public int compareTo(Node node){
        if(this.accWeight < node.accWeight) {
            return -1;
        } else if (this.accWeight > node.accWeight) {
            return 1;
        } else  {
            return 0;
        }
    }
}
