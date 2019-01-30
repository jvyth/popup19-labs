import java.util.ArrayList;

public class UnionFind {
  private class Node {
    int val;
    public Node parent;

    public Node(int x) {
      this.val = x;
      this.parent = this;
    }

    public boolean isRoot() {
      return this == this.parent;
    }
  }

  private ArrayList<Node> sets;

  public UnionFind(int initSize) {
    sets = new ArrayList<>(initSize);
  }

  Node find(int x) {
    try {
      Node n = sets.get(x);
      if (n == null) return null;
      while(!n.isRoot()) {
        Node tmp = n;
        n = n.parent;
        tmp.parent = n.parent;
      }
      return n;
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  boolean makeSet(int x) {
    try {
      if (sets.get(x) != null) return false;
      sets.set(x, new Node(x));
      return true;
    } catch (IndexOutOfBoundsException e) {
      sets.ensureCapacity(x+1);
      return makeSet(x);
    }
  }

  Node union(int x, int y) {
    Node xr = find(x);
    Node yr = find(y);
    if (xr == null || yr == null) return null;
    yr.parent = xr;
    return xr;
  }
}
