import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Grafo {
    private int numArestas;
    private Integer VISITADO = 0;

    int prev[];
    Map<Integer, PriorityQueue<Aresta>> grafo;

    private int raio = Integer.MAX_VALUE;
    private int diametro = 0;

    public Grafo(){
        this.grafo = new HashMap<>();
    }

    public void criaGrafo(){

        String property = System.getProperty("user.dir");
        // File f = new File(property+"/Library/resources/grafo.txt");
        File f = new File(property+"/resources/grafo.txt");
        try (Scanner s = new Scanner(f)) {
            s.nextInt();
            int u,v;
            float p;
            while(s.hasNext()){
                this.numArestas++;
                u = Integer.parseInt(s.next());
                v = Integer.parseInt(s.next());
                p = Float.parseFloat(s.next());
                adicionaAresta(u,v,p);
                adicionaAresta(v,u,p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.calculaRadioEDiametro();
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
        System.out.println(String.format("A ordem do grafo é: %d",this.grafo.size()));
    }

    public void tamanhoDoGrafo(){
        System.out.println(String.format("O tamanho do grafo é: %d",this.numArestas));
    }

    public void vizinhosDe(int u){
        PriorityQueue<Aresta> arestas = this.grafo.get(u);
        Iterator<Aresta> iterator = arestas.iterator();
        System.out.print(String.format("V%d:",u));
        while(iterator.hasNext()){
            Aresta next = iterator.next();
            System.out.print(String.format(" V%d (%.1f)",next.para,next.peso));
        }
        System.out.println();
    }

    public void grauDe(int u){
        int grau = this.grafo.get(u).size();
        System.out.println(String.format("O grau do vértice %d é %d",u,grau));
    }

    public void sequenciaDeGraus(){
        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        };
        PriorityQueue<Integer> sequencia = new PriorityQueue<>(comparator);
        ;
        for(int i=1; i <= 5; i++){
            sequencia.add(this.grafo.get(i).size());
        }
        System.out.println(String.format("A sequencia de graus é: %s",sequencia));
    }

    public int excentricidadeDe(int u){
        this.VISITADO++;
        final int FLAG = -1;
        Map<Integer,Integer> visitados = new HashMap<>();
        ArrayDeque<Integer> fila = new ArrayDeque<>();
        fila.offer(u);
        fila.offer(FLAG);
        visitados.put(u,VISITADO);
        int profundidade = 0;
        while(true){
            Integer id = fila.poll();

            if(id == FLAG){
                if(fila.isEmpty()) break;

                fila.offer(FLAG);
                profundidade++;
            }else{
                PriorityQueue<Aresta> arestas = this.grafo.get(id);
                if(arestas != null){
                    Iterator<Aresta> iterator = arestas.iterator();
                    while(iterator.hasNext()){
                        Aresta a = iterator.next();
                        if(visitados.get(a.para) != VISITADO){
                            visitados.put(a.para,VISITADO);
                            fila.offer(a.para);
                        }
                    }
                }
            }
        }
        return profundidade;
    }
    private void calculaRadioEDiametro(){
        for(Integer u :this.grafo.keySet()){
            System.out.println(u);
            int excentricidade = excentricidadeDe(u);
            this.diametro = Math.max(this.diametro,excentricidade);
            this.raio = Math.min(this.raio,excentricidade);
        }
    }

    public int getRaio() {
        return raio;
    }

    public int getDiametro() {
        return diametro;
    }

    public void centro() {
        // verificar a excentricidade de todos os vértices
        // armazenar as excentricidades para cada vértice
        // selecionar as menores excentricidades
        // printar os vértices do centro
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<Integer> vals = new ArrayList<Integer>();

        for(Integer u :this.grafo.keySet()){
            int excentricidade = excentricidadeDe(u);
            vals.add(excentricidade);
            keys.add("V"+u);
        }

        Integer low = 0;
        for (int i = 0; i < vals.size(); i++) {
            if (i == 0 || vals.get(i) < low) {
                low = vals.get(i);
            }
        }

        for (int i = vals.size()-1; i >= 0; i--) {
            if (vals.get(i) > low) {
                vals.remove(i);
                keys.remove(i);
            }
        }

        System.out.println("Centro do grafo: ");

        for (int i = 0; i < vals.size(); i++) {
            System.out.println(keys.get(i)+" ");
        }

    }
}
