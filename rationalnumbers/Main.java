public class Main{
    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        int cases = kattio.getInt();       
        long n1, d1, n2, d2;
        String op;
        for(; cases > 0; --cases){
            n1 = kattio.getInt(); 
            d1 = kattio.getInt(); 
            op = kattio.getWord();
            n2 = kattio.getInt(); 
            d2 = kattio.getInt(); 

            // Rational.java
            if(op.equals("+")){
                kattio.println(new Rational(n1,d1).add(new Rational(n2,d2)).reduced());
            } else if(op.equals("-")){
                kattio.println(new Rational(n1,d1).sub(new Rational(n2,d2)).reduced());
            } else if(op.equals("*")){
                kattio.println(new Rational(n1,d1).mul(new Rational(n2,d2)).reduced());
            } else {
                kattio.println(new Rational(n1,d1).div(new Rational(n2,d2)).reduced());
            }
        }
        kattio.close();
    }
}
