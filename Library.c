#include "Library.h"
#include <stdio.h>
#include <stdlib.h>

void criaGrafo(Grafo* grafo){
    for(long i =0; i<= grafo->numVertices; i++) criaLista(&grafo->adj[i]);
}
void criaLista(Lista* lista){
    lista->primeiro = (Apontador) malloc(sizeof(No));
    lista->ultimo = lista->primeiro;
    lista->primeiro->prox = NULL;
}
int ehVazia(Lista lista){
    return (lista.primeiro == lista.ultimo);
}
void insere(Aresta* aresta,Lista* lista){
    lista->ultimo->prox = (Apontador)malloc(sizeof(No));
    lista->ultimo = lista->ultimo->prox;
    lista->ultimo->aresta = *aresta;
    lista->ultimo->prox = NULL;
}
// Aqui ocorre a criação das arestas que saem de um dado vertice v1
void insereAresta(ValorVertice* v1,ValorVertice* v2,PesoVertice* peso,Grafo* grafo){
    Aresta ab;
    ab.valor = *v2; //Representa o outro vértice
    ab.peso = *peso; // representa o peso da aresta que une os vértices
    insere(&ab,&grafo->adj[*v1]); // Aqui é efetivada a inserção de uma nova aresta na lista de adjacência
    Aresta ba;
    ba.valor = *v1;
    ba.peso = *peso;
    insere(&ba,&grafo->adj[*v2]);
}

void liberaGrafo(Grafo* grafo){
    Apontador auxAnterior, aux;
    for(int i =0; i <= grafo->numVertices; i++){
        aux = grafo->adj[i].primeiro->prox;
        free(grafo->adj[i].primeiro);
        grafo->adj[i].primeiro = NULL;
        while(aux != NULL){
            auxAnterior = aux;
            aux = aux->prox;
            free(auxAnterior);
        }
    }
    grafo->numVertices = 0;
}
void imprimeGrafo(Grafo* grafo){
    Apontador aux;
    for(int i=0; i <= grafo->numVertices; i++){
        if(!ehVazia(grafo->adj[i])){
           printf("Vertice%2d:",i);
           aux =grafo->adj[i].primeiro->prox;
           while(aux != NULL){
            printf("%3d (%.1f)",aux->aresta.valor,aux->aresta.peso);
            aux = aux->prox;
           } 
           putchar('\n');
        }
    }
}
void leGrafo(Grafo* grafo){
    FILE* f = fopen("./inputs/grafo.txt","r");
    if(!f){
        printf("Deu ruim na abertura do arquivo!");
        exit(1);
    }
    int v1,v2,numVertices;
    float peso;
    fscanf(f,"%d",&grafo->numVertices);
    grafo->numArestas = 0;
    criaGrafo(grafo);
    while(!feof(f)){
        fscanf(f,"%d %d %f",&v1,&v2,&peso);
        grafo->numArestas++;
        insereAresta(&v1,&v2,&peso,grafo);
    }
    fclose(f);
}
/*
void imprimeLista(Lista);

void lerGrafo(char path[250]){
    FILE *arquivo = fopen(path, "r");
    char ch;
    int counter = 1, vertice1, vertice2;
    int ordem = (int)fgetc(arquivo)-48;
    float  peso, grafo[ordem][ordem];
    for (int i = 0; i<ordem; i++){
        for (int k = 0; k<ordem; k++){
            grafo[i][k] = 0.0;
        }
    }
    if (arquivo == NULL){
        printf("No such file in directory");
        exit(1);
    }
    while((ch = fgetc(arquivo))!=EOF) {
        if (counter == 1){
            fscanf(arquivo, "%d", &vertice1);
            counter ++;
        }
        if (counter == 2){
            fscanf(arquivo, "%d", &vertice2);
            counter ++;
        }
        if (counter == 3){
            fscanf(arquivo, "%f", &peso);
            grafo[vertice1-1][vertice2-1] = (peso);
            counter = 1;
        }
    }
    for (int i= 0; i<ordem; i++){
        if (i ==0){
            printf("");
        }
        else{
            printf("\n");
        }
        for (int k = 0; k<ordem; k++){
            printf("%.1f ", grafo[i][k]);
        }
    }
    fclose(arquivo);
}

float **inicializaGrafo(char path[250]){
    FILE *arquivo = fopen(path, "r");
    int ordem = (int)fgetc(arquivo)-48;
    float **grafo = (float **)malloc(ordem * sizeof(float *));
    int row;

    for (row = 0; row < ordem; row++) {
        grafo[row] = (float *)malloc(ordem * sizeof(float));
    }

    for (int i = 0; i<ordem; i++){
        for (int k = 0; k<ordem; k++){
            grafo[i][k] = 0.0;
        }
    }
    if (arquivo == NULL){
        printf("No such file in directory");
        exit(1);
    }
    char ch;
    int counter = 1, vertice1, vertice2;
    float  peso;
    while((ch = fgetc(arquivo))!=EOF) {
        if (counter == 1){
            fscanf(arquivo, "%d", &vertice1);
            counter ++;
        }
        if (counter == 2){
            fscanf(arquivo, "%d", &vertice2);
            counter ++;
        }
        if (counter == 3){
            fscanf(arquivo, "%f", &peso);
            grafo[vertice1-1][vertice2-1] = (peso);
            counter = 1;
        }
    }
    fclose(arquivo);
    return grafo;
}

void limpaGrafo(float **grafo, int ordem)
{
    int row;
    for (row = 0; row < ordem; row++) {
        free(grafo[row]);
    }
    free(grafo);
}

int ordemGrafo(char path[250]){
    FILE *arquivo = fopen(path, "r");
    int ordem = (int)fgetc(arquivo) - 48;
    fclose(arquivo);
    return ordem;
}

int lerTamanhoGrafo(char path[250]){
    char ch;
    int count = 0;
    FILE *arquivo = fopen(path, "r");
    int tamanho = (int)fgetc(arquivo) - 48;
    if (arquivo == NULL)
    {
        printf("nao foi possivel abrir %s", arquivo);
        return 0;
    }

    for (int c = getc(arquivo); c != EOF; c = getc(arquivo))
        if (c == '\n')
            count ++;
    fclose(arquivo);
    return count + tamanho;
}


int tamanhoGrafo(float **grafo, int ordem){
    int count = 0;
    for (int i = 0; i<ordem; i++){
        for (int k = 0; k<ordem; k++){
            if (grafo[i][k] != 0.0){
                count ++;
            }
            else{
                continue;
            }
        }
    }
    return count + ordem;
}

int **vizinhoVertice(float **grafo, int ordem, int vertice){
    int count = 0;
    for (int i = 0; i<ordem; i++){
        if (grafo[vertice-1][i] != 0.0){
            count ++;
        }
    }
    int *vizinhos = malloc(count * sizeof(float *)), aux = 0;
    for (int i = 0; i<ordem; i++){
        if (grafo[vertice-1][i] != 0.0){
            vizinhos[aux] = i + 1;
            aux ++;
        }
    }
    return vizinhos;
}

int grauVertice(float **grafo, int ordem, int vertice){
    int count = 0;
    for (int i = 0; i<ordem; i++){
        if (grafo[vertice-1][i] != 0.0){
            count ++;
        }
    }
    return count ++;
}
*/