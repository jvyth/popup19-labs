public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(2, 1);
        Point p5 = new Point(2, 1);
    }

    public double dot(Point p2){
        return x * p2.x + y * p2.y;
    }

    public double cross(Point p2){
        return x * p2.y - p2.x * y;
    }

    public Point add(Point p2){
        return new Point(x + p2.x, y + p2.y); 
    }

    public Point sub(Point p2){
        return new Point(x - p2.x, y - p2.y); 
    }

    public Point mul(double s) {
        return new Point(x * s, y * s);
    }

    @Override
    public String toString(){
        //return "(" + x.toDouble() + "," + y.toDouble() + ")";
        String sx = String.format("%.2f", x);
        String sy = String.format("%.2f", y);
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
        if(Math.abs(x - p2.x) < 1e-8 && Math.abs(y - p2.y) < 1e-8){
            return true;
        } else {
            return false;
        }
    }
}
