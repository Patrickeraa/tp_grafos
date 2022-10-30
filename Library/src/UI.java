import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Grafo grafo;
    public UI(){
        this.grafo = new Grafo();
        grafo.criaGrafo();
        grafo.imprimeGrafo();
    }
    private void menu(){
        System.out.println( "//====[]====================================================================================\\\\\n" +
                            "||  1 || Retornar a ordem do grafo                                                          ||\n" +
                            "||  2 || Retornar o tamanho do grafo                                                        ||\n" +
                            "||  3 || Retornar os vizinhos de um vértice fornecido                                       ||\n" +
                            "||  4 || Determinar o grau de um vértice fornecido                                          ||\n" +
                            "||  5 || Retornar a sequência de graus do grafo                                             ||\n" +
                            "||  6 || Determinar a excentricidade de um vértice                                          ||\n" +
                            "||  7 || Determinar o raio do grafo                                                         ||\n" +
                            "||  8 || Determinar o diâmetro do grafo                                                     ||\n" +
                            "||  9 || Determinar o centro do grafo                                                       ||\n" +
                            "|| 10 || Vértices visitados na busca em profundidade e aresta(s) que não faz(em) dessa busca||\n" +
                            "|| 11 || Determinar distância e caminho mínimo                                              ||\n" +
                            "|| 12 || Determinar a centralidade de proximidade C de um vértice x                         ||\n" +
                            "||  0 || Para sair                                                                          ||\n" +
                            "\\\\====[]====================================================================================//\n"
        );
    }

    public void abreMenu(){
        Scanner s = new Scanner(System.in);
        int resposta;
        do{
            menu();
            resposta =s.nextInt();
            escolheOpcao(resposta);
        }while( resposta != 0);
    }

    private void escolheOpcao(int opcao){
        int u;
        switch (opcao){
            case 1: grafo.ordemDoGrafo();break;
            case 2: grafo.tamanhoDoGrafo();break;
            case 3:
                u = solicitaVertice();
                grafo.vizinhosDe(u);break;
            case 4:
                u = solicitaVertice();
                grafo.grauDe(u);break;
            case 5:
                grafo.sequenciaDeGraus();break;
            case 6:
                u = solicitaVertice();
                int exc = grafo.excentricidadeDe(u);
                System.out.println(String.format("A excentricidade de V%d é %d",u,exc));break;
            case 7:
                int raio = grafo.getRaio();
                System.out.println(String.format("O raio do grafo é %d",raio));break;
            case 8:
                int diametro = grafo.getDiametro();
                System.out.println(String.format(String.format("O diametro do grafo é %d",diametro)));break;
            case 9:
                ArrayList<String> vs = grafo.centro();
                System.out.println("Centro do grafo: ");

                for (int i = 0; i < vs.size(); i++) {
                    System.out.println(vs.get(i)+" ");
                }
                break;
            case 10:
                int origem = solicitaVertice();
                this.grafo.resetBuscaProfundidade();
                grafo.buscaProfundidade(origem);
                System.out.println("Árvore profundidade: "+grafo.getArvoreProfundidade());
                System.out.println("Arestas retorno: "+grafo.getArestasRetorno());
                break;
            case 11:
                grafo.distancia();
                break;
            case 12:
                u = solicitaVertice();
                float exec = grafo.centralidadeGrafo(u);
                System.out.println("Centralidade do vértice "+u+": "+exec);
                break;

        }
    }
    private int solicitaVertice(){
        Scanner input = new Scanner(System.in);
        System.out.print("Informe um vértice:");
        int u = input.nextInt();
        return u;
    }
}
