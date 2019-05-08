public class LineSegment{
    public Point p1;
    public Point p2; 

    public LineSegment(Point a, Point b){
        this.p1 = a;
        this.p2 = b;
    }

    public static Point[] intersects(LineSegment l1, LineSegment l2){
        Point p1 = l1.p1; 
        Point p2 = l1.p2; 
        Point q1 = l2.p1;
        Point q2 = l2.p2;

        Rational numer;
        Rational denom;

        //Edge case - if both segments actually are points
        if(p1.isEqual(p2) && q1.isEqual(q2)){
            //If they are the same point, they intersect.
            if(p1.isEqual(q1)){
                return new Point[]{p1};
            } else {
                return null;
            }
        } 

        //Edge case 2 - if one of the segments actually is a point
        if(p1.isEqual(p2)){
            //if l1 is a point, check if point lies on l2 (between q1-q2)
            if(q2.sub(q1).cross(q1.sub(p1)).isZero()){
                numer = p1.sub(q1).dot(q2.sub(q1));
                denom = q2.sub(q1).dot(q2.sub(q1));
                Rational a1 = numer.div(denom);
                if(a1.inRange(0,1)){
                    return new Point[]{p1};
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else if(q1.isEqual(q2)) {
            if(p2.sub(p1).cross(p1.sub(q1)).isZero()){
                numer = q1.sub(p1).dot(p2.sub(p1));
                denom = p2.sub(p1).dot(p2.sub(p1));
                Rational a1 = numer.div(denom);
                if(a1.inRange(0,1)){
                    return new Point[]{q1};
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        numer = q1.sub(p1).cross(q2.sub(q1));
        denom = p2.sub(p1).cross(q2.sub(q1));

        //parallel
        if(denom.isZero()){
            //collinear
            if(p2.sub(p1).cross(q1.sub(p1)).isZero()){
                numer = q1.sub(p1).dot(p2.sub(p1));
                denom = p2.sub(p1).dot(p2.sub(p1));
                Rational a1 = numer.div(denom);

                numer = q2.sub(p1).dot(p2.sub(p1));
                denom = p2.sub(p1).dot(p2.sub(p1));
                Rational a2 = numer.div(denom);

                Point min; 
                Point max;
                if(a1.compareTo(a2) == -1){
                    if(a2.compareTo(new Rational(1,1)) == 1){
                        a2 = new Rational(1,1);
                    } 

                    if(a1.compareTo(new Rational(0,1)) == -1){
                        a1 = new Rational(0,1);
                    }
                } else {
                    if(a1.compareTo(new Rational(1,1)) == 1) {
                        a1 = new Rational(1,1);
                    } 

                    if(a2.compareTo(new Rational(0,1)) == -1){
                        a2 = new Rational(0,1);
                    }
                }

                min = p1.add(p2.sub(p1).mul(a1));
                max = p1.add(p2.sub(p1).mul(a2));

                if(min.x.compareTo(max.x) == 0){
                    if(min.y.compareTo(max.y) == -1){
                        return new Point[]{min,max};
                    } else {
                        return new Point[]{max,min};
                    }
                } else if (min.x.compareTo(max.x) == -1) {
                        return new Point[]{min,max};
                } else {
                        return new Point[]{max,min};
                }
            } else {
                return null;
            }
        }

        Rational s = numer.div(denom);
        numer = p1.sub(q1).cross(p2.sub(p1));
        denom = q2.sub(q1).cross(p2.sub(p1));
        Rational t = numer.div(denom);
        Point intersection = p1.add((p2.sub(p1).mul(s)));
        //Point r1 = q1.add((q2.sub(q1).mul(t)));

        if(s.inRange(0,1) && t.inRange(0,1)){
            return new Point[]{intersection};
        } else {
            return null;
        }
    }

   public static void main(String[] args){
       LineSegment l1 = new LineSegment(new Point(-5,0), new Point(5,0)); 
       LineSegment l2 = new LineSegment(new Point(10,-5), new Point(15,5)); 
       LineSegment.intersects(l1,l2);
   } 
}
