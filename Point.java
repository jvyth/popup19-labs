public class Point{
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
        return "(" + x.toDouble() + "," + y.toDouble() + ")";
    }
}
