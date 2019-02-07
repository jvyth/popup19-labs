import java.util.ArrayList;

/*
 *  This file contains a solution for https://kth.kattis.com/problems/intervalcover
 *  Created during spring semester of 2019 for the course DD2458 at KTH
 *
 *  @author Jakob Vyth (vyth@kth.se)
 *  @author Carl Nyströmer (carlnys@kth.se)
 */
public class Main {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        while (kattio.hasMoreTokens()) {
            double L = kattio.getDouble();
            double R = kattio.getDouble();
            int n = kattio.getInt();
            if(n < 0) {
                kattio.println("impossible");
                continue;
            }
            ArrayList<Interval> intervals = new ArrayList<>(n);
            for (int id = 0; id < n; ++id) {
                double a = kattio.getDouble();
                double b = kattio.getDouble();
                intervals.add(new Interval(a, b, id));
            }

            ArrayList<Integer> res = Intervalcover.cover(L, R, intervals);

            if (res == null) {
                kattio.println("impossible");
            } else {
                kattio.println(res.size());
                for (Integer i : res) {
                    kattio.print(i + " ");
                }
                kattio.println();
            }

        }
        kattio.close();
    }
}
