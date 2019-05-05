import java.util.*;

/*
 * The purpose of this class is to create a suffix array of a string.
 *
 * This is done using a "prefix doubling" algorithm.
 *
 * @author Carl Nyströmer
 * @author Jakob Vyth
 */
public class SuffixArray {

    private ArrayList<Suffix> sa;

    private class Suffix implements Comparable<Suffix> {
        int index; //which index in original text
        int r1; //which rank in first half of Suffix
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

    /*
     * Create the suffix array.
     *
     * @param str The string to construct the suffix array from.
     */
    public SuffixArray(String str) {
        int n = str.length();
        sa = new ArrayList<Suffix>(n);
        ArrayList<Suffix> suffix = new ArrayList<Suffix>(n);
        for (int i = 0; i < n-1; ++i) {
                // Add base case of suffixes, the rank is the ascii-code.
                Suffix s = new Suffix(i, str.charAt(i), str.charAt(i+1));
                suffix.add(s);
                sa.add(s);
        }
        // Last character is a special case, since it doens't have a next rank. (-1)
        Suffix last = new Suffix(n-1, str.charAt(n-1), -1);
        suffix.add(last);
        sa.add(last);

        // Sort on both first and second rank.
        Collections.sort(sa);

        // For each loop, double the amount of characters to rank
        for (int k = 4; k < 2*n; k*=2) {
            int rank = 0;
            int previousRank = sa.get(0).r1;
            sa.get(0).r1 = rank;

            // Compute r1 for next iteration
            for (int i = 1; i < n; ++i){
                Suffix s = sa.get(i);
                // If current suffix has same rank as previous suffix, they're in the same "group"
                if (s.r1 == previousRank && s.r2 == sa.get(i-1).r2){
                    s.r1 = rank;
                // Otherwise, assign it to a new "group"
                } else {
                    ++rank;
                    previousRank = s.r1; // Must save in order to compare to next suffix
                    s.r1 = rank;
               }
            }

            // Compute r2 for next iteration, here we utilize the fact that k/2 characters already have
            // been sorted.
            for(int i = 0; i < n; ++i) {
                // The index in the suffix-array for the suffix that already has been sorted.
                int r = i + k/2;
                Suffix s = suffix.get(i);
                if (r < n) {
                    s.r2 = suffix.get(r).r1;
                } else {
                    s.r2 = -1;
                }
            }

            Collections.sort(sa);
        }
    }

    public String toString(){
        return sa.toString();
    }

    /*
     * @param i Index in the suffix array
     * @return The i'th suffix in lexographical order.
     */
    public int getSuffix(int i) {
        return sa.get(i).index;
    }
}
