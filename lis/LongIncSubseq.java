import java.util.Arrays;

public class LongIncSubseq{
        
    public static int[] lis(int[] seq){
        int[] last = new int[seq.length + 1];
        int[] last_indices = new int[seq.length];
        Arrays.fill(last, Integer.MAX_VALUE);
        Arrays.fill(last_indices, -1);
        last[0] = Integer.MIN_VALUE;

        int l;
        for(int i = 0; i < seq.length; i++){
            l = Arrays.binarySearch(last, seq[i]); 
            if(l < 0){
                l = -(l + 1);
                //min(last[l], seq[i])
                if(last[l] > seq[i]){
                    last[l] = seq[i];
                    last_indices[l-1] = i;
                }
            } 
        }
        
        int subSeqLen = Arrays.asList(last_indices).indexOf(-1);
        if(subSeqLen < 0){
            return last_indices;
        }
        return Arrays.copyOfRange(last_indices,0,subSeqLen);
    }

    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in);
        while(kattio.hasMoreTokens()){
            int n = kattio.getInt();
            int[] seq = new int[n];
            for(int i = 0; i < n; i++){
                seq[i] = kattio.getInt();
            }
            int[] lis = lis(seq);
            System.out.println(lis.length);
            for(int i: lis){
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}
