#include <stdio.h>
#include <stdlib.h>
#include "functions.h"
#include "functions.c"

#define MAX_LINE_LENGTH 1000

int main() {
    FILE *arquivo = fopen("C:\\Users\\patri\\CLionProjects\\tp_grafos\\grafo.txt", "r");
    float **grafo = inicializaGrafo(arquivo);
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
    limpaGrafo(grafo, 5);
}
