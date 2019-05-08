/*
 *  The purpose of this class is to represent rational numbers and 
 *  provide a way to do the fundamental operations addition, subtraction
 *  multiplication, and division. Each operations returns a new rational number,
 *  this way provides flexibility to perform several operations in a row
 *  over the same rational number.
 *
 *  @author Jakob Vyth (vyth@kth.se)
 *  @author Carl Nyströmer (carlnys@kth.se)
 */
public class Rational implements Comparable<Rational>{
    private long numer;
    private long denom;

    /*
     * Create a rational number.
     *
     * @param numer The numerator of the rational number. 
     * @param denom The denominator of the rational number. 
     */
    public Rational(long numer, long denom) {
        this.numer = numer; 
        this.denom = denom; 
    }

    public Rational(double d){
        Rational r = new Rational((long) (d*1e6),(long)1e6).reduced();
        this.numer = r.numer;
        this.denom = r.denom;
    }

    /*
     * Perform addition.
     * @param r The rational number to add to this one.
     * @return The result as a rational number. 
     */
    public Rational add(Rational r) {
        long lcm = lcm(this.denom, r.denom);
        long num1, num2;
        num1 = this.numer * (lcm/this.denom);  
        num2 = r.numer * (lcm/r.denom);

        return new Rational(num1 + num2, lcm).reduced();
    }

    /*
     * Perform subtraction.
     * @param r The rational number to subtract from this one.
     * @return The result as a rational number. 
     */
    public Rational sub(Rational r) {
        //find least common denominator = lcm
        long lcm = lcm(this.denom, r.denom);
        long num1, num2;
        num1 = this.numer * (lcm/this.denom);  
        num2 = r.numer * (lcm/r.denom);

        return new Rational(num1 - num2, lcm).reduced();
    }

    /*
     * Perform multiplication.
     * @param r The rational number to multiply with this one.
     * @return The result as a rational number. 
     */
    public Rational mul(Rational r) {
        // 3/5 * 2/3 = 6/15 = 2/5 
        return new Rational(this.numer*r.numer, this.denom*r.denom).reduced();
    }

    /*
     * Perform division.
     * @param r The rational number to divide with this one.
     * @return The result as a rational number. 
     */
    public Rational div(Rational r) {
        //find greatest common divider between n3 and d3
        return new Rational(this.numer*r.denom, this.denom*r.numer).reduced();
    }

    /*
     * @return A string representation of the rational number. 
     *     E.g "1 / 5" 
     */
    public String toString() {
        if (denom < 0) {
            return new StringBuilder().append(-numer).append(" / ").append(-denom).toString();
        }
        return new StringBuilder().append(numer).append(" / ").append(denom).toString();
    }

    /*
     * 
     * @return The reduced form of the rational number. 
     *     E.g 2/10 => 1/5 
     */
    public Rational reduced(){
        long gcd = gcd(this.numer, this.denom);
        return new Rational(this.numer/gcd, this.denom/gcd);
    }

    public Rational sqrt(){
        //System.out.println(toDouble());
        //System.out.println(Math.sqrt(toDouble()));
        //System.out.println(new Double(String.format("%.7g%n",Math.sqrt(toDouble()))));
        //return new Rational(new Double(String.format("%.6g%n",Math.sqrt(toDouble()))));    
        return new Rational(new Double(String.format("%.7g%n",Math.sqrt(toDouble())))).reduced();    
    }

    public double toDouble(){
        return ((double) numer) / ((double) denom);
    }

    public static double toDouble(Rational r){

        return ((double) r.numer) / ((double) r.denom);
    }

    public boolean isZero(){
        if(numer == 0){
            return true;
        } else {
            return false;
        }
    }

    public int compareTo(Rational other){
        Rational res = sub(other);
        if(res.numer == 0) {
            return 0;
        } else if(res.numer < 0 && res.denom >0){
            return -1;    
        } else if (res.denom < 0 && res.numer > 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public boolean inRange(long a, long b) {
        Rational x1 = new Rational(a, 1); 
        Rational x2 = new Rational(b, 1); 

        if(compareTo(x1) != -1){ 
            if(compareTo(x2) != 1){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private long lcm(long a, long b){
        return Math.abs(a*b)/gcd(a,b);
    }

    private long gcd(long a, long b) {
        if (a == 0) {
            return b;
        } else if (b == 0){
            return a;
        }
        long big = Math.abs(a);
        long small = Math.abs(b);  
        if (big < small) {
            long tmp = small;
            small = big;
            big = tmp;

        }
        long rest = big%small; 
        while (rest != 0) {
            big = small;
            small = rest;
            rest = big%small;
        }
        return small;
    }
}
