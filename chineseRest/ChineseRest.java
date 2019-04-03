import java.util.*;

public class ChineseRest{

    // Returns [coefftoa, coefftob, gcd] 
    public static long[] eGCD(long a, long b){
        if(b == 0) {
            long[] arr = {1, 0, a};
            return arr;
        }
        
        if(a < b){
            long tmp = a;
            a = b;
            b = tmp;
        }

        long c = a/b; 
        long r = a%b;

        //if(r == 0){
          //  long[] arr = {0, 1, b};
           // return arr;
       //    }

        long[] arr = eGCD(b,r);
        long x1 = arr[1];

        arr[1] = arr[0] - c*arr[1]; 
        arr[0] = x1;

        return arr; 
    }

    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        //System.out.println(Arrays.toString(eGCD(Integer.parseInt(args[0]),Integer.parseInt(args[1]))));
            
        System.out.println(test);
        int c = kattio.getInt();
        long a, n, b, m;
        for(; c > 0; --c){
            a = kattio.getLong(); 
            n = kattio.getLong(); 
            b = kattio.getLong(); 
            m = kattio.getLong(); 
            long[] arr = eGCD(n,m); 
            long g = arr[2];
            long v,u,x,k;

            if(m > n){
                u = arr[0];
                v = arr[1];
            } else {
                u = arr[1];
                v = arr[0];
            }

            if(a%g == b%g){
                x = (a*u*m + b*v*n)/g;
                k = ((m*n)/g); 
                kattio.printf("%d %d\n", Math.floorMod(x, k), k);
            } else {
                kattio.println("no solution");
            }
        }
        kattio.close();
    } 
}
