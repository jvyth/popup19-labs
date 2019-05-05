public class Main{
    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        int c = kattio.getInt();
        for(; c > 0; --c){
            long a = kattio.getLong();
            long n = kattio.getLong();
            long b = kattio.getLong();
            long m = kattio.getLong();
            long x = ChineseRemainder.solve(a,n,b,m);
            if(x >= 0){
                kattio.println(x + " " + n*m/(ChineseRemainder.extEuc(n,m)[2]));
            } else {
                kattio.println("no solution");
            }
        }
        kattio.close();
    } 
}
