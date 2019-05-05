public class LineSegment{
    public Point p1;
    public Point p2; 
    public LineSegment(Point a, Point b){
        this.p1 = a;
        this.p2 = b;
    }

    public static void intersects(LineSegment l1, LineSegment l2){
        Point p1 = l1.p1; 
        Point p2 = l1.p2; 
        Point q1 = l2.p1;
        Point q2 = l2.p2;
        Rational numer = q1.sub(p1).cross(q2.sub(q1));
        Rational denom = p2.sub(p1).cross(q2.sub(q1));
        if(denom.isZero()){
            return false;
        }
        Rational s = numer.div(denom);
        //Rational t = z.x.div((q2.sub(q1).x)); 
        Point r = p1.add((p2.sub(p1).mul(s)));
        System.out.println(r);
    }

   public static void main(String[] args){
       LineSegment l1 = new LineSegment(new Point(-5,0), new Point(5,0)); 
       LineSegment l2 = new LineSegment(new Point(0,-5), new Point(0,5)); 
       LineSegment.intersects(l1,l2);
   } 
}
