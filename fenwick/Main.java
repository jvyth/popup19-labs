/*
  This file contains a solution for https://kth.kattis.com/problems/fenwick
  Created during spring semester of 2019 for the course DD2458 at KTH

  @author Jakob Vyth (vyth@kth.se) 
  @author Carl Nyströmer (carlnys@kth.se)
*/
public class Main{
  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int N = io.getInt(); int Q = io.getInt();
    FenwickTree ft = new FenwickTree(N);
    String op;
    for (; Q > 0; --Q) {
      op = io.getWord();
      if (op.equals("?")) {
        io.println(ft.sum(io.getInt()));
      } else {
        ft.add(io.getInt()+1, io.getLong());
      }
    }
    io.close();
  }
}
