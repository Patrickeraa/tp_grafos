public class Vertice implements Comparable<Vertice> {
    int n;
    int grau; 
    public Vertice(int n,int grau) {
        this.n = n;
        this.grau = grau;
    }

    @Override
    public int compareTo(Vertice o) {
        if(this.grau >= o.grau)
            return -1;
        else if(this.grau < o.grau)
            return 0;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("V: %d, Grau: %d",this.n,this.grau);
    }

}
