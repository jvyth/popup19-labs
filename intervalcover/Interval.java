/*
  Created during spring semester of 2019 for the course DD2458 at KTH
  Authors: Jakob Vyth (vyth@kth.se) and Carl Nyströmer (carlnys@kth.se)
*/
class Interval implements Comparable<Interval> {
  public final int id;
  public final double a;
  public final double b;

  public Interval(double a, double b, int id) {
    this.a = a;
    this.b = b;
    this.id = id;
  }

  @Override
  public int compareTo(Interval x) {
    if (this.a == x.a) return 0;
    if (this.a < x.a) return -1;
    return 1;
  }

  public String toString() {
    return "[" + a + "," + b + "]";
  }
}
