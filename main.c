#include <stdio.h>
#include <stdlib.h>
#include "Library.h"

Grafo grafo;

int main() {

    leGrafo(&grafo); 
    imprimeGrafo(&grafo);
    liberaGrafo(&grafo);
    // float **grafo = inicializaGrafo(path);
    // int ordem = ordemGrafo(path);
    // for (int i= 0; i<5; i++){
    //     if (i ==0){
    //         printf("");
    //     }
    //     else{
    //         printf("\n");
    //     }
    //     for (int k = 0; k<5; k++){
    //         printf("%.1f ", grafo[i][k]);
    //     }
    // }
    // int tamanho = tamanhoGrafo(grafo, ordem);
    // printf("\n%d", tamanho);

    // int **num_vizinhos = vizinhoVertice(grafo, ordem, 1);
    // printf("\n%d", num_vizinhos[1]);

    // int grau = grauVertice(grafo, ordem, 1);
    // printf("\n%d", grau);
    return 0;
}
