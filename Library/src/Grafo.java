import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Grafo {
    private int numArestas;
    private Integer VISITADO = 0;
    /*
    * Veriáveis necessárias para algorítmo para identificar SCCs
    * */
    private int sccCount,id;
    private int[] ids,low,sccs;
    private Deque<Integer> stack;
    private static int NAO_VISITADO = -1;
    private boolean[] onStack;
    private boolean visitados[];
    private List<Aresta> retorno = new ArrayList<>();
    private List<Aresta> arvoreProfundidade = new ArrayList<>();
    Map<Integer, PriorityQueue<Aresta>> grafo;

    private int raio = Integer.MAX_VALUE;
    private int diametro = 0;

    float[][] L;
    float[][] R;

    public Grafo(){
        this.grafo = new HashMap<>();
    }

    public void criaGrafo(){

        String property = System.getProperty("user.dir");
//        File f = new File(property+"/Library/resources/grafo2.txt");
         File f = new File(property+"/resources/grafo2.txt");
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
//                adicionaAresta(v,u,p); // Comentei esta linha para trabalhar com grafos direcionados
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.calculaRadioEDiametro();
        this.visitados = new boolean[this.grafo.size()+1];
    }

    private void adicionaAresta(int u,int v,float p){
        if(this.grafo.containsKey(u)){
            this.grafo.get(u).add(new Aresta(u,v,p));
        }else{
            this.grafo.put(u,new PriorityQueue<Aresta>(Arrays.asList(new Aresta(u, v, p))));
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
    public void resetBuscaProfundidade(){
        this.retorno = new ArrayList<>();
        this.arvoreProfundidade = new ArrayList<>();
        this.visitados = new boolean[this.grafo.size() + 1];
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
        PriorityQueue<Integer> sequencia = new PriorityQueue<>();
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

    public List<Aresta> getArestasRetorno() {
        return retorno;
    }

    public List<Aresta> getArvoreProfundidade() {
        return arvoreProfundidade;
    }

    public ArrayList<String> centro() {
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

        return keys;

    }

    public void buscaProfundidade(int u){

        Aresta primeira = this.grafo.get(u).peek();
        this.visitados[primeira.de] = true;


        PriorityQueue<Aresta> arestas = this.grafo.get(u);
        if(arestas != null){
            Iterator<Aresta> iterator = arestas.iterator();
            while(iterator.hasNext()){
                Aresta next = iterator.next();
                if(!this.visitados[next.para]){
                    this.arvoreProfundidade.add(next);
                    buscaProfundidade(next.para);
                }else{
                    if(!this.arvoreProfundidade.contains(next)){
                        if(!this.retorno.contains(next))
                            this.retorno.add(next);
                    }
                }
            }
        }
    }

    public int getOrdemDoGrafo(){
        return this.grafo.size();
    }

    public int getNumArestas(){
        return this.numArestas;
    }

    final static float INF = 9999.0F;

    public void distancia(){
        String property = System.getProperty("user.dir");
        File f = new File(property+"/Library/resources/grafo.txt");

        int V = getOrdemDoGrafo();
        
        // File f = new File(property+"/resources/grafo.txt");
        try (Scanner s = new Scanner(f)) {
            s.nextInt();
            this.L = new float[V][V];
            int u,v;
            float p;
            for (int k = 0; k < this.L.length; k++){
                for (int j = 0;j<this.L.length;j++){
                    if (k == j){
                        this.L[k][j] = 0.0F;
                    }
                    else{
                        this.L[k][j] = INF;
                    }
                }
            }
            while(s.hasNext()){
                u = Integer.parseInt(s.next());
                v = Integer.parseInt(s.next());
                p = Float.parseFloat(s.next());
                this.L[u-1][v-1] = p;
                this.L[v-1][u-1] = p;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.R = new float[V][V];

        for(int i = 0; i < V; i++){
            for(int j = 0; j < V; j++){

                if(this.L[i][j] == INF)
                    R[i][j] = 0;

                else
                    R[i][j] = i+1;
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (this.L[i][j] > (this.L[i][k] + this.L[k][j])) {
                        this.L[i][j] = this.L[i][k] + this.L[k][j];
                        this.R[i][j] = this.R[k][j];
                    }
                }
            }
        }

        System.out.println("Matriz de distância: ");
        printMatrix(this.L, V);

        System.out.println("Matriz de menor caminho: ");
        printMatrix(this.R, V);
    }
    
    void printMatrix(float[][] matrix, int V) {
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (matrix[i][j] == INF)
                    System.out.print("INF    ");
                else
                    System.out.print(numberFormat.format(matrix[i][j]) + "   ");
            }
            System.out.println();
        }
    }

    public float centralidadeGrafo(int indVertice){
        distancia();
        float C, dist = 0;
        int V = getOrdemDoGrafo();

        for (int i = 0; i < V; i++) {
            dist += this.L[indVertice-1][i];
        }
        
        C = (V-1)/dist;
        
        return C;
    }
    public boolean temCiclos(){
        resolve();
        System.out.printf("Número de ciclos: %d\n",sccCount);
        return sccCount > 0;
    }
    private void resolve(){
        // Inicializando todas as variáveis
        sccCount = id = 0;
        //Número de vértices
        int n = grafo.size() + 1;
        //Array de ids para identificar os vértices
        ids = new int[n];
        // Array para determinar o menor id dos vértices de um ciclo
        low = new int[n];
        // Array para retornar os componentes (não foi pedido, apenas se necessário)
        sccs = new int[n];
        // Array para verificar se um vértice está na pilha
        onStack = new boolean[n];
        // Pilha para conter os vértices que estão sendo visitados no momento
        stack = new ArrayDeque<>();
        // Inicializando o vetor de ids com todas posições não visitadas
        Arrays.fill(ids,NAO_VISITADO);
        // Percorrendo todos os vértices e realizando dfs nos que não foram visitados ainda.
        for(int i=1; i< n;i++){
            if(ids[i] == NAO_VISITADO) dfs(i);
        }
    }
    private void dfs(int de){
        //Definindo um id para cada nó
        ids[de] = low[de] = id++;
        //Colocando o nó inicial na pilha
        stack.push(de);
        //Identificando que um vértice está na pilha
        onStack[de] = true;

        // Recuperando arestas do grafo
        PriorityQueue<Aresta> arestas = grafo.get(de);
        if(arestas != null){
            // Iterar por todos os vizinhos de um vértice
            Iterator<Aresta> iterator = arestas.iterator();
            while(iterator.hasNext()){
                Aresta next = iterator.next();
                /* Parte mais impoortante do algorítmo
                * Se um vértice vizinho não foi visitado, realiza bfs naquele vértice
                * Quando retornar do bfs, deve-se comparar o menor valor entre o vértice de origem e o de destino
                * Acrescentar no array low, o menor id, entre os vértices visitados
                * Como se trata do retorno de uma chamada recursiva, é possível definir o menor valor para todos os vértices já visitados
                *
                * */
                if(ids[next.para] == NAO_VISITADO) dfs(next.para);
                if(onStack[next.para]) low[de] = Math.min(low[de],low[next.para]);
            }
        }
        /* Ao terminar de visitar todos os vértices, identifica-se a posição do vértice de menor id
         * Com isso é possível saber onde o cíclo começa.
         * E para saber onde o ciclo termina, basta remover da pilha os vértices com o mesmo id
         * Ao terminar essa remoção sabemos que encontramos um componente (ou ciclo)
         * */
//        System.out.println((ids));
        if(ids[de] == low[de]) {
            for (int node = stack.pop(); ; node = stack.pop()) {
                onStack[node] = false;
                sccs[node] = sccCount;
                if (node == de) break;
            }
            sccCount++;
        }
    }

}
