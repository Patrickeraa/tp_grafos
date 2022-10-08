#include <stdio.h>
#include <stdlib.h>
#include "functions.h"
#include "functions.c"

int main() {
    char path[250] = "C:\\Users\\patri\\CLionProjects\\tp_grafos\\grafo.txt";
    float **grafo = inicializaGrafo(path);
    for (int i= 0; i<5; i++){
        if (i ==0){
            printf("");
        }
        else{
            printf("\n");
        }
        for (int k = 0; k<5; k++){
            printf("%.1f ", grafo[i][k]);
        }
    }
    int ordem = ordemGrafo(path);
    printf("\n%d", ordem);
    limpaGrafo(grafo, 5);
}
