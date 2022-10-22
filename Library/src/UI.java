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
        switch (opcao){
            case 1: grafo.ordemDoGrafo();break;
        }
    }
}
