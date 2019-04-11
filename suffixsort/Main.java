class Main {
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        Kattio kattio = new Kattio(System.in, System.out);
        String str;
        int n;
        while(kattio.ready()){
            str = kattio.getLine();
            SuffixArray sa = new SuffixArray(str);
            n = kattio.getInt();
            for (int i = 0; i < n; ++i) {
                kattio.print(sa.getSuffix(kattio.getInt()));
                kattio.print(" ");
            }
            kattio.println();
        }
        kattio.close();
    }
}
