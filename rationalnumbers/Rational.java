/*
   This file contains a solution for https://kth.kattis.com/problems/rationalarithmetic
   Created during spring semester of 2019 for the course DD2458 at KTH
   Authors: Jakob Vyth (vyth@kth.se) and Carl longyströmer (carlnys@kth.se)
*/

public class Rational{
    //denominators and numerators
    private long numer;
    private long denom;

    /*
     * Generic class operating on rational numbers
     * @param <long> restricts input to be a number
     */
    public Rational(long numer, long denom){
        this.numer = numer; 
        this.denom = denom; 
    }

    public Rational add(Rational r){
        //find least common multiple = lcm
        long lcm = lcm(this.denom, r.denom);
        long num1, num2;
        num1 = this.numer * (lcm/this.denom);  
        num2 = r.numer * (lcm/r.denom);

        return new Rational(num1 + num2, lcm);
    }

    public Rational sub(Rational r){
        //find least common denominator = lcm
        long lcm = lcm(this.denom, r.denom);
        long num1, num2;
        num1 = this.numer * (lcm/this.denom);  
        num2 = r.numer * (lcm/r.denom);

        return new Rational(num1 - num2, lcm);
    }

    public Rational mul(Rational r){
        // 3/5 * 2/3 = 6/15 = 2/5 
        return new Rational(this.numer*r.numer, this.denom*r.denom);
    }

    public Rational div(Rational r){
        //find greatest common divider between n3 and d3
        return new Rational(this.numer*r.denom, this.denom*r.numer);
    }

    public long gcd(long a, long b){
        if(a == 0){
            return b;
        } else if (b == 0){
            return a;
        }
        long big = Math.abs(a);
        long small = Math.abs(b);  
        if(big < small){
            long tmp = small;
            small = big;
            big = tmp;

        }
        long rest = big%small; 
        while (rest != 0) {
            //System.out.prlongln(big + " = x * " + small + " + " + rest);
            big = small;
            small = rest;
            rest = big%small;
        }
        return small;
    }

    public String toString(){
        if(denom < 0){
            return new StringBuilder().append(-numer).append(" / ").append(-denom).toString();
        }
        return new StringBuilder().append(numer).append(" / ").append(denom).toString();
    }

    public long lcm(long a, long b){
        return Math.abs(a*b)/gcd(a,b);
    }

    public Rational reduced(){
        long gcd = gcd(this.numer, this.denom);
        return new Rational(this.numer/gcd, this.denom/gcd);
    }

    public static void main(String[] args){
       Rational r1 = new Rational(1,5);
       Rational r2 = new Rational(2,10);
       Rational r3 = new Rational(370368,3000);
       Rational r4 = new Rational(3703696968,30000000);
       System.out.println(r3.reduced());
       System.out.println(r4.reduced());
       System.out.println(r1.add(r2).reduced());
       System.out.println(r1.mul(r2).reduced());
       System.out.println(r1.div(r2).reduced());
       System.out.println(r2.reduced());
       //System.out.prlongln( r1.lcm(150, 350));
    } 
}
