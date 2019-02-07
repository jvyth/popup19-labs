public class Polymul{
    public static int[] polymul(int[] p, int[] q){
        //If p.size() = n = 5, we have poly of degree 4
        //pl.size() = n/2 always
        //pr.size() = n/2 + 1 if n is odd
        //otherwise pr.size() = n/2


        int n = p.size();

        //if n = 5 
        //plb = 0
        //ple = 1
        //
        //prb = 2
        //pre = 4
        //
        //if n = 4 
        //plb = 0
        //ple = 1
        //
        //prb = 2
        //pre = 3

        int plb = 0; 
        int ple = (n/2)-1; 
        int prb = n/2;
        int pre = n-1; 

        int qlb = 0; 
        int qle = (n/2)-1; 
        int qrb = n/2;
        int qre = n-1; 

         
    } 

    public static polymulhelp(int[] p, int[] q, int pb, int pe, int qb, int qe){
        if(pb == pe && qb == qe){
            return p[pb] * q[qb];
        }
    }
}
