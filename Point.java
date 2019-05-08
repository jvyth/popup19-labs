public class Point {
    public Rational x;
    public Rational y;
    public Point(double x, double y){
        this.x = new Rational(x);
        this.y = new Rational(y);
    }

    public Point(Rational x, Rational y){
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args){
        Rational r1 = new Rational(5,7);
        Rational r2 = new Rational(3,11);

        Rational r3 = new Rational(5,10);
        Rational r4 = new Rational(1,2);

        Rational r5 = new Rational(-3,10);
        Rational r6 = new Rational(3,10);

        Rational r7 = new Rational(-3,-10);
        Rational r8 = new Rational(-3,-10);

        System.out.println(r1.compareTo(r2));
        System.out.println(r3.compareTo(r4));
        System.out.println(r5.compareTo(r6));
        System.out.println(r7.compareTo(r8));
        System.out.println(r7 == r8);

        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(2, 1);

        Point p5 = new Point(2, 1);
    }

    public Rational dot(Point p2){
        //x1x2 + y1y2
        return x.mul(p2.x).add(y.mul(p2.y));
    }

    public static Rational cosAlpha(Point p1, Point p2){
        //x1x2 + y1y2 / ||p1|| ||p2|| = cos(a)
        return p1.dot(p2).div(p1.magnitude().mul(p2.magnitude()));
    }

    public static Rational sinAlpha(Point p1, Point p2){
        return (p1.x.mul(p2.y).sub(p2.x.mul(p1.y))).div(p1.magnitude().mul(p2.magnitude()));
    }

    public Rational cross(Point p2){
        return (x.mul(p2.y).sub(p2.x.mul(y)));
    }

    public Rational magnitude(){
        //||p|| 
        return x.mul(x).add((y.mul(y))).sqrt();
    }

    public Point add(Point p2){
        return new Point(x.add(p2.x), y.add(p2.y)); 
    }

    public Point sub(Point p2){
        return new Point(x.sub(p2.x), y.sub(p2.y)); 
    }

    public Point mul(Rational s) {
        return new Point(x.mul(s), y.mul(s));
    }

    @Override
    public String toString(){
        //return "(" + x.toDouble() + "," + y.toDouble() + ")";
        //String sx = String.format("%.2f", round(x.toDouble()));
        //String sy = String.format("%.2f", round(y.toDouble()));
        String sx = String.format("%.2f", x.toDouble());
        String sy = String.format("%.2f", y.toDouble());
        return sx + " " + sy;
    }

    public double round(double x){
        if(x > 0){
            return (double) ((int) (x * 100 + 0.5)) / 100;
        } else if (x < 0){
            return (double) ((int) (x * 100 - 0.5)) / 100;
        } else {
            return 0; 
        }
    }

    public boolean isEqual(Point p2){
        if(x.compareTo(p2.x) == 0  && y.compareTo(p2.y) == 0){
            return true;
        } else {
            return false;
        }
    }

   // public boolean isEqual(Point p2){
   //     if(x.toDouble() == p2.x.toDouble() && y.toDouble() == p2.y.toDouble()){
   //         System.out.println("yo");
   //         return true;
   //     } else {
   //         return false;
   //     }
   // }
}
