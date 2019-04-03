import java.util.*;
import java.time.*;

class MainSingle {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);
        //Instant start = Instant.now();
        while (kattio.ready()) {
            String pattern = kattio.getLine();
            String text = kattio.getLine();
            ArrayList<Integer> ret =  StringMatch.find(pattern, text);
            for (int i : ret) {
                kattio.print(i + " ");
            }
            kattio.println();
        }
  //      Instant end = Instant.now();
  //      Duration interval = Duration.between(start, end);
        //kattio.println("Time: " + interval.toMillis());
        kattio.close();
    }
}
