/*
  This file contains a solution for https://kth.kattis.com/problems/unionfind
  Created during spring semester of 2019 for the course DD2458 at KTH

  @author Jakob Vyth (vyth@kth.se) 
  @author Carl Nyströmer (carlnys@kth.se)
*/
public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int N = io.getInt(); int Q = io.getInt();
        UnionFind uf = new UnionFind(N);
        String cmd; int a,b;
        for (;Q > 0; --Q) {
            cmd = io.getWord(); a = io.getInt(); b = io.getInt();
            if (cmd.equals("=")) {
                uf.union(a,b);
            } else {
                io.println(uf.same(a,b) ? "yes" : "no");
            }
        }
        io.close();
    }
}
