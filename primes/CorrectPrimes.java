import java.util.*;

public class CorrectPrimes{
    //HashSet<Integer> primes;
    BitSet primes;
    public CorrectPrimes(int n){
        //ArrayList<Integer> rootNPrimes = new ArrayList<Integer>();

        //primes = new HashSet<Integer>((int) (n/(2*Math.log(n))));
        int rootN = (int) Math.sqrt(n) + 1;
        primes = new BitSet(n+rootN);
        int[] segment = new int[rootN];
        int currPrime = 2;

        while(currPrime < rootN){
            //rootNPrimes.add(currPrime);
            primes.set(currPrime);
            for(int mult = currPrime*2; mult < rootN; mult += currPrime){
                segment[mult] = 1;
            }

            while(!(++currPrime >= rootN) && segment[currPrime] == 1){
                //System.out.println(currPrime);
            }
        }

        int lower = 0;
        int higher = rootN - 1;
        while(higher < n){
            lower = higher + 1;
            higher = higher + rootN;
            segment = new int[rootN];
            //System.out.println(primes.toString());
            for(int p = primes.nextSetBit(0); p < rootN && p != -1; p = primes.nextSetBit(p+1)){
                //System.out.println(p);
                int firstMultP = lower/p * p == lower ? lower : lower/p * p + p;
                for(int mult = firstMultP; mult <= higher; mult += p){
                    segment[mult%rootN] = 1;
                }
            }

            for(int i = 0; i < rootN; ++i){
                if(segment[i] == 0 && i+lower <= n){
                    primes.set(i+lower);
                }
            }
        }
    }

    public boolean isPrime(int x){
        return primes.get(x);
    }

    public int numPrimes(){
        return primes.cardinality();
    }


   // public static void main(String[] args){
   //     Kattio kattio = new Kattio(System.in, System.out);
   //     int n = kattio.getInt();
   //     CorrectPrimes p = new CorrectPrimes(n);
   //     int q = kattio.getInt();
   //     kattio.println(p.numPrimes());
   //     for(; q > 0; --q){
   //         if(p.isPrime(kattio.getInt())){
   //             kattio.println(1);
   //         } else {
   //             kattio.println(0);
   //         }
   //     }
   //     kattio.close();
   // }
}

