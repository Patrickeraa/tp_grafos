import java.util.Comparator;

public class Aresta implements Comparable<Aresta> {
    int de,para;
    float peso;
    public Aresta(int de,int para,float peso){
        this.de = de;
        this.para = para;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta o) {
        if(this.para > o.para)
            return 1;
        else if(this.para < o.para)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",this.de,this.para);
    }
}
