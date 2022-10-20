#ifndef _LIBRARY_H_
#define _LIBRARY_H_

#define MAXNUMVERTICES 100
#define MAXNUMARESTAS 4500

typedef int ValorVertice;
typedef float PesoVertice;
typedef struct{
  ValorVertice valor;
  PesoVertice peso;
}Aresta;
typedef struct No* Apontador;
typedef struct No{
  Aresta aresta;
  Apontador prox;
}No;
typedef struct Lista{
  Apontador primeiro,ultimo;
}Lista;
typedef struct Grafo{
  Lista adj[MAXNUMVERTICES + 1];
  ValorVertice numVertices;
  short numArestas;
}Grafo;


void leGrafo(Grafo*);
void criaGrafo(Grafo*);
void criaLista(Lista*);
int ehVazia(Lista);
void insere(Aresta*,Lista*);
void insereAresta(ValorVertice*,ValorVertice*,PesoVertice*,Grafo*);
void liberaGrafo(Grafo*);
void imprimeGrafo(Grafo*);
void imprimeLista(Lista);





//lê o grafo com base no arquivo de texto e printa o grafo.
void lerGrafo(char path[250]);

//inicializa o grafo retornando um grafo com base no arquivo lido
float **inicializaGrafo(char path[250]);

//esvazia a memória utilizada pelo grafo
void limpaGrafo(float **grafo, int tamanho);

//retorna a ordem do grafo lendo o arquivo de texto
int ordemGrafo(char path[250]);

//lê o tamanho do grafo, somando a ordem com o numero de arestas
int lerTamanhoGrafo(char path[250]);

//recebe o grafo e a ordem, retornando o tamanho com base no grafo previamente criado, não lendo o arquivo de texto
int tamanhoGrafo(float **grafo, int ordem);

//lê um grafo, a ordem e o vertice alvo, retornando um vetor que contem os vertices adjacentes.
int **vizinhoVertice(float **grafo, int ordem, int vertice);

//retorna a quantidade de vizinhos que um vertice possui
int grauVertice(float **grafo, int ordem, int vertice);
#endif