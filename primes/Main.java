public class Main{
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
