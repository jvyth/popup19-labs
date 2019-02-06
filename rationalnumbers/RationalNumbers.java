/*
   This file contains a solution for https://kth.kattis.com/problems/rationalarithmetic
   Created during spring semester of 2019 for the course DD2458 at KTH
Authors: Jakob Vyth (vyth@kth.se) and Carl Nyströmer (carlnys@kth.se)
*/

public class Rational<N extends Number>{
    //denominators and numerators
    private N numer;
    private N denom;

    /*
     * Generic class operating on rational numbers
     * @param <N> restricts input to be a number
     */
    public Rational(N numer, N denom){
        this.numer = numer; 
        this.denom = denom; 
    }

    public Rational<N> add(Rational<N> r){
        //find least common multiple = lcm
        this.numer *=  (lcm/this.denom);  
        r.numer *= (lcm/r.denom);

        return new Rational(n1 + n2, lcm);
    }

    public Rational<N> sub(Rational<N> r){
        //find least common denominator = lcm
        this.numer *= (lcm/this.denom);  
        r.numer *= (lcm/r.denom);

        return new Rational(this.numer - r.numer, lcm);
    }

    public Rational<N> mul(Rational<N> r){
        // 3/5 * 2/3 = 6/15 = 2/5 
        return new Rational(this.numer*r.numer, this.denom*r.denom);
    }

    public Rational<N> div(Rational<N> r){
        //find greatest common divider between n3 and d3
        return new Rational(this.numer*r.denom, this.denom*r.numer);
    }

    public N gcd(N a, N b){
        N big, small;  
        if(a > b){
            big = a;
            small = b;
        } else {
            big = b;
            small = a; 
        }
        N rest = big%small; 
        while (rest != 0) {
            System.out.println(big + " = x * " + small + " + " + rest);
            big = small;
            small = rest;
            rest = big%small;
        }
        return small;
    }

    public static void main(String[] args){
       Rational<Integer> r1 = new Rational(1,5);
       System.out.println(r1.gcd(2735,537));
       System.out.println(r1.gcd(6,3));
    } 
}
