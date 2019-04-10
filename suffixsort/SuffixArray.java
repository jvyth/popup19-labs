import java.util.*;

public class SuffixArray {
    
    private Suffix[] sa; 

    private class Suffix implements Comparable<Suffix> {
        int index; 
        int r1;
        int r2; 

        public Suffix(int index, int r1, int r2) {
            this.index = index;
            this.r1 = r1;
            this.r2 = r2;
        }

        public int compareTo(Suffix other) {
            if(r1 == other.r1){
                return r2 - other.r2; 
            } else {
                return r1 - other.r1;
            }
        }

        public String toString(){
            return "(" + index + "," + r1 + "," + r2 + ")";
        }
    }

    public SuffixArray(String str) {
        int n = str.length();
        sa = new Suffix[n];
        Suffix[] suffix = new Suffix[n];
        for (int i = 0; i < n-1; ++i) {
                sa[i] = new Suffix(i, str.charAt(i), str.charAt(i+1));
                suffix[i] = sa[i];
        }
        sa[n-1] = new Suffix(n-1, str.charAt(n-1), -1);
        suffix[n-1] = sa[n-1];

        Arrays.sort(sa);
        //System.out.println(Arrays.toString(sa));
        
        for (int k = 4; k < 2*n; k*=2) {
            int rank = 0;
            int previousRank = sa[0].r1;
            sa[0].r1 = rank;
            
            for (int i = 1; i < n; ++i){
                if (sa[i].r1 == previousRank && sa[i].r2 == sa[i-1].r2){
                    sa[i].r1 = rank;
                } else {
                    ++rank;
                    previousRank = sa[i].r1;
                    sa[i].r1 = rank;
               }
            }
            

            for(int i = 0; i < n; ++i) {
                int r = i + k/2; 
                if (r < n) {
                    suffix[i].r2 = suffix[r].r1;
                } else {
                    suffix[i].r2 = -1;
                }
            }

            Arrays.sort(sa);
            //System.out.println(Arrays.toString(sa));
        }
    }

    public String toString(){
        return Arrays.toString(sa);
    }

    public int getSuffix(int i) {
        return sa[i].index; 
    }

    public static void main(String[] args) { 
        final long startTime = System.currentTimeMillis();
        Kattio kattio = new Kattio(System.in, System.out);
        String str; 
        int n; 
        while(kattio.ready()){
            str = kattio.getLine();
            SuffixArray sa = new SuffixArray(str);
            n = kattio.getInt();
            for (int i = 0; i < n; ++i) {
                kattio.print(sa.getSuffix(kattio.getInt()));
                kattio.print(" ");
                //System.out.printf("%d ", sa.getSuffix(kattio.getInt()));
            }
            kattio.println();
        }
        kattio.println((System.currentTimeMillis() - startTime)/1000);
        kattio.println((System.currentTimeMillis() - startTime));
        kattio.close();
    }
}
