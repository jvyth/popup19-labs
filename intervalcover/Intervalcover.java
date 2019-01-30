/*
   This file contains a solution for https://kth.kattis.com/problems/intervalcover
   Created during spring semester of 2019 for the course DD2458 at KTH
Authors: Jakob Vyth (vyth@kth.se) and Carl Nyströmer (carlnys@kth.se)
*/

import java.util.ArrayList;
import java.util.Collections;

class Intervalcover {
    public static ArrayList<Integer> solveCase(double L, double R, int n, ArrayList<Interval> intervals) {
        ArrayList<Integer> S = new ArrayList<>(n);
        double max_b = Double.NEGATIVE_INFINITY;
        int max_index = 0;
        boolean found_max;
        do {
            found_max = false;
            for (int i = max_index ; i < intervals.size();  i++) {
                Interval I = intervals.get(i);
                if(I.a > L){
                    break;
                }
                if (I.b >= max_b) {
                    max_b = I.b;
                    max_index = I.id;
                    found_max = true;
                }
            }
            if (!found_max) {
                return null;
            }
            S.add(max_index);
            L = max_b;
        } while (L < R);
        return S;
    }

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in);

        while (kattio.hasMoreTokens()) {
            double L = kattio.getDouble();
            double R = kattio.getDouble();
            int n = kattio.getInt();
            if(n < 0) {
                System.out.println("impossible");
                continue;
            }
            ArrayList<Interval> intervals = new ArrayList<>(n);
            for (int id = 0; id < n; ++id) {
                double a = kattio.getDouble();
                double b = kattio.getDouble();
                intervals.add(new Interval(a, b, id));
            }
            Collections.sort(intervals);

            ArrayList<Integer> res = solveCase(L, R, n, intervals);

            if (res == null) {
                System.out.println("impossible");
            } else {
                System.out.println(res.size());
                for (Integer i : res) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }

        }
    }
}
