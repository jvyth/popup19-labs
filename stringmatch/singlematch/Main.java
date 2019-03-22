import java.util.*;

class Main {
    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

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
        kattio.close();
    }
}
