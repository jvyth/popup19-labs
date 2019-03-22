import java.util.*;
import java.time.*;

class Main {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        Instant start = Instant.now();
        while (kattio.ready()) {
            String[] patterns = new String[1];
            patterns[0] = kattio.getLine();
            String text = kattio.getLine();
            ArrayList<ArrayList<Integer>> ret =  StringMatch.find(patterns, text);
            for (ArrayList<Integer> a : ret) {
                for (int i : a) {
                    kattio.print(i + " ");
                }
                kattio.println();
            }
        }
        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        kattio.println("Time: " + interval.toMillis());
        kattio.close();
    }
}
