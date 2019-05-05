import java.util.*;
import java.util.Random;
import java.math.*;

/*
 * The purpose of this class is to solve the general chinese remainder theorem
 * i.e find x for
 *      x = a (mod n)
 *      x = b (mod m)
 *
 * @author Carl Nyströmer
 * @author Jakob Vyth
 */
public class ChineseRemainder{

    /*
     * Does the extended euclidean algorithm for a and b.
     *
     * @param a Some long value
     * @param b Some long value
     * @return The algorithm will solve the equation ax + by = gcd(a,b)
     *         and return [x, y, gcd(a,b)]
     */
    public static long[] extEuc(long a, long b){
        //Forward
        if(b == 0) {
            long[] arr = {1, 0, a}; // a = rest (GCD)
            System.out.printf("a[0]: %d, a[1]: %d, a[2]: %d\n", arr[0],arr[1],arr[2]);
            return arr;
        }
        long c = a/b;
        long r = a%b;
        System.out.printf("a: %d, b: %d, c: %d\n", a, b, c);
        long[] arr = extEuc(b,r);
        //Backwards
        long x1 = arr[1];
        arr[1] = arr[0] - c*arr[1];
        arr[0] = x1;
        System.out.printf("a[0]: %d, a[1]: %d, a[2]: %d\n", arr[0],arr[1],arr[2]);
        return arr;
    }

    /*
     * Returns the solution to the equation
     *      x = a (mod n)
     *      x = b (mod m)
     *
     * @return Returns x in the above algorithm.
     */
    public static long solve(long a, long n, long b, long m){
        long[] arr = extEuc(n,m);                  //um + vn = gcd(m,n) -> um = g (mod n) -> u/g * m (mod n) = 1
        BigInteger g = BigInteger.valueOf(arr[2]); //g = GCD(a,b)
        BigInteger v = BigInteger.valueOf(arr[0]);
        BigInteger u = BigInteger.valueOf(arr[1]);
        BigInteger nb = BigInteger.valueOf(n);
        BigInteger mb = BigInteger.valueOf(m);
        BigInteger ab = BigInteger.valueOf(a);
        BigInteger bb = BigInteger.valueOf(b);
        if(ab.mod(g).equals(bb.mod(g))){
            // x = (a*u*m/g) + (b*v*n/g)
            BigInteger x = ab.multiply(u).multiply(mb).divide(g).add(bb.multiply(v).multiply(nb).divide(g));
            // k = (m*n/g) = lcm(m,n)
            BigInteger k = mb.multiply(nb).divide(g);
            x = x.mod(k);
            return x.longValue();
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
      ChineseRemainder.extEuc(888,54);
    }
}
