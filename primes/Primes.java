import java.util.*;

public class Primes{
    BitSet primes;
    int n;
    public Primes(int n){
        this.n = n;
        int rootN = (int) Math.sqrt(n);
        primes = new BitSet(n+1);
        int currPrime = 2;
        primes.set(0);
        primes.set(1);
        int previousPrime = 1;
        while(currPrime <= rootN){
            for(int mult = currPrime*(previousPrime+1); mult <= n; mult += currPrime){
                primes.set(mult);
            }
            previousPrime = currPrime;
            currPrime = primes.nextClearBit(currPrime+1);
        }
    }

    public boolean isPrime(int x){
        return !primes.get(x);
    }

    public int numPrimes(){
        return n-primes.cardinality()+1;
    }


    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out);
        int n = kattio.getInt();
        Primes p = new Primes(n);
        int q = kattio.getInt();
        kattio.println(p.numPrimes());
        for(; q > 0; --q){
            if(p.isPrime(kattio.getInt())){
                kattio.println(1);
            } else {
                kattio.println(0);
            }
        }
        kattio.close();
    }
}
