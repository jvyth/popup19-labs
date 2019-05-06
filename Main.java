public class Main{
    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in, System.out); 
        int n = kattio.getInt();
        //int[] ans = {1,0,1,0,1,0,1,1,0,1,1,1,1};
        for(int i = 0; i < n; i++){
           double x1 = kattio.getDouble();
           double y1 = kattio.getDouble();
           double x2 = kattio.getDouble();
           double y2 = kattio.getDouble();
           double x3 = kattio.getDouble();
           double y3 = kattio.getDouble();
           double x4 = kattio.getDouble();
           double y4 = kattio.getDouble();
           LineSegment l1 = new LineSegment(new Point(x1,y1), new Point(x2,y2)); 
           LineSegment l2 = new LineSegment(new Point(x3,y3), new Point(x4,y4)); 
           Point[] intersection = LineSegment.intersects(l1,l2);
           if(intersection == null){
               System.out.print("none");
           } else if (intersection.length > 1) {
               for(Point p : intersection) {
                   System.out.print(p + " ");
               }
           } else {
               System.out.print(intersection[0]);
           }
         //  if(ans[i] == 1){
         //      System.out.println(", INTERSECT!");
         //  } else {
         //      System.out.println(", NONE!");
         //  }
           System.out.println();
        }
        kattio.close();
    }
}
