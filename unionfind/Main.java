public class Main {
    public static void main(String[] args){
        Kattio kattio = new Kattio(System.in);
        int n = kattio.getInt();
        int q = kattio.getInt();

        UnionFind uf = new UnionFind(n);
        String command;
        int x;
        int y;

        for(int i = 0; i < q; i++){
            command = kattio.getWord();
            x = kattio.getInt();
            y = kattio.getInt();
            if (command.equals("=")){ 
                uf.union(x,y);
            } else if (command.equals("?")){
                if (uf.find(x) == uf.find(y)){
                    kattio.println("yes");
                } else {
                   kattio.println("no");
                }
            }
        }
        kattio.close();
    }
}
