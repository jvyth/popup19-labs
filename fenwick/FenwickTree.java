/*
  This file contains a solution for https://kth.kattis.com/problems/fenwick
  Created during spring semester of 2019 for the course DD2458 at KTH
  Authors: Jakob Vyth (vyth@kth.se) and Carl Nyströmer (carlnys@kth.se)
*/

class FenwickTree {
  private long[] sums;
  private int N;

  public FenwickTree(int n) {
    //1-indexed
    this.N = n+1;
    this.sums = new long[n+1];
  }

  public void add(int i, long d) {
    for(; i < N; i += i&(-i)) {
      sums[i] += d;
    }
  }

  public long sum(int i) {
    long sum = 0;
    for(; i > 0; i -= i&(-i)){
      sum += sums[i];
    }
    return sum;
  }

  public static void main(String[] args) {
    Kattio io = new Kattio(System.in, System.out);
    int N = io.getInt(); int Q = io.getInt();
    FenwickTree ft = new FenwickTree(N);
    String op;
    for (; Q > 0; --Q) {
      //System.err.println(Q);
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
