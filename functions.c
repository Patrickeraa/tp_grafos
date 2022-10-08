#include "functions.h"
#include <stdio.h>
#include <stdlib.h>

void lerGrafo(char path[250]){
    FILE *arquivo = fopen(path, "r");
    char ch;
    int counter = 1, vertice1, vertice2;
    int tamanho = (int)fgetc(arquivo)-48;
    float  peso, grafo[tamanho][tamanho];
    for (int i = 0; i<tamanho; i++){
        for (int k = 0; k<tamanho; k++){
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
    for (int i= 0; i<tamanho; i++){
        if (i ==0){
            printf("");
        }
        else{
            printf("\n");
        }
        for (int k = 0; k<tamanho; k++){
            printf("%.1f ", grafo[i][k]);
        }
    }
    fclose(arquivo);
}

float **inicializaGrafo(char path[250]){
    FILE *arquivo = fopen(path, "r");
    int tamanho = (int)fgetc(arquivo)-48;
    float **grafo = (float **)malloc(tamanho * sizeof(float *));
    int row;

    for (row = 0; row < tamanho; row++) {
        grafo[row] = (float *)malloc(tamanho * sizeof(float));
    }

    for (int i = 0; i<tamanho; i++){
        for (int k = 0; k<tamanho; k++){
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

void limpaGrafo(float **grafo, int tamanho)
{
    int row;
    for (row = 0; row < tamanho; row++) {
        free(grafo[row]);
    }
    free(grafo);
}

int ordemGrafo(char path[250]){
    FILE *arquivo = fopen(path, "r");
    int tamanho = (int)fgetc(arquivo) - 48;
    fclose(arquivo);
    return tamanho;
}
