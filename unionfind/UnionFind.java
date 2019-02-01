/*
  This file contains a solution for https://kth.kattis.com/problems/unionfind
  Created during spring semester of 2019 for the course DD2458 at KTH
  Authors: Jakob Vyth (vyth@kth.se) and Carl Nyströmer (carlnys@kth.se)
*/

import java.util.Arrays;

public class UnionFind {
  private int[] parents;
  private int[] size;

  public UnionFind(int n) {
    parents = new int[n];
    size = new int[n];
    for (int i = 0; i < n ; ++i) {
      parents[i] = i;
      size[i] = 1;
    }
  }

  public boolean same(int x, int y) {
    return find(x) == find(y);
  }

  private int find(int x) {
    if (parents[x] != x) parents[x] = find(parents[x]);
    return parents[x];
  }

  // private int find(int x) {
  //   while (parents[x] != x) x = parents[x];
  //   return x;
  // }

  // private int find(int x) {
  //
  //   while (parents[x] != x) {
  //     int tmp = x;
  //     x = parents[x];
  //     parents[tmp] = parents[x];
  //   }
  //   return x;
  // }

  int union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);
    if (xRoot == yRoot) return xRoot;
    if (size[xRoot] > size[yRoot]) {
      parents[yRoot] = xRoot;
      size[xRoot] += size[yRoot];
      return xRoot;
    } else {
      parents[xRoot] = yRoot;
      size[yRoot] += size[xRoot];
      return yRoot;
    }
  }

  public String toString() {
    return Arrays.toString(parents);
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int N = io.getInt(); int Q = io.getInt();
    UnionFind uf = new UnionFind(N);
    String cmd; int a,b;
    for (;Q > 0; --Q) {
      cmd = io.getWord(); a = io.getInt(); b = io.getInt();
      if (cmd.equals("+")) {
        uf.union(a,b);
      } else {
        io.println(uf.same(a,b) ? "yes" : "no");
      }
    }
    io.close();
  }
}
