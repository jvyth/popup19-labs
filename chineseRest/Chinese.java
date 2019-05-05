public class Chinese {
    // Returns [coefftoa, coefftob, gcd] 
    public static long[] eGCD(long a, long b){
        if(b == 0) {
            long[] arr = {1, 0, a};
            return arr;
        }

        long c = a/b; 
        long r = a%b;

        long[] arr = eGCD(b,r);
        long x1 = arr[1];

        arr[1] = arr[0] - c*arr[1]; 
        arr[0] = x1;

        return arr; 
    }

    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        //System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
        int c = kattio.getInt();
        long a, n, b, m, k, x;
        for(; c > 0; --c){
            a = kattio.getLong(); 
            n = kattio.getLong(); 
            b = kattio.getLong(); 
            m = kattio.getLong(); 
            long[] arr = eGCD(n,m); 
            x = n*b*arr[0] + m*a*arr[1];
            k = n*m; 
            x = Math.floorMod(x, k);
            kattio.printf("%d %d\n", x, k);
        }
        kattio.close();
    }
}
