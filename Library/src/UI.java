import java.util.Scanner;

public class UI {
    private Grafo grafo;
    public UI(){
        this.grafo = new Grafo();
        grafo.criaGrafo();
        grafo.imprimeGrafo();
    }
    private void menu(){
        System.out.println("" +
                "1 Retornar a ordem do grafo\n" +
                "2 Retornar o tamanho do grafo\n" +
                "3 Retornar os vizinhos de um vértice fornecido\n" +
                "4 Determinar o grau de um vértice fornecido\n" +
                "5 Retornar a sequência de graus do grafo\n" +
                "6 Determinar a excentricidade de um vértice\n" +
                "7 Determinar o raio do grafo\n" +
                "8 Determinar o diâmetro do grafo\n" +
                "9 Determinar o centro do grafo\n" +
                "10 sequência de vértices visitados na busca em profundidade e informar a(s) aresta(s) que não faz(em) parte da árvore de busca em profundidade\n" +
                "11 Determinar distância e caminho mínimo\n" +
                "12 Determinar a centralidade de proximidade C de um vértice x, dada por" +
                "0 para sair"
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

        }
    }
    private int solicitaVertice(){
        Scanner input = new Scanner(System.in);
        System.out.print("Informe um vértice:");
        int u = input.nextInt();
        return u;
    }
}
