#include <stdio.h>
#include <stdlib.h>
#include "functions.h"

#define MAX_LINE_LENGTH 1000

int main() {
    FILE *arquivo = fopen("C:\\Users\\patri\\CLionProjects\\tp_grafos\\grafo.txt", "r");
    char ch;
    int counter = 0, tamanhoInt, vertice1, vertice2;
    int  tamanho;
    float  peso, grafo[3][3];
    for (int i = 0; i<3; i++){
        for (int k = 0; k<3; k++){
            grafo[i][k] = 0.0;
        }
    }
    if (arquivo == NULL){
        printf("No such file in directory");
        exit(1);
    }
    while((ch = fgetc(arquivo))!=EOF) {
        if (counter == 0){
            tamanho = (int)fgetc(arquivo)-48;
            counter ++;
        }
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
    for (int i= 0; i<tamanho; i++){
        printf("\n");
        for (int k = 0; k<tamanho; k++){
            printf("%.1f ", grafo[i][k]);
        }
    }
    fclose(arquivo);
    return 0;
}
