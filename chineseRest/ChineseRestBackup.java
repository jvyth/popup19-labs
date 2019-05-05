import java.util.*;
import java.util.Random;

public class ChineseRest{

    // Returns [coefftoa, coefftob, gcd] 
    public static long[] eGCD(long a, long b){
        if(b == 0) {
            long[] arr = {1, 0, a};
            return arr;
        }

        //        if(a < b){
        //            long tmp = a;
        //            a = b;
        //            b = tmp;
        //        }
        long c = a/b; 
        long r = a%b;
        long[] arr = eGCD(b,r); 
        long x1 = arr[1];
        arr[1] = arr[0] - c*arr[1];
        arr[0] = x1;
        return arr; 
    }

    public static void main(String[] args){
        //Kattio kattio = new Kattio(System.in, System.out); 
        //System.out.println(Arrays.toString(eGCD(Integer.parseInt(args[0]),Integer.parseInt(args[1]))));
      //  int c = kattio.getInt();
      //  long a, n, b, m;
      //  for(; c > 0; --c){
      //      a = kattio.getLong(); 
      //      n = kattio.getLong(); 
      //      b = kattio.getLong(); 
      //      m = kattio.getLong(); 
      //      long[] arr = eGCD(n,m); 
      //      long g = arr[2];
      //      long v = arr[0];
      //      long u = arr[1];          
      //      if(a%g == b%g){
      //          long x = a*u*(m/g) + b*v*(n/g);
      //          long k = m*(n/g); 
      //          kattio.printf("%d %d\n", Math.floorMod(x, k), k);
      //      } else {
      //          kattio.println("no solution");
      //      }
      //  }
      //  kattio.close();
        boolean noError = true;
        Random rand = new Random();
        while(noError){
            long n = (long) (rand.nextDouble() * (Long.parseLong(args[0])));
            long m = (long) (rand.nextDouble() * (Long.parseLong(args[0])));
           // long n = (long) (rand.nextDouble() * (1000000000L));
           // long m = (long) (rand.nextDouble() * (1000000000L));
            long a = (long) (rand.nextDouble() * (n-1));
            long b = (long) (rand.nextDouble() * (m-1));

            long[] arr = eGCD(n,m); 
            long g = arr[2];
            long v = arr[0];
            long u = arr[1];          

            

            try { 
            if(a%g == b%g){
                long x = a*u*(m/g) + b*v*(n/g);
                long k = m*(n/g); 
                x = Math.floorMod(x,k);
                if(x % n != a && x % m != b) {
                    System.out.printf("a = %d, b = %d, n = %d, m = %d\n", a,b,n,m);
                    System.out.printf("x = %d, k = %d, g = %d\n",x,k,g);
                    System.out.printf("v = %d, u = %d\n",v,u);
                    System.out.printf("%d*%d*%d/%d\n",a,u,m,g);
                    noError = false;
                }
            } 
            } catch(Exception e) {
                System.out.printf("a = %d, b = %d, n = %d, m = %d\n", a,b,n,m);
                System.out.printf("g = %d\n",g);
            }
        }
    } 
}        
