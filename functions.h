#include <stdio.h>

void criarGrafo(FILE *arquivo){
    arquivo = fopen("C:\\Users\\patri\\CLionProjects\\tp_grafos\\grafo.txt", "r");
    char ch;
    int counter = 1, tamanho, vertice1, vertice2;
    double peso;
    double grafo[tamanho][tamanho];
    if (arquivo == NULL){
        printf("No such file in directory");
        exit(1);
    }
    while((ch = fgetc(arquivo))!=EOF) {
        if (counter == 0){
            fscanf(arquivo, "%d", &tamanho);
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
            fscanf(arquivo, "%lf", &peso);
            grafo[vertice1][vertice2] = peso;
            counter = 1;
        }
    }
    for (int i; i<tamanho; i++){
        printf("\n");
        for (int k; k<tamanho; k++){
            printf("%lf ", grafo[i][k]);
        }
    }
    fclose(arquivo);
}