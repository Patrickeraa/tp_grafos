import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map;

public class Grafo {
    int numVertices;
    int prev[];
    Map<Integer, PriorityQueue<Aresta>> grafo;
    public Grafo(){
        this.grafo = new HashMap<>();
    }
    public void criaGrafo(){

        String property = System.getProperty("user.dir");
        File f = new File(property+"/resources/grafo.txt");
        try (Scanner s = new Scanner(f)) {
            numVertices = s.nextInt();
            int u,v;
            float p;
            while(s.hasNext()){
//                String[] split = s.nextLine().split(" ");
                u = Integer.parseInt(s.next());
                v = Integer.parseInt(s.next());
                p = Float.parseFloat(s.next());
                adicionaAresta(u,v,p);
                adicionaAresta(v,u,p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void adicionaAresta(int u,int v,float p){
        if(this.grafo.containsKey(u)){
            this.grafo.get(u).add(new Aresta(u,v,p));
        }else{
            this.grafo.put(u,new PriorityQueue<Aresta>(List.of(new Aresta(u, v, p))));
        }
    }
    public void imprimeGrafo(){
        for(Integer key: this.grafo.keySet()){
            PriorityQueue<Aresta> arestas = this.grafo.get(key);
            Iterator<Aresta> iterator = arestas.iterator();
            System.out.print(String.format("V%d:",key));
            while(iterator.hasNext()){
                Aresta a = iterator.next();
                System.out.print(String.format(" V%d (%.1f)",a.para,a.peso));
            }
            System.out.println();
        }
    }
    public void ordemDoGrafo(){
        System.out.println(String.format("A ordem do grafo é: %d",this.numVertices));
    }
}
