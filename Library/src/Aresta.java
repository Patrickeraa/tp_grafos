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

    @Override
    public boolean equals(Object obj) {
        Aresta aresta = (Aresta) obj;
        return aresta.de == this.para && aresta.para == this.de || aresta.para == this.de && aresta.de == this.para;
    }

    public float comparadorArvore(Aresta compareAresta)
    {
        return (this.peso - compareAresta.peso);
    }
}
